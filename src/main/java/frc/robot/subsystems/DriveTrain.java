/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
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
  private RobotDrive robotDrive41;



  public DriveTrain(){

    driveTrainFrontLeftMotor = new WPI_VictorSPX(12);
    driveTrainFrontLeftMotor.setInverted(true);


    driveTrainFrontRightMotor = new WPI_VictorSPX(13);
    driveTrainFrontRightMotor.setInverted(true);


    driveTrainRearLeftMotor = new WPI_VictorSPX(14);
    driveTrainRearLeftMotor.setInverted(true);


    driveTrainRearRightMotor = new WPI_VictorSPX(15);
    driveTrainRearRightMotor.setInverted(true);


    robotDrive41 = new RobotDrive(driveTrainFrontLeftMotor, driveTrainRearLeftMotor,
    driveTrainFrontRightMotor, driveTrainRearRightMotor);

    robotDrive41.setSafetyEnabled(false);
    robotDrive41.setExpiration(0.1);
    robotDrive41.setSensitivity(0.5);
    robotDrive41.setMaxOutput(1.0);

  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  public void autonomousTurn(double speed) 
 	{
 		// if speed is - robot goes left, else right
 		driveTrainFrontLeftMotor.set(-speed);
 		driveTrainFrontRightMotor.set(-speed);
 		driveTrainRearLeftMotor.set(-speed);
 		driveTrainRearRightMotor.set(-speed);
  } 
  public void autonomousDrive(double speed) 
  {
    
    driveTrainRearRightMotor.set(speed);
    driveTrainFrontRightMotor.set(-speed);
    driveTrainRearLeftMotor.set(speed);
    driveTrainRearRightMotor.set(-speed);
 } 
  public void autonomousStop() 
  {
   
    driveTrainRearRightMotor.set(0);
    driveTrainFrontRightMotor.set(0);
    driveTrainRearLeftMotor.set(0);
    driveTrainRearRightMotor.set(0);
  }

  public void driveBase() {
    robotDrive41.arcadeDrive(Robot.m_oi.xbox.getY(),Robot.m_oi.xbox.getX());
  }
}
