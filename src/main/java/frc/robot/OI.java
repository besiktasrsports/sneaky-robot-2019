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
import frc.robot.commands.auto.ArmTurnPID;
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
    climberPushButton = new JoystickButton(xbox, 7);
    climberReleaseButton = new JoystickButton(xbox, 8);
    toggleCompressorButton = new JoystickButton(xbox, 10);
    driveTrainModeChangerButton = new JoystickButton(xbox, 11);
    cameraServoTestButton = new JoystickButton(xbox, 1);
    
    // Button Panel - Arm
    frontFloorCargoIntakeButton = new JoystickButton(buttonPanel, 1);
    frontHatchButton = new JoystickButton(buttonPanel, 2);
    frontRocketCargoButton = new JoystickButton(buttonPanel, 3);
    frontCargoToCargoShipButton = new JoystickButton(buttonPanel, 4);
    armToStartPositionButton = new JoystickButton(buttonPanel, 5);
    rearCargoToCargoShipButton = new JoystickButton(buttonPanel, 6);
    rearRocketCargoButton = new JoystickButton(buttonPanel, 7);
    rearHatchButton = new JoystickButton(buttonPanel, 8);
    controlArmJs = new JoystickButton(buttonPanel, 9);
    // Button Panel - Intake and Climb
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
    cameraServoTestButton.whileHeld(new TurnServo());
    /*
    // Arm - Front
    armToStartPositionButton.whileHeld(new ArmTurnPID(96.0));
    frontCargoToCargoShipButton.whileHeld(new ArmTurnPID(120.0));
    frontHatchButton.whileHeld(new ArmTurnPID(188.0));
    frontFloorCargoIntakeButton.whileHeld(new ArmTurnPID(214.0));
    frontRocketCargoButton.whileHeld(new ArmTurnPID(170.0));
    // Arm - Rear
    rearCargoToCargoShipButton.whileHeld(new ArmTurnPID(60.0));
    rearRocketCargoButton.whileHeld(new ArmTurnPID(10.0));
    rearHatchButton.whileHeld(new ArmTurnPID(0.0));
    */
    armToStartPositionButton.whileHeld(new ChangeArmTargetAngle(96.0));
    frontCargoToCargoShipButton.whileHeld(new ChangeArmTargetAngle(120.0));
    frontHatchButton.whileHeld(new ChangeArmTargetAngle(195.0));
    frontFloorCargoIntakeButton.whileHeld(new ChangeArmTargetAngle(210.0));
    frontRocketCargoButton.whileHeld(new ChangeArmTargetAngle(170.0));
    // Arm - Rear
    rearCargoToCargoShipButton.whileHeld(new ChangeArmTargetAngle(60.0));
    rearRocketCargoButton.whileHeld(new ChangeArmTargetAngle(10.0));
    rearHatchButton.whileHeld(new ChangeArmTargetAngle(0.0));
    controlArmJs.whileHeld(new ChangeArmTargetAngle(500.0));
  }

  public Joystick getXbox() {
    return xbox;
  }

  public Joystick getButtonPanel() {
    return buttonPanel;
  }
}

























































































































  

  
