/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.*;


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
  private JoystickButton rotateArmForwardButton;
  private JoystickButton rotateArmBackwardButton;
  private JoystickButton climberPushButton;
  private JoystickButton climberReleaseButton;
  private JoystickButton climbMoveForwardButton;

 
  public OI(){

    xbox = new Joystick(0);
    buttonPanel = new Joystick(1);
    climberPushButton = new JoystickButton(xbox, 7);
    climberReleaseButton = new JoystickButton(xbox, 8);
    toggleCompressorButton = new JoystickButton(xbox, 10);
    driveTrainModeChangerButton = new JoystickButton(xbox, 11);
    takeObjectButton = new JoystickButton(buttonPanel, 10);
    releaseObjectButton = new JoystickButton(buttonPanel, 11);
    intakeStateChangerButton = new JoystickButton(buttonPanel, 12);
    rotateArmForwardButton  = new JoystickButton(buttonPanel, 13);
    rotateArmBackwardButton  = new JoystickButton(buttonPanel, 14);
    climbMoveForwardButton = new JoystickButton(buttonPanel, 15);



    
    //driveTrainModeChangerButton.whileHeld(new DriveTrainModeChanger());
    toggleCompressorButton.whileHeld(new ToggleCompressor());
    takeObjectButton.whileHeld(new TakeObject());
    releaseObjectButton.whileHeld(new ReleaseObject());
    intakeStateChangerButton.whileHeld(new IntakeConverter());
    rotateArmForwardButton.whileHeld(new armForward());
    rotateArmBackwardButton.whileHeld(new armBackward());
    climberPushButton.whileHeld(new climbPush());
    climberReleaseButton.whileHeld(new climbRelease());
    climbMoveForwardButton.whileHeld(new climbDriveForward());
  
  }

  public Joystick getXbox() {
    return xbox;
  }

  public Joystick getButtonPanel() {
    return buttonPanel;
  }
}

























































































































  

  
