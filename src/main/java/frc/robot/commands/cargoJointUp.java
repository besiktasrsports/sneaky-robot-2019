/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;


public class cargoJointUp extends Command {
    
  NetworkTableInstance inst = NetworkTableInstance.getDefault();
  NetworkTable table = inst.getTable("datatable");
  static NetworkTableEntry downSwitch;

  public cargoJointUp() {
    
      // Use requires() here to declare subsystem dependencies
      // eg. requires(chassis);
      requires(Robot.m_cargo);
    
      downSwitch = table.getEntry("downSwitch");
    }
  
    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }
  
    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {    
    
        Robot.m_cargo.cargoJointUp();
        downSwitch.setBoolean(false);
  
    }
  
    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
      return Robot.m_cargo.upLimitSwStatus;
    }
  
    // Called once after isFinished returns true
    @Override
    protected void end() {
      //Robot.m_cargo.cargoJointMotor.set(-.3);
     // Robot.m_cargo.cargoJointMotor.set(0.2);
    }
    
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
     //  Robot.m_cargo.cargoJointMotor.set(0);
        
    }
  }
  