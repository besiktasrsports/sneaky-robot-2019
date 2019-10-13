/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.auto.Autonomous;
import frc.robot.sensors.Encoder;
import frc.robot.subsystems.*;
import frc.robot.utils.Vision;

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
  public static ClimbDriver m_climbDriver;
  public static CameraServo m_cameraServo;
  public static Intake m_intake;
  public static Vision m_vision;
  // Construsct Sensors
  public static Encoder m_encoder;
  // Construct OI
  public static OI m_oi;
  Autonomous autoCG;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_driveTrain = new DriveTrain();
    m_arm = new Arm();
    m_climber = new Climber();
    m_intake = new Intake();
    m_climbDriver = new ClimbDriver();
    m_cameraServo = new CameraServo();
    m_encoder = new Encoder();
    m_vision = new Vision();
    autoCG = new Autonomous();
    m_oi = new OI();

    CameraServer.getInstance().startAutomaticCapture();
    m_intake.compressor.setClosedLoopControl(false);
    SmartDashboard.putData("Auto mode", m_chooser);
    
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // System.out.println(m_intake.intakeLimitSw.get());
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
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
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
   Scheduler.getInstance().run();

   m_intake.intakeStateChanger("HATCH");
   m_arm.calibrationSwCounter.reset();
   m_arm.frontFloorCargoCounter.reset();
   m_arm.rearFloorCargoCounter.reset();
    
   // retract cyclinders here ?
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.

    // no need to change to hatch state
    // m_intake.intakeStateChanger("HATCH");
    m_arm.calibrationSwCounter.reset();
    // Erase those
    m_arm.frontFloorCargoCounter.reset();
    m_arm.rearFloorCargoCounter.reset();
    
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
   // m_encoder.printEncoderVelocity();
   m_arm.readArmEncoder(); // Assign this to a variables

  if(m_arm.calibrationSwCounter.get() != 0){
    m_arm.calibrationSwStatus = true;
    m_arm.calibrationSwCounter.reset();
    
  }
  else{
    m_arm.calibrationSwStatus = false;

    if(m_intake.intakeLimitSwCounter.get() != 0){
      m_intake.intakeLimitSwStatus = true;
      m_intake.intakeLimitSwCounter.reset();
    }
    else{
      m_intake.intakeLimitSwStatus = false;
    }
    //System.out.println("Intake Limit Switch Status : "+m_intake.intakeLimitSwStatus);
  }

  //System.out.println("Climber Limit Switch Status : "+m_climber.limitSwStatus);
  //System.out.println("Arm Calibration Limit Switch Status : "+m_arm.calibrationSwStatus);
    

    
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
