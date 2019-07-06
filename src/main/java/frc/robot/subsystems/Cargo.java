/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;


public class Cargo extends Subsystem {

  // Define variables and actuators here as private:
  public SpeedController cargoJointMotor;
  private SpeedController leftCargoMotor;
  private SpeedController rightCargoMotor;
  private DigitalInput cargoLimitDown;
  private DigitalInput cargoLimitUp;
  public boolean hShifter;
  public boolean shifterFlag;
  private Counter upCounter;
  private Counter downCounter;
  public boolean upLimitSwStatus = false;
  public boolean downLimitSwStatus = false;
  NetworkTableInstance inst = NetworkTableInstance.getDefault();
  NetworkTable table = inst.getTable("datatable");
  public static NetworkTableEntry upSwitch;
  public static NetworkTableEntry downSwitch;
  
  public Cargo() {
    // Construct objects here
    leftCargoMotor  = new Spark(1);
    rightCargoMotor = new Spark(2);
    cargoJointMotor = new Spark(0);
    cargoLimitUp = new DigitalInput(0);
    cargoLimitDown = new DigitalInput(1);
    upCounter = new Counter(cargoLimitUp);
    downCounter = new Counter(cargoLimitDown);

    hShifter = false;

    
    upSwitch = table.getEntry("upSwitch");
    downSwitch = table.getEntry("downSwitch");

  }

  @Override
  public void initDefaultCommand() {

  }

  @Override
  public void periodic() {
   //  System.out.println("Up Limit is :"+cargoLimitUp.get());
   //  System.out.println("Down Limit is :"+cargoLimitDown.get());
    if(upCounter.get() != 0 ){
      upLimitSwStatus = true;
      upCounter.reset();
    }
    else{
      upLimitSwStatus = false;
      upCounter.reset();
    }

    if(downCounter.get() != 0){
      downLimitSwStatus = true;
      downCounter.reset();

    }
    else{
      downLimitSwStatus = false;
      downCounter.reset();
    }

  }

  // All subsytem functions are defined here

  public void setCargoPosition(double _angle) {
    // Moves the cargo to specific angle

  }

  public void releaseCargo() {
    // Releases the cargo
    if(hShifter == false){
    shifterFlag = false;
    leftCargoMotor.set(-0.43);
    rightCargoMotor.set(-0.43);
    shifterFlag = true;
    }
    else{
      shifterFlag = false;
     leftCargoMotor.set(-1);
      rightCargoMotor.set(-1);
      shifterFlag = true;
  }
  }

  public void takeCargo() {
    // Intakes the cargo
    leftCargoMotor.set(0.5);
    rightCargoMotor.set(0.5);
  }

  public void cargoStop(){
    leftCargoMotor.set(0);
    rightCargoMotor.set(0);
  }

  public void cargoJointStop() {
    cargoJointMotor.set(0);
  }
  
  public void cargoJointUp(){
   cargoJointMotor.set(1);
  }

  public void cargoJointDown() {
    cargoJointMotor.set(-0.6);
  }

}