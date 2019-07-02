/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utils;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;

/**
 * Add your docs here.
 */
public class SoundTrigger {

    NetworkTableInstance inst = NetworkTableInstance.getDefault();
    NetworkTable table = inst.getTable("datatable");
    static NetworkTableEntry matchTime;
    static NetworkTableEntry tymeButton;

    public SoundTrigger(){

        matchTime = table.getEntry("tyme");
        tymeButton = table.getEntry("tymeButton");
        

        
    }
    public static void soundButton(){
        matchTime.setDouble(DriverStation.getInstance().getMatchTime());
        tymeButton.setBoolean(true);
    }

}
