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

/**
 * Add your docs here.
 */
public class cargoJointDown extends Command {

  NetworkTableInstance inst = NetworkTableInstance.getDefault();
  NetworkTable table = inst.getTable("datatable");
  static NetworkTableEntry upSwitch;

  public cargoJointDown() {
      // Use requires() here to declare subsystem dependencies
      // eg. requires(chassis);
      requires(Robot.m_cargo);
      upSwitch = table.getEntry("upSwitch");
    }
  
    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }
  
    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Robot.m_cargo.cargoJointDown();
        upSwitch.setBoolean(false);
    }
  
    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
      return Robot.m_cargo.downLimitSwStatus;
    }
  
    // Called once after isFinished returns true
    @Override
    protected void end() {
      Robot.m_cargo.cargoJointStop();
    }
  
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
      end();
    }
  }

