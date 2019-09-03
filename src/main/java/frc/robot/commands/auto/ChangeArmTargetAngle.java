/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ChangeArmTargetAngle extends Command {
  // May add this to another subsytem?
  double _targetAngle;
  public ChangeArmTargetAngle(double _angle) {
    requires(Robot.m_arm);
    this._targetAngle = _angle;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if(_targetAngle == 0.0 && Robot.m_intake.intakeState == "HATCH"){
      //System.out.println("Intake state is hatch and target is 0, i can pass");
      Robot.m_arm.targetAngle = 0.0;
    }
    else if(_targetAngle == 0.0 && Robot.m_intake.intakeState != "HATCH"){
      //System.out.println("Intake state is not hatch and target is 0, i can't pass");
      Robot.m_arm.targetAngle = Robot.m_arm.targetAngle;
    }
    else if(_targetAngle != 0.0){
      //System.out.println("Target is not 0, i can pass");
      Robot.m_arm.targetAngle = _targetAngle;
    }
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
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
