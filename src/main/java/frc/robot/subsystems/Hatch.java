/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;

public class Hatch extends Subsystem {

  // Define variables and actuators here as private:
  // private Compressor compressor;

  private Compressor compressor;
  private DoubleSolenoid hmExtenderDB; // stands for " Hatch Mechanism Extender Double Solenoid "
  private DoubleSolenoid hatchGripperDB; // stands for " Hatch Gripper Double Solenoid "
  NetworkTableInstance inst = NetworkTableInstance.getDefault();
  NetworkTable table = inst.getTable("datatable");
  
  public static int pt;
  public static boolean ps;

  public Hatch() {
    // Construct objects here
    // compressor = new Compressor(0);

    compressor = new Compressor(0);
    addChild("Compressor",compressor);
    
    hmExtenderDB = new DoubleSolenoid(0, 0, 1);
    hatchGripperDB = new DoubleSolenoid(0, 2, 3);
    addChild("Double Solenoid 1",hmExtenderDB);

    
    

  }
  
  @Override
  public void initDefaultCommand() {

  }

  // All subsytem functions are defined here
  
  // Opens the compressor
  public void openCompressor() {
    compressor.setClosedLoopControl(true);
  }

  // Closes the compressor
  public void closeCompressor() {
    compressor.setClosedLoopControl(false);
  }

  // Emypties the cylinders
  public void freeHM() { 
    hmExtenderDB.set(DoubleSolenoid.Value.kOff);
    hatchGripperDB.set(DoubleSolenoid.Value.kOff);
    ps = false;
  
  }

  // Opens the cylinders
  public void extendHM() {
    hmExtenderDB.set(DoubleSolenoid.Value.kForward);
    pt = pt + 1;
    ps = true;
    
  }

  // Closes the cylinders
  public void retractHM() {
    hmExtenderDB.set(DoubleSolenoid.Value.kReverse);
    ps = false;
    
  }

  public void holdHatch(){
    hatchGripperDB.set(DoubleSolenoid.Value.kForward);
    
  }
  
  public void releaseHatch(){

    hatchGripperDB.set(DoubleSolenoid.Value.kReverse);
    

  }
}
