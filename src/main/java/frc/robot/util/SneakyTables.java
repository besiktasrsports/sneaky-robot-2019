/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.util;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * Add your docs here.
 */
public class SneakyTables {

    NetworkTableInstance instance = NetworkTableInstance.getDefault();
    NetworkTable visionTable = instance.getTable("visiontable");
    NetworkTableEntry visionAngleToTurnEntry;

    public SneakyTables() {

        visionAngleToTurnEntry = visionTable.getEntry("angle");

    }

    public double getVisionAngleToTurn() {
        return visionAngleToTurnEntry.getNumber(0.0).doubleValue();
    }
}
