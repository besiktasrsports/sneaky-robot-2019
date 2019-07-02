/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;



//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
//import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.commands.xboxDrive;


public class DriveTrain extends Subsystem {

  // Define variables and actuators here as private:
  // private WPI_TalonSRX leftRearTalon;

  /*public WPI_TalonSRX driveTrainLeftFrontMotor;
  public WPI_TalonSRX driveTrainLeftRearMotor;
  public WPI_VictorSPX driveTrainRightFrontMotor;
  public WPI_VictorSPX driveTrainRightRearMotor;
  private RobotDrive robotDrive41;*/

  public DriveTrain() {
    // Construct objects here
    // leftFrontTalon = new WPI_TalonSRX(11);

    /*driveTrainLeftFrontMotor = new WPI_TalonSRX(16);
    driveTrainLeftFrontMotor.setInverted(true);
    
    driveTrainLeftRearMotor = new WPI_TalonSRX(13);
    driveTrainLeftRearMotor.setInverted(true);
    
    driveTrainRightFrontMotor = new WPI_VictorSPX(3);
    driveTrainRightFrontMotor.setInverted(true);
    
    driveTrainRightRearMotor = new WPI_VictorSPX(4);
    driveTrainRightRearMotor.setInverted(true);

    robotDrive41 = new RobotDrive(driveTrainLeftFrontMotor, driveTrainLeftRearMotor,
        driveTrainRightFrontMotor, driveTrainRightRearMotor);
        
    robotDrive41.setSafetyEnabled(false);
    robotDrive41.setExpiration(0.1);
    robotDrive41.setSensitivity(0.5);
    robotDrive41.setMaxOutput(1.0);*/

  }

  @Override
  public void initDefaultCommand() {
    Robot.m_driveTrain.setDefaultCommand(new xboxDrive());
  
  }

  @Override
  public void periodic() {
    // Put code here to be run every loop

  }
  public void autonomousTurn(double speed) 
 	{
 		// if speed is - robot goes left, else right
 	/*	driveTrainLeftFrontMotor.set(-speed);
 		driveTrainRightFrontMotor.set(-speed);
 		driveTrainLeftRearMotor.set(-speed);
 		driveTrainRightRearMotor.set(-speed);*/
  } 
  public void autonomousDrive(double speed) 
  {
    // if speed is - robot goes left, else right
   /* driveTrainLeftFrontMotor.set(speed);
    driveTrainRightFrontMotor.set(-speed-0.05);
    driveTrainLeftRearMotor.set(speed);
    driveTrainRightRearMotor.set(-speed-0.05);*/
 } 
  public void autonomousStop() 
  {
    // if speed is - robot goes left, else right
    /*driveTrainLeftFrontMotor.set(0);
    driveTrainRightFrontMotor.set(0);
    driveTrainLeftRearMotor.set(0);
    driveTrainRightRearMotor.set(0);*/
  }

  public void driveBase() {
 //   robotDrive41.arcadeDrive(Robot.m_oi.xbox.getY(),Robot.m_oi.xbox.getX());
  }
}
