package frc.robot.commands;



import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;





public class openCompressor extends Command {

  boolean switchFlag = false;

  public openCompressor() {

  }

  @Override

  protected void initialize() {

  }


  @Override

  protected void execute() {

    Robot.m_hatch.openCompressor();
    switchFlag = true;

  }


  @Override

  protected boolean isFinished() {

    return switchFlag;

  }


  @Override

  protected void end() {

  }

  @Override

  protected void interrupted() {

  }

}