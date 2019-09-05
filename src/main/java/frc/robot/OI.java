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
import frc.robot.commands.auto.ChangeArmTargetAngle;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
 
  // Joysticks
  public Joystick xbox;
  public Joystick buttonPanel;
  // Drivetrains
  private JoystickButton driveTrainModeChangerButton;
  // Pneumatics
  private JoystickButton toggleCompressorButton;
  // Intake
  private JoystickButton takeObjectButton;
  private JoystickButton releaseObjectButton;
  private JoystickButton intakeStateChangerButton;
  private JoystickButton closeGripperButton;
  private JoystickButton openGripperButton;
  // Arm - Front
  private JoystickButton rotateArmForwardButton;
  private JoystickButton rotateArmBackwardButton;
  private JoystickButton armToStartPositionButton;
  private JoystickButton frontCargoToCargoShipButton;
  private JoystickButton frontHatchButton;
  private JoystickButton frontFloorCargoIntakeButton;
  private JoystickButton frontRocketCargoButton;
  // Arm - Rear
  private JoystickButton rearRocketCargoButton;
  private JoystickButton rearHatchButton;
  private JoystickButton rearCargoToCargoShipButton;
  private JoystickButton rearFloorCargoIntakeButton;
  private JoystickButton controlArmJs;
  // Climber
  private JoystickButton climberPushButton;
  private JoystickButton climberReleaseButton;
  private JoystickButton climbMoveForwardButton;
  // Camera
  private JoystickButton cameraServoTestButton;

 
  public OI(){

    xbox = new Joystick(0);
    buttonPanel = new Joystick(1);

    // Xbox
    climberPushButton = new JoystickButton(xbox, 5);
    climberReleaseButton = new JoystickButton(xbox, 6);
    toggleCompressorButton = new JoystickButton(xbox, 8);
    driveTrainModeChangerButton = new JoystickButton(xbox, 7);
    // cameraServoTestButton = new JoystickButton(xbox, 3);
    openGripperButton = new JoystickButton(xbox, 1);
    takeObjectButton = new JoystickButton(xbox, 2);
    releaseObjectButton = new JoystickButton(xbox, 4);
    closeGripperButton =  new JoystickButton(xbox, 3);
    
    
    // Button Panel - Arm
    frontFloorCargoIntakeButton = new JoystickButton(buttonPanel, 1);
    frontHatchButton = new JoystickButton(buttonPanel, 2);
    frontRocketCargoButton = new JoystickButton(buttonPanel, 3);
    frontCargoToCargoShipButton = new JoystickButton(buttonPanel, 4);
    armToStartPositionButton = new JoystickButton(buttonPanel, 5);
    rearCargoToCargoShipButton = new JoystickButton(buttonPanel, 6);
    rearRocketCargoButton = new JoystickButton(buttonPanel, 7);
    rearHatchButton = new JoystickButton(buttonPanel, 8);
    rearFloorCargoIntakeButton = new JoystickButton(buttonPanel, 9);
    controlArmJs = new JoystickButton(buttonPanel, 11);
    // Button Panel - Intake and Climb
    intakeStateChangerButton = new JoystickButton(buttonPanel, 10);
    rotateArmForwardButton  = new JoystickButton(buttonPanel, 13);
    rotateArmBackwardButton  = new JoystickButton(buttonPanel, 14);
    climbMoveForwardButton = new JoystickButton(buttonPanel, 15);

    driveTrainModeChangerButton.whileHeld(new DriveTrainModeChanger());
    toggleCompressorButton.whileHeld(new ToggleCompressor());
    takeObjectButton.whileHeld(new TakeObject());
    releaseObjectButton.whileHeld(new ReleaseObject());
    intakeStateChangerButton.whileHeld(new IntakeConverter());
    rotateArmForwardButton.whileHeld(new armForward());
    rotateArmBackwardButton.whileHeld(new armBackward());
    climberPushButton.whileHeld(new climbPush());
    climberReleaseButton.whileHeld(new climbRelease());
    climbMoveForwardButton.whileHeld(new climbDriveForward());
    // cameraServoTestButton.whileHeld(new TurnServo());
    closeGripperButton.whileHeld(new CloseGripper());
    openGripperButton.whileHeld(new OpenGripper());
    
    // Arm - Front
    armToStartPositionButton.whileHeld(new ChangeArmTargetAngle(96.0));
    frontCargoToCargoShipButton.whileHeld(new ChangeArmTargetAngle(133.0));
    frontHatchButton.whileHeld(new ChangeArmTargetAngle(183.0));
    frontFloorCargoIntakeButton.whileHeld(new ChangeArmTargetAngle(213.5));
    frontRocketCargoButton.whileHeld(new ChangeArmTargetAngle(168.0));
    // Arm - Rear
    rearCargoToCargoShipButton.whileHeld(new ChangeArmTargetAngle(60.0));
    rearRocketCargoButton.whileHeld(new ChangeArmTargetAngle(36.0));
    rearHatchButton.whileHeld(new ChangeArmTargetAngle(16.34));
    rearFloorCargoIntakeButton.whileHeld(new ChangeArmTargetAngle(-19.5));
    
    controlArmJs.whileHeld(new ChangeArmTargetAngle(10000.0));
  }

  public Joystick getXbox() {
    return xbox;
  }

  public Joystick getButtonPanel() {
    return buttonPanel;
  }
}
