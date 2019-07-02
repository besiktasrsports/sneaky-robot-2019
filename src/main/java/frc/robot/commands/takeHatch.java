package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class takeHatch extends Command {
   boolean flag = false;
    public takeHatch() {

        requires(Robot.m_hatch);

    }

    @Override

    protected void initialize() {
        
    }

    @Override

    protected void execute() {

        Robot.m_hatch.holdHatch();
        // System.out.println("I am closing the cylinders");
        System.out.println("holdHatch");
        Robot.vision.holdStatus.setBoolean(true);
        
        flag = true;
    }

    @Override

    protected boolean isFinished() {

        return flag;
    }

    @Override

    protected void end() {

        Robot.m_hatch.freeHM();

    }

    @Override

    protected void interrupted() {

        end();

    }

}