package frc.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.Autonomous;
import frc.robot.commands.AutonomousDrivePID;
import frc.robot.commands.ForTurnPIDTest;
import frc.robot.commands.Teleoperated;
import frc.robot.commands.delay;
import frc.robot.commands.extendHM;
import frc.robot.commands.releaseHatch;
import frc.robot.commands.retractHM;
import frc.robot.commands.takeHatch;
import frc.robot.sensors.NavX;
import frc.robot.subsystems.Cargo;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Hatch;
import frc.robot.utils.POVConverter;
import frc.robot.utils.SoundTrigger;
import frc.robot.utils.Vision;
import frc.robot.sensors.Encoder;



public class Robot extends TimedRobot {
  Autonomous autoCG;
  // Define OI
  public static OI m_oi;
  // Define subsytems
  public static Cargo m_cargo;
  public static Hatch m_hatch;
  public static Climber m_climber;
  // public static RobotDrive driveTrainRobotDrive41;
  public static DriveTrain m_driveTrain;
  public static POVConverter m_povConverter;

  NetworkTableInstance inst = NetworkTableInstance.getDefault();
  NetworkTable table = inst.getTable("datatable");
  NetworkTableEntry roboState;
  NetworkTableEntry matchTime;
  NetworkTableEntry tymeButton;
  NetworkTableEntry pistonTime;
  NetworkTableEntry pistonStatus;
  NetworkTableEntry voltage;
  NetworkTableEntry speed;
  NetworkTableEntry cargoShift;
  
  public double Tspeed;
  public static SoundTrigger sound_trigger;  
  public static NavX m_navx;
  public static Vision vision;
  public static double encoderPosition;
  public static double targetPosition = 500;
  public static double rotation;
  public static Encoder encoder;
  public static double currentAngle;
  public static double visionAngle;
  

 
  @Override
  public void robotInit() {
    
    // Construct Subsytems
    m_cargo = new Cargo();
    m_hatch = new Hatch();
    m_climber = new Climber();
    m_driveTrain = new DriveTrain();
    m_povConverter = new POVConverter();
    sound_trigger = new SoundTrigger();
    m_navx = new NavX();
    vision = new Vision();
    encoder = new Encoder();

    autoCG = new Autonomous();
    // Construct OI
    m_oi = new OI();
 
    vision.visionStarter.setBoolean(false);    

    Robot.m_hatch.closeCompressor();
    m_navx.zeroYaw();
    Robot.vision.x.setNumber(0);
    Robot.vision.navxAngle.setNumber(Robot.m_navx.yawValue());
    vision.downLimitSwitchStatus.setDefaultBoolean(false);
    vision.upLimitSwitchStatus.setDefaultBoolean(true);
    SmartDashboard.putData("Gyro", m_navx.ahrs);
    
    
    


  }

  @Override
  public void robotPeriodic() {
 
    visionAngle = vision.angle.getDouble(0);
 
    vision.navxAngle.getNumber(79);
    }

  @Override
  public void disabledInit() {

  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
   
  }

  @Override
  public void autonomousInit() {
    m_navx.zeroYaw();
    Scheduler.getInstance().run();
 
    autoCG.addSequential(new extendHM());

    autoCG.addSequential(new takeHatch());
 
    autoCG.start();
    
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();

  }

  @Override
  public void teleopInit() {


  }

  @Override
  public void teleopPeriodic() {
    
    Scheduler.getInstance().run();
   System.out.println(m_povConverter.convertToButton("logitech"));
    
  
  }

   

  @Override
  public void testPeriodic() {
  }
}
