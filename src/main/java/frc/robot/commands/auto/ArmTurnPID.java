/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ArmTurnPID extends Command {

  double currentAngle, targetAngle;
  double accuracy = 3; // x Degree accuracy
  double currentError, errorDiff;
  double prevError = 0;
  double totalError = 0;
  double kP = 0.009;
  double kI = 0;// 0.00003;
  double kD = 0.08;
  double kPowerChange = 0.05;
  double powerAccuracy ;
  double targetPower;
  double power = 0;
  int trueFlag; 

  public ArmTurnPID(double _targetAngle) {
    requires(Robot.m_arm);
    this.targetAngle = _targetAngle;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() { 
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    currentAngle = Robot.m_arm.readArmEncoder();
    // targetAngle = Robot.m_arm.targetAngle;
    currentError = currentAngle - targetAngle;

    // Reset kI if targetAngleChange

    // Cumulative error
    totalError += currentError;
    // Difference in error
    errorDiff = currentError - prevError;

    // Error Calculation
    if (currentError < 0) 
		{
			targetPower = kP*currentError + kD*errorDiff + kI*totalError;	
		}
		else 
		{
			targetPower =  kP*currentError + kD*errorDiff + kI*totalError;
    }
    /*
    // Increment or decrement power slowly
    if(power < targetPower)
    {
      power += kPowerChange;
    }
    else
    {
      power -= kPowerChange;
    }
    */
    power = targetPower;

    if(targetAngle > 150)
    {
      power += 0.08;
    }
    else if(targetAngle > 100)
    {
      power += 0.025;
    }
    else
    {
      power += 0.025;
    }

    // Apply power to the motor
    Robot.m_arm.rotateArm(power);
    System.out.println("Arm power to apply: " + power);
    // Get previous error
    prevError = currentError;

    // Check if we are stable
    /*
    if(currentAngle < targetAngle + accuracy && currentAngle > targetAngle -accuracy) 
    {
      trueFlag++;
    }
    else 
    {
      trueFlag = 0;
    }
    */
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    /*
    if(trueFlag >= 5)
    {
      return true;
    }
    else
    {
      return false;
    }
    */
    return false; // aka never end
  }
  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_arm.stopArm();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.m_arm.stopArm();
  }
}
