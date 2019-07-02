/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.utils.Vision;

public class ForTurnPIDTest extends CommandGroup {
  /**
   * Add your docs here.
   */

  public ForTurnPIDTest() {
    this.addSequential(new zeroYaw());
    this.addSequential(new realAutonomousTurnPID(5));
    //this.addSequential(new realAutonomousTurnPID(Robot.m_navx.yawValue()+90));
  //  if(Vision.buttonFlag == true) {
   //   this.addSequential(new AutonomousTurnPID(Robot.m_navx.yawValue()+Vision.angle.getDouble(0)));
   // }
    
  }
}
