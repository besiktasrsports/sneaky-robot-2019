/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DefaultArmTurnPID extends Command {

  double currentAngle, targetAngle;
  double accuracy = 3; // x Degree accuracy
  double currentError, errorDiff;
  double prevError = 0;
  double totalError = 0;
  double kP = 0.06;
  double kI = 0;// 0.00003;
  double kD = 0.16;
  double kPowerChange = 0.05;
  double powerAccuracy ;
  double targetPower;
  double power = 0;
  int trueFlag; 

  public DefaultArmTurnPID() {
    requires(Robot.m_arm);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() { 
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    currentAngle = Robot.m_arm.readArmEncoder();
    targetAngle = Robot.m_arm.targetAngle;

    if(targetAngle == 10000.0)  // Do Js control
    {
      Robot.m_arm.rotateArm(Robot.m_oi.xbox.getRawAxis(3));
    }
    else
    {
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

      power = targetPower;

      

      // Apply power to the motor
      Robot.m_arm.rotateArm(power);
      System.out.println("Arm power to apply: " + power);
      // Get previous error
      prevError = currentError;
    }
    if(Robot.m_arm.readArmEncoder() < 90)
    {
      Robot.m_cameraServo.setServoAngle(180);
    }
    else
    {
      Robot.m_cameraServo.setServoAngle(0);
    }

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {

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
