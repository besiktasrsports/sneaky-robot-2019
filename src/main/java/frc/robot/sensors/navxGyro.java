/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
/**
 * Add your docs here.
 */
public class navxGyro {
public static AHRS ahrs;
public static double yawValue;
public static double pitchValue;
public static double rollValue;


public navxGyro(){
      
    ahrs = new AHRS(SPI.Port.kMXP);
    
    }

public double getYawValue(){

    yawValue = ahrs.getYaw();
    
    return yawValue;
    }


public double getPitchValue(){
    pitchValue = ahrs.getPitch();
    return pitchValue;
}
public double getRollValue(){
    rollValue = ahrs.getPitch();
    return rollValue;
}
}


