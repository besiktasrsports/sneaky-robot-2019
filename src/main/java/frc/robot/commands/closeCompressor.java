package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;



public class closeCompressor extends Command {

  boolean switchFlag = false;

  public closeCompressor() {


  }


  @Override

  protected void initialize() {

  }


  @Override

  protected void execute() {

    Robot.m_hatch.closeCompressor();
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