/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class climbDriveForward extends Command {
  public climbDriveForward() {
    requires(Robot.m_climbDriver);
    // Check drivetrain requirement
    requires(Robot.m_driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.m_climbDriver.climbDriveForward(0.5);
    // Might change these
    Robot.m_driveTrain.leftSide.set(-0.3);
    Robot.m_driveTrain.rightSide.set(0.3);

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_climbDriver.climbDriveForward(0);
    Robot.m_driveTrain.leftSide.set(0);
    Robot.m_driveTrain.rightSide.set(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();

  }
}
