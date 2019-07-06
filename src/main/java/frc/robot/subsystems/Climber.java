/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;

//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.commands.lift;

public class Climber extends Subsystem {

  // Define variables and actuators here as private:
   private SpeedController rearlegExtender;
   public  SpeedController RightFrontLegExtenderMotor;
   private SpeedController FrontLegMotor;
   private SpeedController rearArmExtenderMotor;
   private SpeedController LeftFrontLegExtenderMotor;
  public boolean lockFlag = false;
  public boolean commandFlag = false;

  public Climber() {
    // Construct objects here
    
    LeftFrontLegExtenderMotor = new Spark(14);
    LeftFrontLegExtenderMotor.setInverted(false);
    RightFrontLegExtenderMotor = new Spark(12);
    RightFrontLegExtenderMotor.setInverted(false);
    FrontLegMotor = new Spark(15);
    rearArmExtenderMotor = new Spark(11);

  }

  @Override
  public void initDefaultCommand() {
  //  Robot.m_climber.setDefaultCommand(new lift());
  }

  @Override
  public void periodic() {
    // Put code here to be run every loop
    System.out.println(lockFlag);

  }

  public void extendLeg() {
    // Extends the leg of climbing mechanism
    LeftFrontLegExtenderMotor.set(0.1);
    RightFrontLegExtenderMotor.set(0.1);
  }

  public void retractLeg() {
    // Extends the leg of climbing mechanism
    LeftFrontLegExtenderMotor.set(-0.1);
    RightFrontLegExtenderMotor.set(-0.1);
  }

  public void stopLeg() {
    LeftFrontLegExtenderMotor.set(0);
    RightFrontLegExtenderMotor.set(0);
  }

  public void lifter(double speed) {
    double lockSpeed = 0;
    if(lockFlag == true){
      lockSpeed = 0.25;
      commandFlag = false;
      LeftFrontLegExtenderMotor.set(speed+lockSpeed);
      RightFrontLegExtenderMotor.set(speed+lockSpeed); 
      commandFlag = true;
    }
    else{
     lockSpeed = 0;
      commandFlag = false;
      LeftFrontLegExtenderMotor.set(speed);
      RightFrontLegExtenderMotor.set(speed); 
      commandFlag = true;
    }

       
  }

  public void liftWheelForward() {
    FrontLegMotor.set(0.5);
  }

  public void liftWheelBackward() {
    FrontLegMotor.set(-0.5);
  }

  public void liftWheelStop() {
    FrontLegMotor.set(0);
  }

  public void extendArm() {
    rearArmExtenderMotor.set(0.5);
  }

  public void retractArm() {
    rearArmExtenderMotor.set(-0.5);
  }

  public void stopArm() {
    rearArmExtenderMotor.set(0);
  }

}