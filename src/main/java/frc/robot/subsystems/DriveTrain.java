/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public WPI_VictorSPX driveTrainFrontLeftMotor;
  public WPI_VictorSPX driveTrainFrontRightMotor;
  public WPI_VictorSPX driveTrainRearLeftMotor;
  public WPI_VictorSPX driveTrainRearRightMotor;
  public SpeedControllerGroup rightSide;
  public SpeedControllerGroup leftSide;
  private DifferentialDrive differentialDrive41;



  public DriveTrain(){

    driveTrainFrontLeftMotor = new WPI_VictorSPX(14);
    driveTrainFrontLeftMotor.setInverted(true);

    driveTrainFrontRightMotor = new WPI_VictorSPX(13);
    driveTrainFrontRightMotor.setInverted(true);

    driveTrainRearLeftMotor = new WPI_VictorSPX(14);
    driveTrainRearLeftMotor.setInverted(true);

    driveTrainRearRightMotor = new WPI_VictorSPX(15);
    driveTrainRearRightMotor.setInverted(true);

    rightSide = new SpeedControllerGroup(driveTrainFrontRightMotor, driveTrainRearRightMotor);
    leftSide  = new SpeedControllerGroup(driveTrainFrontLeftMotor, driveTrainRearLeftMotor);


    differentialDrive41 = new DifferentialDrive(leftSide, rightSide);

    differentialDrive41.setSafetyEnabled(false);
    differentialDrive41.setExpiration(0.1);
    // No sensitivity for differantial drive
    differentialDrive41.setMaxOutput(1.0);

  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  public void autonomousTurn(double speed) 
 	{
     // if speed is - robot goes left, else right
    rightSide.set(speed);
    leftSide.set(-speed);

  } 
  public void autonomousDrive(double speed) 
  {
    
    rightSide.set(speed);
    leftSide.set(speed);
 } 
  public void autonomousStop() 
  {
   
    rightSide.set(0);
    leftSide.set(0);
  }

  public void driveBase() {
    // Arcade Drive
    differentialDrive41.arcadeDrive(Robot.m_oi.xbox.getY(),Robot.m_oi.xbox.getX());
    // Cheesy Drive
    // differentialDrive41.curvatureDrive(Robot.m_oi.xbox.getY(),Robot.m_oi.xbox.getX(), Robot.m_oi.xbox.getRawButton(0)) ;
  }
}
