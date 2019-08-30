/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.JoystickRotateArm;
import frc.robot.commands.auto.ArmTurnPID;

/**
 * Add your docs here.
 */
public class Arm extends Subsystem {

  public String armState;
  // We can also increment the targetAngle slowly as well
  public double targetAngle = 90.0;
  private final WPI_TalonSRX armMotor;
  private final double kEncoderPositionToAngle = 4388.0; // 4260
  private final double kEncoderPositionAt0 = 387963.0;


  public Arm()
  {
    // Assign ID
    armMotor = new WPI_TalonSRX(11);
    // Invert arm motor
    // armMotor.setInverted(true);
    // Assign Encoder to Motor
    armMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

  }

  @Override
  public void initDefaultCommand() {
    // setDefaultCommand(new ArmTurnPID());
    setDefaultCommand(new JoystickRotateArm());
  }

  // Return Angle
  public double readArmEncoder()
  {
    double rawArmPosition = armMotor.getSelectedSensorPosition();
    System.out.println("Arm position:" + rawArmPosition);

    double armPosition = (kEncoderPositionAt0 - rawArmPosition);
    double armAngle = armPosition/kEncoderPositionToAngle;
    // System.out.println("Arm position:" + rawArmPosition);
    System.out.println("Arm angle:" + armAngle);
    return armAngle;
  }

  public void rotateArm(double speed){
    armMotor.set(speed);
  }

  public void stopArm()
  {
    armMotor.set(0);
  }

}
