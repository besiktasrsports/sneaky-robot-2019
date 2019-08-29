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
import frc.robot.commands.auto.ArmTurnPID;

/**
 * Add your docs here.
 */
public class Arm extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public String armState;
  private final WPI_TalonSRX armMotor;


  public Arm()
  {
    // Assign ID
    armMotor = new WPI_TalonSRX(11);
    // Assign Encoder to Motor
    armMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new ArmTurnPID());
  }

  // Return Angle
  public double readArmEncoder()
  {
    System.out.println("Arm position:" + armMotor.getSelectedSensorPosition());
    return(armMotor.getSelectedSensorPosition());
  }

  public void rotateArmForward()
  {
    armMotor.set(0.7);
  }

  public void rotateArmBackward()
  {
    armMotor.set(-0.7);
  }

  public void rotateArm(double speed){
    armMotor.set(speed);
  }

  public void stopArm()
  {
    armMotor.set(0);
  }

}
