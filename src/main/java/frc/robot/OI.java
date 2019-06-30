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

public class OI {

  // Define joysticks and buttons as public
  public Joystick xbox;
  public Joystick logitech;
  // public JoystickButton xboxButton1;
  public JoystickButton backTurnButton;
  public JoystickButton forwardTurnButton;
  public JoystickButton leftTurnButton;
  public JoystickButton rightTurnButton;
  public JoystickButton hatchThrowButton;
  public JoystickButton compressorOpenButton;
  public JoystickButton compressorCloseButton;
  public JoystickButton visionTriggerButton;
  public JoystickButton cargoJointUpButton;
  public JoystickButton cargoJointDownButton;
  public JoystickButton cargoIntakeButton;
  public JoystickButton cargoThrowButton;
  public JoystickButton liftArmExtendButton;
  public JoystickButton liftArmRetractButton;
  public JoystickButton climberMoveForwardButton;
  public JoystickButton climberMoveBackwardButton;
  public JoystickButton redlineShifterButton;
  public JoystickButton liftLockButton;
  public JoystickButton extendHMButton;
  public JoystickButton retractHMButton;

  public OI() {

    xbox = new Joystick(0);
    logitech = new Joystick(1);
    // xboxButton1 = new JoystickButton(xbox, 1);
    // xboxButton1.whenPressed(new ReleaseHatch()); 10
    /*
    backTurnButton = new JoystickButton(xbox , 1); // Turn to 180
    forwardTurnButton = new JoystickButton(xbox , 4); // Turn to 0
    leftTurnButton = new JoystickButton(xbox , 3); // Turn to -90
    rightTurnButton = new JoystickButton(xbox , 2); // Turn to 90
    */
    backTurnButton = new JoystickButton(xbox , 1); // Extend Hatch Mechanism
    forwardTurnButton = new JoystickButton(xbox , 4); // Retract Hatch Mechanism
    hatchThrowButton = new JoystickButton(xbox , 6); // Hatch Throw
    compressorOpenButton = new JoystickButton(xbox , 7); // Open Compressor
    compressorCloseButton = new JoystickButton(xbox , 8); // Close Compressor
    visionTriggerButton = new JoystickButton(xbox , 5); // Trigger Vision 


    cargoJointUpButton = new JoystickButton(logitech , 2); // Cargo Joint Up
    cargoJointDownButton = new JoystickButton(logitech , 1); // Cargo Joint Down
    cargoIntakeButton = new JoystickButton(logitech , 3); // Cargo Intake
    cargoThrowButton = new JoystickButton(logitech , 4); // Cargo Throw
    liftArmRetractButton =  new JoystickButton(logitech , 7); // Retract Arm
    liftArmExtendButton = new JoystickButton(logitech , 8); // Extend Arm
    climberMoveForwardButton = new JoystickButton(logitech, 5); // Snowblower Forward
    climberMoveBackwardButton = new JoystickButton(logitech, 6); // Snowblower Backward
    redlineShifterButton = new JoystickButton(logitech , 9); // Redline Shifter
    liftLockButton = new JoystickButton(logitech, 10);

    backTurnButton.whenPressed(new extendHM());
    forwardTurnButton.whenPressed(new retractHM());
    //leftTurnButton.whenPressed(new realAutonomousTurnPID(270));
    //rightTurnButton.whenPressed(new realAutonomousTurnPID(90));
    hatchThrowButton.whenPressed(new takeHatch());
    compressorOpenButton.whenPressed(new openCompressor());
    compressorCloseButton.whenPressed(new closeCompressor());
    visionTriggerButton.whenPressed(new releaseHatch());

    cargoJointUpButton.whenPressed(new cargoJointUp());
    cargoJointDownButton.whenPressed(new cargoJointDown());
    cargoIntakeButton.whileHeld(new takeCargo());
    cargoThrowButton.whileHeld(new releaseCargo());
    liftArmRetractButton.whileHeld(new retractArm());
    liftArmExtendButton.whileHeld(new extendArm());
    climberMoveBackwardButton.whileHeld(new liftDriveBackward());
    climberMoveForwardButton.whileHeld(new liftDriveForward());
    liftLockButton.whenPressed(new liftLockTrigger());

  }

  public Joystick getXbox() {
    return xbox;
  }

  public Joystick getLogitech() {
    return logitech;
  }

}