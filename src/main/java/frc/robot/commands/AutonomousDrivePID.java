package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 *
 */
public class AutonomousDrivePID extends Command {
      double power;
      double distance;
      double current_distance=0;
      double distance_error=distance-current_distance;
      double distance_oldError=distance_error;
      // double kP = 0.0014;
      // double kD = 0.04;
      double kP = 0.0002;
      double kD = 0.0007;
      double angle_power;
      double right_power;
      double left_power;
      int true_flag = 0;
      double accuracy = 2;
   
      public AutonomousDrivePID(double _distance ) 
      {
    	  requires(Robot.m_driveTrain);
    	  this.distance= _distance;
      }

    // Called just before this Command runs the first time
    protected void initialize() {
      Robot.encoder.zeroEncoder();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
      current_distance = 100;
    	

    	distance_error = distance-current_distance;
    			
	
		if (distance_error<0) 
		{
			power = -0.3 + (kP*distance_error+(kD*(distance_error-distance_oldError)));	
		}
		else 
		{
			power = 0.3 + (kP*distance_error+(kD*(distance_error-distance_oldError)));	
		}

    if(power>1) 
    {
      power=1;
    }
    else if(power<-1)
    {
        power=-1;
    }

		Robot.m_driveTrain.autonomousDrive(-power);
		/*
		System.out.println("Measured Distance:");
		System.out.print(current_distance+ "		");
		System.out.print("Error Distance");
		System.out.print(distance_error+ "		");
		System.out.print("Power :"); 
		System.out.print(power);
		System.out.println("True Flag:       "+ true_flag);
		*/
		     
		     
		     distance_oldError= distance_error;
	if(current_distance< distance+ accuracy && current_distance > distance-accuracy) 
	{
		// System.out.println("arizona kertenkelecik");
		true_flag++;
	}
	else 
	{
		true_flag=0;
	}	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (true_flag>=5) 
    	{
    		return true;
    	}
    	else 
    	{
    		return false;
    	}
    	
    	
}

    // Called once after isFinished returns true
    protected void end() {Robot.m_driveTrain.autonomousStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
      end();
    	
    }
}
