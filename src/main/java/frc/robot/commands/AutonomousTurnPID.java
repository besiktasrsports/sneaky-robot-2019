/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class AutonomousTurnPID extends Command{
    double measured_angle;
    double target_angle;
    double accuracy=1;
    double error;
    double old_error = error;
    double kP;
    double kI;
    double kD;
    double power;
    double true_flag;
    double total_error = 0;    
    double lowest_power = 0;

    public AutonomousTurnPID(double _angle) {
        requires(Robot.m_driveTrain);
        this.target_angle=_angle;
        
    }

protected void initialize() {
        /*
        System.out.print("Angle to turn:");
        System.out.println(target_angle);
        System.out.print("Angle measured:");
        System.out.println(Robot.vision.angle.getDouble(0));
        */
        power = 0;
        total_error = 0;
        if(target_angle >= -10 && target_angle <= 10){
            kP = 0.055;
            kI = 0.00025;
            kD = 0.0;
            }
        else if(target_angle > 10 && target_angle <= 30){
            kP = 0.013;
            kI = 0.0000;
            kD = 0.000;

        }
        else if(target_angle == -90 || target_angle == 90){
            kP = 0.025;
            kI = 0;
            kD = 0.015;
        }
        true_flag=0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {   

        measured_angle= Robot.m_navx.yawValue();
        System.out.println(measured_angle);    
            error = target_angle - measured_angle;
            total_error += error;
            power = ((kP*error)+(kI*total_error)+(kD*(error-old_error)));
            if(power < 0)
            {
                power -= lowest_power;
            }
            else {
                power += lowest_power;
            }
            /*
            System.out.println("Error  :"+" " + error);
            System.out.println("kP  :"+" " + kP);
            System.out.println("kI  :"+" " + kI);
            System.out.println("Total Error  :"+" " + total_error);
            
            */
            if(power>1){
                power=1;
            }
            else if(power<-1)
            {
                power=-1;
            }
            Robot.m_driveTrain.autonomousTurn(power);
            // System.out.println("Measured  :"+" " + measured_angle);
            // System.out.println("Error  :"+" " + error);
            // System.out.println("Power  :"+" " + power);
            // System.out.println("True Flag  :"+" " + true_flag);

            old_error = error;
            if(measured_angle < target_angle+accuracy && measured_angle > target_angle-accuracy) 
		{
			true_flag++;
		}
		else
		{
			true_flag = 0;
}
    }

    // Called once after isFinished returns true
    protected boolean isFinished(){
        if(true_flag>=5){
            return true;}
        else{return false;}
    }
    
    protected void end() {
        Robot.m_driveTrain.autonomousStop();
    }
    

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    }
    
}
	