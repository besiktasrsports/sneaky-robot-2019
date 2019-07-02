package frc.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
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
 // public double exampleEnc;

 
  @Override
  public void robotInit() {
    
    // Construct Subsytems
    m_cargo = new Cargo();
    m_hatch = new Hatch();
    m_climber = new Climber();
    m_driveTrain = new DriveTrain();
    sound_trigger = new SoundTrigger();
    m_navx = new NavX();
    vision = new Vision();
    encoder = new Encoder();
    //CameraServer.getInstance().startAutomaticCapture();
    // pistonTime = table.getEntry("pistonTime");
    // pistonStatus = table.getEntry("pistonStatus");
    // voltage = table.getEntry("voltage");
    autoCG = new Autonomous();
    // Construct OI
    m_oi = new OI();
    // speed = table.getEntry("speed");
    // cargoShift = table.getEntry("shift");
    vision.visionStarter.setBoolean(false);    

    Robot.m_hatch.closeCompressor();
    m_navx.zeroYaw();
    Robot.vision.x.setNumber(0);
    Robot.vision.navxAngle.setNumber(Robot.m_navx.yawValue());
  //  vision.holdStatus.setBoolean(true);
   // vision.extendStatus.setBoolean(true);
  }

  @Override
  public void robotPeriodic() {
    currentAngle = m_navx.yawValue();
    visionAngle = vision.angle.getDouble(0);
    // System.out.println(encoder.getEncoderPosition());
    /*
    System.out.print("Current angle: ");
    System.out.println(currentAngle);
    
    System.out.print("Vision angle: ");
    System.out.println(visionAngle);
    */
    

   // System.out.println(Vision.x.getNumber(0));
    vision.navxAngle.getNumber(79);
    }

  @Override
  public void disabledInit() {
    // roboState = table.getEntry("roboState");
    // roboState.setString("no");
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
    // vision.visionStarter.setBoolean(false);
  }

  @Override
  public void autonomousInit() {
    m_navx.zeroYaw();
    Scheduler.getInstance().run();
    /*
    Scheduler.getInstance().removeAll();
    autoCG.addSequential(new AutonomousDrivePID(210),5);
    autoCG.addSequential(new releaseHatch());
    autoCG.addSequential(new AutonomousDrivePID(-20));
    autoCG.start();
    */
    // autoCG.addSequential(new ForTurnPIDTest());
    // autoCG.start();
    autoCG.addSequential(new extendHM());
  //  autoCG.addSequential(new delay(1));
   // autoCG.addSequential(new retractHM());
   // autoCG.addSequential(new delay(1));
    
   // autoCG.addSequential(new delay(1));
   // autoCG.addSequential(new releaseHatch());
    autoCG.addSequential(new takeHatch());
    //autoCG.addSequential(new delay(1));
    
    autoCG.start();
    
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    /*
    voltage.setDouble(DriverStation.getInstance().getBatteryVoltage());
    
    if (m_oi.getXbox().getY()<0){

      Tspeed = -m_oi.getXbox().getY();
    
    }else{

      Tspeed = m_oi.getXbox().getY();

    }
    speed.setDouble(Tspeed*10);
    */
  }

  @Override
  public void teleopInit() {

    
    // roboState = table.getEntry("roboState");
    // roboState.setString("teleop");
    // matchTime.setDouble(0);
    // m_hatch.openCompressor();


    // vision.visionStarter.setBoolean(true);
    // Robot.m_hatch.openCompressor();
    //exampleEnc = m_climber.liftMotor1.getSelectedSensorPosition();
    // m_climber.liftMotor1.setStatusFramePeriod(StatusFrame.Status_12_Feedback1, 1);
    // m_climber.liftMotor1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
    // m_climber.liftMotor1.setSelectedSensorPosition(0);
    // rotation = targetPosition/48;
    
 

  }

  @Override
  public void teleopPeriodic() {
    
    Scheduler.getInstance().run();
   // Encoder.Left_Encoder_Position();
   // Encoder.Right_Encoder_Position();
   // System.out.println(m_navx.yawValue());

    // voltage.setDouble(DriverStation.getInstance().getBatteryVoltage());
    // cargoShift.setBoolean(m_cargo.hShifter);

    /*
    if (m_oi.getXbox().getY()<0){

      Tspeed = -m_oi.getXbox().getY();
    
    }else{

      Tspeed = m_oi.getXbox().getY();

    }
    speed.setDouble(Tspeed*10);
    */
    // pistonStatus.setBoolean(m_hatch.ps);
    // pistonTime.setNumber(m_hatch.pt);  
    // System.out.println(matchTime);
   
    // System.out.println(limitSwStatus);
    /*
    if(m_cargo.counterDown.get() > 0) {
      m_cargo.downSwitch.setBoolean(true);
      m_cargo.downLimitSwStatus = true;
      m_cargo.counterDown.reset();
      m_cargo.downLimitSwStatus = false;
    }
    */
  }

   // vision.visionStarter.setBoolean(true);
   /*
    if(vision.buttonFlag == true) {
      teleopCG.addSequential(new ForTurnPIDTest());
      teleopCG.start();
    }
    */
    
    /*
    encoderPosition = Robot.m_climber.liftMotor1.getSelectedSensorPosition();
    System.out.println("Encoder Position   : " + encoderPosition);
    System.out.println("Rotation Counter : " + rotation);
    
    if (encoderPosition <= rotation*4100 ){
      
      m_climber.liftMotor1.set(0.3);
      m_climber.liftMotor2.set(0.3);
    }
    else {
      m_climber.liftMotor1.set(0);
      m_climber.liftMotor2.set(0);
    }
    */

  @Override
  public void testPeriodic() {
  }
}
