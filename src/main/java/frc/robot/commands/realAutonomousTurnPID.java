/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class realAutonomousTurnPID extends Command {
    double angle, power;
		double yawAngle = Robot.m_navx.yawValue();
		// double kP =  0.009;
		double kP = 0.003;
		double kD = 0.008;
    double error = angle-yawAngle;
    double angleToTurn = error + Robot.m_navx.angleValue();
		double error_old = error;
		int true_flag = 0;
    double accuracy = 2;
    long startTime;
    int seconds;
		
	public realAutonomousTurnPID(double _angle) {
		requires(Robot.m_driveTrain);	
    this.angle = _angle;
    this.seconds = 3;
    seconds = 3;
	}
	
	protected void initialize() {
    double angleDiff = angle-Robot.m_navx.yawValue();
    if(angleDiff > 180)
    {
      angleDiff -= 360;
    }
    else if(angleDiff < -180 ) {
      angleDiff += 360;
    }
    angleToTurn = angleDiff+Robot.m_navx.angleValue();

		startTime = System.currentTimeMillis();
		/*
    System.out.print("Angle Diff: ");
    System.out.println(angleDiff);
    System.out.print("Current yaw: ");
    System.out.println(Robot.m_navx.yawValue());
    System.out.print("Commanded angle: ");
    System.out.println(angle);
		*/
  }
	protected void execute() {
		yawAngle = Robot.m_navx.angleValue();
		error = angleToTurn - yawAngle; // ERROR_MAX = 180, 90
    if(error < 0)
		{
		power = -0.2 + ((kP*error + (kD*(error-error_old))));
    }
		else {
			power = 0.2 + ((kP*error + (kD*(error-error_old))));			
		}
		if(power > 1) {
			power = 1;
		}
		else if(power < -1) {
			power = -1;
		}
		
    Robot.m_driveTrain.autonomousTurn(power);
    /*
    System.out.print("Angle To Turn: ");
		System.out.print(angleToTurn);    
    System.out.print("Measured: ");
		System.out.print(yawAngle);
		System.out.print("Error: ");
		System.out.print(error);
		System.out.print("Power: ");
		System.out.println(power);
		System.out.println("True Flag :    "+ true_flag);
		*/
		error_old = error;
		if(yawAngle < angleToTurn+2 && yawAngle > angleToTurn-2) 
		{
			true_flag++;
		}
		else
		{
			true_flag = 0;
		}
	}
	protected boolean isFinished() 
	{		
		// System.out.print("True flag: ");
		// System.out.println(true_flag);
		if(true_flag >= 5) 
		{
			return true || (System.currentTimeMillis() - startTime) > (int) (1000d * this.seconds);
		}
		else 
		{
			return false || (System.currentTimeMillis() - startTime) > (int) (1000d * this.seconds);
    }

		
	}
	protected void end() 
	{
		Robot.m_driveTrain.autonomousStop();
	}
}
	

	