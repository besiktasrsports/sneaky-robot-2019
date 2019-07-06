/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.sensors;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrame;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.lang.Math;

import frc.robot.Robot;
import frc.robot.utils.Vision;

public class Encoder {
    public static double Left_Encoder_Position;
    public static double targetPosition = 500;
    public static double Right_Encoder_Position;
    public static double Perimeter = 48;
    // public static double RoundNumber = targetPosition/Perimeter;
    public static double RoundNumber = 0;
    public static double encoderValue;
    public static double encoderRound;
    public static double encoderPosition;
    public static double encoderZeroValue;
    public  double x=27;

    public Encoder() {
       /* Robot.m_climber.LeftFrontLegExtenderMotor.setStatusFramePeriod(StatusFrame.Status_12_Feedback1, 1);
        Robot.m_climber.LeftFrontLegExtenderMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        Robot.m_climber.LeftFrontLegExtenderMotor.setSelectedSensorPosition(0);
        
        Robot.m_driveTrain.driveTrainLeftRearMotor.setStatusFramePeriod(StatusFrame.Status_12_Feedback1, 1);
        Robot.m_driveTrain.driveTrainLeftRearMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        Robot.m_driveTrain.driveTrainLeftRearMotor.setSelectedSensorPosition(0);*/
         

    }
    public static void Left_Encoder_Position(){
        /*Left_Encoder_Position = Robot.m_driveTrain.driveTrainLeftRearMotor.getSelectedSensorPosition();
        
        System.out.println("Left Encoder Position   : " + Left_Encoder_Position);
        System.out.println("Left Round Number : " + Left_Encoder_Position/4100);
        /*
        if (Left_Encoder_Position <= RoundNumber*4100 ){
          
            Robot.m_driveTrain.driveTrainLeftFrontMotor.set(0.3);
            Robot.m_driveTrain.driveTrainLeftFrontMotor.set(0.3);
        }
        else {
            Robot.m_driveTrain.driveTrainLeftFrontMotor.set(0);
            Robot.m_driveTrain.driveTrainLeftFrontMotor.set(0);
        }
        
        

    }
    public static void Right_Encoder_Position() {
        /*
        Right_Encoder_Position = Robot.m_climber.LeftFrontLegExtenderMotor.getSelectedSensorPosition();
        System.out.println("Right Encoder Position   : " + Right_Encoder_Position);
        System.out.println("Right Round Number : " + Right_Encoder_Position/4100);    
        /*
        if (Right_Encoder_Position <= RoundNumber*4100 ){
          
            Robot.m_driveTrain.driveTrainRightFrontMotor.set(0.3);
            Robot.m_driveTrain.driveTrainRightFrontMotor.set(0.3);
        }
        else {
            Robot.m_driveTrain.driveTrainRightFrontMotor.set(0);
            Robot.m_driveTrain.driveTrainRightFrontMotor.set(0);
        }
        */
    }

    public  double getEncoderPosition() {
      /*  encoderValue = (Robot.m_climber.LeftFrontLegExtenderMotor.getSelectedSensorPosition()+ Robot.m_driveTrain.driveTrainLeftRearMotor.getSelectedSensorPosition())/2;
        encoderRound = encoderValue/4100;
        encoderPosition = encoderRound*2*Math.PI*15.24;
        return encoderPosition-encoderZeroValue;*/
        
        return x;
    }

    public static void zeroEncoder() {
       /* encoderValue = (Robot.m_climber.LeftFrontLegExtenderMotor.getSelectedSensorPosition()+ Robot.m_driveTrain.driveTrainLeftRearMotor.getSelectedSensorPosition())/2;
        encoderRound = encoderValue/4100;
        encoderPosition = encoderRound*2*Math.PI*15.24;
        encoderZeroValue = encoderPosition;*/
    }
}