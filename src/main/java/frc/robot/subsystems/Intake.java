/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.freeTurn;


/**
 * Add your docs here.
 */
public class Intake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public String intakeState;
  public boolean compressorState = false;
  public boolean intakeStateChangeBoolean = false;
  private final WPI_VictorSPX leftIntakeMotor;
  private final WPI_VictorSPX rightIntakeMotor;
  private final DoubleSolenoid stateChangeCyclinder;
  public DigitalInput intakeLimitSw;
  public Counter intakeLimitSwCounter;
  public boolean intakeLimitSwStatus;
  public final Compressor compressor;

  public Intake(){

    leftIntakeMotor = new WPI_VictorSPX(17);
    rightIntakeMotor = new WPI_VictorSPX(15);
    rightIntakeMotor.setInverted(true);
    compressor = new Compressor(0);
    stateChangeCyclinder = new DoubleSolenoid(6, 7);
    intakeLimitSw = new DigitalInput(7);
    intakeLimitSwCounter = new Counter(intakeLimitSw);

  }
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new freeTurn());
   
  }

  public void gripCargo(){
    stateChangeCyclinder.set(Value.kForward);
    leftIntakeMotor.set(0.1);
    rightIntakeMotor.set(-0.1);

  }

  public void closeGripper()
  {
    stateChangeCyclinder.set(Value.kForward);
  }

  public void openGripper()
  {
    stateChangeCyclinder.set(Value.kReverse);
  }

  public void intakeStateChanger(String _state){

    this.intakeState = _state;

    if(intakeState == "HATCH"){

      stateChangeCyclinder.set(Value.kReverse);

    }
    else if(intakeState == "CARGO"){
      stateChangeCyclinder.set(Value.kForward);

    }
    else if(intakeState == "FREE"){
      stateChangeCyclinder.set(Value.kOff);
      
    }
    else{
      stateChangeCyclinder.set(Value.kOff);
      // System.err.println("You have entered an undefined state.");
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
      double cargoHoldSpeed = 0.1;
      System.out.println(intakeLimitSw.get());
      leftIntakeMotor.set(-speed+cargoHoldSpeed);
      rightIntakeMotor.set(speed-cargoHoldSpeed);
      

    }
    else if(intakeState == "FREE"){
      leftIntakeMotor.set(0*speed);
      rightIntakeMotor.set(0*speed);
      
    }
    else{
      leftIntakeMotor.set(0*speed);
      rightIntakeMotor.set(0*speed);
      // System.err.println("You have entered an undefined state.");
    }
  }

  
}
