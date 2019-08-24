/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.DriveTrainModeChanger;
import frc.robot.commands.IntakeConverter;
import frc.robot.commands.ReleaseObject;
import frc.robot.commands.TakeObject;
import frc.robot.commands.ToggleCompressor;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
 
  public Joystick xbox;
  public Joystick buttonPanel;
  private JoystickButton driveTrainModeChangerButton;
  private JoystickButton toggleCompressorButton;
  private JoystickButton takeObjectButton;
  private JoystickButton releaseObjectButton;
  private JoystickButton intakeStateChangerButton;
 
  public OI(){

    xbox = new Joystick(0);
    buttonPanel = new Joystick(1);
    driveTrainModeChangerButton = new JoystickButton(xbox, 1);
    toggleCompressorButton = new JoystickButton(xbox, 10);
    takeObjectButton = new JoystickButton(xbox, 2);
    releaseObjectButton = new JoystickButton(xbox, 3);
    intakeStateChangerButton = new JoystickButton(xbox, 4);


    
    driveTrainModeChangerButton.whileHeld(new DriveTrainModeChanger());
    toggleCompressorButton.whileHeld(new ToggleCompressor());
    takeObjectButton.whileHeld(new TakeObject());
    releaseObjectButton.whileHeld(new ReleaseObject());
    intakeStateChangerButton.whileHeld(new IntakeConverter());
  
  }

  public Joystick getXbox() {
    return xbox;
  }

  public Joystick getButtonPanel() {
    return buttonPanel;
  }
}

























































































































  

  
