/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import frc.robot.Robot; 

public class NavX {
    public static AHRS ahrs;
    public static boolean zeroFlag;
    public static double angleZeroValue = 0;

    public NavX() {
        ahrs = new AHRS(SPI.Port.kMXP);
    }

    public static float yawValue() {
        float yawValue = ahrs.getYaw();
        Sendable mappedYaw; 
       
        return yawValue;
        
    }
    public static void zeroYaw() {
        zeroFlag = false;
        ahrs.zeroYaw();
        ahrs.reset();
        zeroFlag = true;
    }
    public static void zeroAngle() {
        angleZeroValue = ahrs.getAngle();
    }

    public static double angleValue() {
        return ahrs.getAngle()-angleZeroValue;
    }

}