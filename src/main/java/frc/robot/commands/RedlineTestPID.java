/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RedlineTestPID extends Command {

  double currentRound;
  double targetRound;
  double accuracy = 3;
  double error;
  double old_error;
  double kP = 0.009;
  double kD = 0.08;
  double power;
  double zeroVar;
  int trueFlag;

  public RedlineTestPID(double _targetRound) {
    requires(Robot.m_intake);
    this.targetRound = _targetRound;
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    power = 0;
    zeroVar = Robot.m_encoder.getLeftEncoderRound();
   

    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    currentRound = Robot.m_encoder.getLeftEncoderRound()-zeroVar;
    error = targetRound-currentRound;
    System.out.println("Error" + error);
    power = ((kP*error)+(kD*(error-old_error)));
    Robot.m_intake.redlineTest(power);
    System.out.println("Suruyorum : "+ power);

    if(power>1){
      power=1;
  }
  else if(power<-1)
  {
      power=-1;
  }

  old_error = error;

  if(currentRound < targetRound+accuracy && currentRound > targetRound-accuracy) 
		{
			trueFlag++;
		}
		else
		{
      trueFlag = 0;
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(trueFlag>=5){
      return true;}
    else{return false;}
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_intake.redlineTest(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
