package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
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
  public POVButton povTestButton;

  public OI() {

    xbox = new Joystick(0);
    logitech = new Joystick(1);
    // xboxButton1 = new JoystickButton(xbox, 1);
    // xboxButton1.whenPressed(new ReleaseHatch()); 10
    
    /*backTurnButton = new JoystickButton(xbox , 1); // Turn to 180
    forwardTurnButton = new JoystickButton(xbox , 4); // Turn to 0
    leftTurnButton = new JoystickButton(xbox , 3); // Turn to -90
    rightTurnButton = new JoystickButton(xbox , 2); // Turn to 90*/
    
    backTurnButton = new JoystickButton(xbox , 2); // Extend Hatch Mechanism
    forwardTurnButton = new JoystickButton(xbox , 4); // Retract Hatch Mechanism
    cargoIntakeButton = new JoystickButton(xbox , 6); // Hatch Throw
   cargoJointUpButton  = new JoystickButton(xbox , 7); // Open Compressor
    cargoJointDownButton = new JoystickButton(xbox , 8); // Close Compressor
    visionTriggerButton = new JoystickButton(xbox , 5); // Trigger Vision 


    compressorOpenButton = new JoystickButton(logitech , 2); // Cargo Joint Up
     compressorCloseButton = new JoystickButton(logitech , 1); // Cargo Joint Down
    hatchThrowButton = new JoystickButton(logitech , 3); // Cargo Intake
    cargoThrowButton = new JoystickButton(logitech , 4); // Cargo Throw
    liftArmRetractButton =  new JoystickButton(logitech , 7); // Retract Arm
    liftArmExtendButton = new JoystickButton(logitech , 8); // Extend Arm
    climberMoveForwardButton = new JoystickButton(logitech, 5); // Snowblower Forward
    climberMoveBackwardButton = new JoystickButton(logitech, 6); // Snowblower Backward
    redlineShifterButton = new JoystickButton(logitech , 9); // Redline Shifter
    liftLockButton = new JoystickButton(logitech, 10);
    povTestButton = new POVButton(logitech, 0);


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
    redlineShifterButton.whenPressed(new redlineShifter());
    liftLockButton.whenPressed(new liftLockTrigger());
    povTestButton.whenPressed(new delay(5));
  }

  public Joystick getXbox() {
    return xbox;
  }

  public Joystick getLogitech() {
    return logitech;
  }

}
