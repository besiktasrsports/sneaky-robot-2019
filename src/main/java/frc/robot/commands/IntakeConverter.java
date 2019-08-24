/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class IntakeConverter extends Command {
  private String intakeState;
  public IntakeConverter() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_intake);
   
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
   Robot.m_intake.intakeStateChangeBoolean = !Robot.m_intake.intakeStateChangeBoolean;
   if(Robot.m_intake.intakeStateChangeBoolean == true){
     intakeState = "CARGO";
   }
   else if(Robot.m_intake.intakeStateChangeBoolean == false){
     intakeState = "HATCH";
   }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(intakeState == "HATCH"){
      Robot.m_intake.intakeStateChanger("HATCH");
    }
    else if(intakeState == "CARGO"){
      Robot.m_intake.intakeStateChanger("CARGO");
    }
    else{
      Robot.m_intake.intakeStateChanger("FREE");
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
