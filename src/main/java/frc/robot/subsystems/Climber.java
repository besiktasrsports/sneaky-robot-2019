/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Climber extends Subsystem {

  private WPI_TalonSRX climbMotor;
  private WPI_TalonSRX climbMotor2;
  private DigitalInput climberLimitSw;
  public boolean limitSwStatus;
  public Counter climbCounter;
  
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public Climber(){

    climbMotor = new WPI_TalonSRX(10);
    climbMotor2 = new WPI_TalonSRX(18);
    climberLimitSw = new DigitalInput(9);
    climbCounter = new Counter(climberLimitSw);
    
    

  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void periodic(){

    

  }

  public void climbDrive(double speed){
   
    if(speed >= 0){
      if(limitSwStatus == true){
        climbMotor.set(0);
        climbMotor2.set(0);
      }
      else{
        climbMotor.set(speed);
        climbMotor2.set(-speed);
      }

    }
    else{
      climbMotor.set(speed);
      climbMotor2.set(-speed);
    }
   
    }

    
  }

  

