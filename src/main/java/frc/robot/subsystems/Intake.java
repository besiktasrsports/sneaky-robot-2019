/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;


/**
 * Add your docs here.
 */
public class Intake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public String intakeState;
  public boolean intakeStateChangeBoolean = false;
  private final WPI_TalonSRX leftIntakeMotor;
  private final WPI_TalonSRX rightIntakeMotor;
  private final DoubleSolenoid stateChangeCyclinder;
  public final Compressor compressor;

  public Intake(){

    leftIntakeMotor = new WPI_TalonSRX(16);
    rightIntakeMotor = new WPI_TalonSRX(17);
    compressor = new Compressor(0);
    stateChangeCyclinder = new DoubleSolenoid(1,2);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
   
  }

  public void intakeStateChanger(String _state){

    this.intakeState = _state;

    if(intakeState == "HATCH"){

      stateChangeCyclinder.set(Value.kForward);

    }
    else if(intakeState == "CARGO"){
      stateChangeCyclinder.set(Value.kReverse);

    }
    else if(intakeState == "FREE"){
      stateChangeCyclinder.set(Value.kOff);
      
    }
    else{
      stateChangeCyclinder.set(Value.kOff);
      System.err.println("You have entered an undefined state.");
    }

  }

  public void intakeDrive(double speed){

    if(intakeState == "HATCH"){

      leftIntakeMotor.set(speed);
      rightIntakeMotor.set(-speed);

    }
    else if(intakeState == "CARGO"){
      leftIntakeMotor.set(-speed);
      rightIntakeMotor.set(speed);

    }
    else if(intakeState == "FREE"){
      leftIntakeMotor.set(0*speed);
      rightIntakeMotor.set(0*speed);
      
    }
    else{
      leftIntakeMotor.set(0*speed);
      rightIntakeMotor.set(0*speed);
      System.err.println("You have entered an undefined state.");
    }
  }

  
}
