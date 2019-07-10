/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.io.IOException;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.auto.Autonomous;
import frc.robot.sensors.Encoder;
import frc.robot.sensors.navxGyro;
import frc.robot.subsystems.*;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.PathfinderFRC;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  // Construct Subsystems
  public static DriveTrain m_driveTrain;
  public static Arm m_arm;
  public static Climber m_climber;
  public static Intake m_intake;
  // Construct OI
  public static OI m_oi;
  Autonomous autoCG;
  SendableChooser<Command> m_chooser = new SendableChooser<>();
  private static final int k_ticks_per_rev = 1024;
  private static final double k_wheel_diameter = 6.0 / 12.0; // 6 inches wheel diameter
  private static final double k_max_velocity = 12; // 12 ft/s max velocity

  /*
  private static final int k_left_channel = 0;
  private static final int k_right_channel = 1;
  private static final int k_left_encoder_port_a = 0; 
  private static final int k_left_encoder_port_b = 1; 
  private static final int k_right_encoder_port_a =2;
  private static final int k_right_encoder_port_b = 3;
  private static final int k_gyro_port = 0;
  */

  private static final String k_path_name = "LeftL1FLCS";
  private EncoderFollower m_left_follower;
  private EncoderFollower m_right_follower;
  private Notifier m_follower_notifier;
  public static Encoder m_encoder;
  public static navxGyro m_navx;

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    m_driveTrain = new DriveTrain();
    m_arm = new Arm();
    m_climber = new Climber();
    m_intake = new Intake();
    autoCG = new Autonomous();
    m_encoder = new Encoder();
    m_navx = new navxGyro();
    m_oi = new OI();

    SmartDashboard.putData("Auto mode", m_chooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode. You
   * can use it to reset any subsystem information you want to clear when the
   * robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable chooser
   * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
   * remove all of the chooser code and uncomment the getString code to get the
   * auto name from the text box below the Gyro
   *
   * <p>
   * You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons to
   * the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    // Scheduler.getInstance().removeAll();

    // autoCG.addSequential(new ExampleCommand());

    // autoCG.start();
    try {
      // Left and Right are reversed, soon to be fixed by jaci
      Trajectory left_trajectory = PathfinderFRC.getTrajectory(k_path_name + ".right");
      Trajectory right_trajectory = PathfinderFRC.getTrajectory(k_path_name + ".left");

      m_left_follower = new EncoderFollower(left_trajectory);
      m_right_follower = new EncoderFollower(right_trajectory);

      m_left_follower.configureEncoder((int)m_encoder.getLeftEncoderPosition(), k_ticks_per_rev, k_wheel_diameter);
      m_left_follower.configurePIDVA(1.0, 0.0, 0.0, 1 / k_max_velocity, 0);

      m_right_follower.configureEncoder((int)m_encoder.getRightEncoderPosition(), k_ticks_per_rev, k_wheel_diameter);
      m_right_follower.configurePIDVA(1.0, 0.0, 0.0, 1 / k_max_velocity, 0);

      m_follower_notifier = new Notifier(this::followPath);
      m_follower_notifier.startPeriodic(left_trajectory.get(0).dt);
      
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    // Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    m_follower_notifier.stop();
    m_driveTrain.leftSide.set(0);
    m_driveTrain.rightSide.set(0);
    
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

  private void followPath() {
    if (m_left_follower.isFinished() || m_right_follower.isFinished()) {
      m_follower_notifier.stop();
      } else {
      // TODO: Reconfigure encoders
      double left_speed = m_left_follower.calculate(m_encoder.getLeftEncoderPosition());
      double right_speed = m_right_follower.calculate(m_encoder.getRightEncoderPosition());
      // TODO: Reconfigure gyro, this could be inverted, could be converted to radians
      double heading = m_navx.ahrs.getAngle(); // Check this again
      double desired_heading = Pathfinder.r2d(m_left_follower.getHeading());
      double heading_difference = Pathfinder.boundHalfDegrees(desired_heading - heading);
      double turn = 0.8 * (-1.0/80.0) * heading_difference;
      m_driveTrain.rightSide.set(right_speed - turn);
      m_driveTrain.leftSide.set(left_speed + turn);
      }

  }


}
