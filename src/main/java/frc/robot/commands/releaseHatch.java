package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;


public class releaseHatch extends Command {

    long startTime;
    int seconds;
    
    public releaseHatch() {

        requires(Robot.m_hatch);
        seconds = 3;

    }

    @Override

    protected void initialize() {

        startTime = System.currentTimeMillis();
    }


    @Override

    protected void execute() {

        Robot.m_hatch.releaseHatch();
        // System.out.println("I am opening the cylinders");
        System.out.println("releaseHatch");
        Robot.vision.holdStatus.setBoolean(false);
       
    }

    @Override

    protected boolean isFinished() {

        return (System.currentTimeMillis() - startTime) > (int) (1000d * this.seconds);
        
    }

    @Override

    protected void end() {

       Robot.m_hatch.freeHM();
        // Robot.m_hatch.turnOffCylinder();

    }

    @Override

    protected void interrupted() {

        end();

    }

}