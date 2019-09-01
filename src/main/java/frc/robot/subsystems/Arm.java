/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.JoystickRotateArm;
import frc.robot.commands.auto.ArmTurnPID;
import frc.robot.commands.auto.DefaultArmTurnPID;

/**
 * Add your docs here.
 */
public class Arm extends Subsystem {

  public String armState;
  // We can also increment the targetAngle slowly as well
  public double targetAngle = 10000.0; // We can make this its current angle as well
  private final WPI_TalonSRX  armMotor1;
  private final WPI_VictorSPX armMotor2;
  private SpeedControllerGroup armSpeedControllerGroup;
  private final double kEncoderPositionToAngle = 13164.0; // 13312
  private final double kEncoderPositionAt0 = -1188295.0;


  public Arm()
  {
    // Assign ID
    armMotor1 = new WPI_TalonSRX(11);
    armMotor2 = new WPI_VictorSPX(20);
    armSpeedControllerGroup = new SpeedControllerGroup(armMotor1, armMotor2);
    // Invert arm motor
    // armMotor.setInverted(true);
    // Assign Encoder to Motor
    armMotor1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DefaultArmTurnPID());
    // setDefaultCommand(new JoystickRotateArm());
  }

  // Return Angle
  public double readArmEncoder()
  {
    double rawArmPosition = armMotor1.getSelectedSensorPosition();
    System.out.println("Raw Arm position:" + rawArmPosition);
    // System.out.println("Arm position:" + rawArmPosition);
    double armPosition = (kEncoderPositionAt0 - rawArmPosition);
    // System.out.println("Arm position:" + armPosition);
    double armAngle = -armPosition/kEncoderPositionToAngle;
    System.out.println("Arm angle:" + armAngle);
    return armAngle;
  }

  public void rotateArm(double speed){
    armSpeedControllerGroup.set(speed);
  }

  public void stopArm()
  {
    armSpeedControllerGroup.set(0);
  }

}
