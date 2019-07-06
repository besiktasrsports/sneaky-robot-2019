/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utils;



import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class POVConverter {

    private enum ConvertedValue {
        UP,
        DOWN,
        RIGHT,
        LEFT,
        RIGHTUP,
        RIGHTDOWN,
        LEFTUP,
        LEFTDOWN,
        NEUTRAL
    }

    

    public POVConverter(){

        


    }

    public ConvertedValue convertToButton(String joystick){
        int rawData = Robot.m_oi.xbox.getPOV();
        ConvertedValue convertedValue;
        convertedValue = ConvertedValue.NEUTRAL;

        if(joystick == "xbox"){
            rawData = Robot.m_oi.xbox.getPOV();
        }
        else if (joystick == "logitech"){
            rawData = Robot.m_oi.logitech.getPOV();
        }

        //(rawData == 0){
        //    convertedValue = ConvertedValue.UP;
       // }
        if (rawData == 45){
            convertedValue = ConvertedValue.RIGHTUP;
        }
        else if (rawData == 90){
            convertedValue = ConvertedValue.RIGHT;
        }
        else if (rawData == 135){
            convertedValue = ConvertedValue.RIGHTDOWN;
        }
        else if (rawData == 180){
            convertedValue = ConvertedValue.DOWN;
        }
        else if (rawData == 225){
            convertedValue = ConvertedValue.LEFTDOWN;
        }
        else if (rawData == 270){
            convertedValue = ConvertedValue.LEFT;
        }
        else if (rawData == 315){
            convertedValue = ConvertedValue.LEFTUP;
        }

        return convertedValue;

    }

}
