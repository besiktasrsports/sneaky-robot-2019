/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Climber extends Subsystem {

  private DoubleSolenoid climbSolenoid;
  
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public Climber(){

    climbSolenoid = new DoubleSolenoid(4 ,5); //you might want to change this
   
    
    

  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void pushCyclinders(){
   
   climbSolenoid.set(Value.kForward);

  }

  public void retractCyclinders(){
    climbSolenoid.set(Value.kReverse);
  }

}

  

