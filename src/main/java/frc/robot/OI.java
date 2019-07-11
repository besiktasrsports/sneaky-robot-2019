/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.RedlineTestForward;
import frc.robot.commands.RedlineTestReverse;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
 
  public Joystick xbox;
  public Joystick logitech;
  private JoystickButton redlineTestButton;
  private JoystickButton redlineTestButton2;

  public OI(){

    xbox = new Joystick(0);
    logitech = new Joystick(1);
    redlineTestButton = new JoystickButton(xbox,4);
    redlineTestButton2 = new JoystickButton(xbox,2);


    redlineTestButton.whenPressed(new RedlineTestForward());
    redlineTestButton2.whenPressed(new RedlineTestReverse());
  }

  public Joystick getXbox() {
    return xbox;
  }

  public Joystick getLogitech() {
    return logitech;
  }
}
