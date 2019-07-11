package frc.robot.sensors;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import java.lang.Math;

import frc.robot.Robot;

public class Encoder {
    //public static double encoderValue;
    //public static double encoderRound;
    //public static double encoderPosition;
    //public static double encoderZeroValue;

    public Encoder() {
        /*
        ***************************
        *   CONFIGURING ENCODERS  *
        ***************************
        */    
      //  Robot.m_driveTrain.driveTrainFrontLeftMotor.setStatusFramePeriod(StatusFrame.Status_12_Feedback1, 1);
        Robot.m_intake.redlineTestMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
        //Robot.m_driveTrain.driveTrainFrontRightMotor.setStatusFramePeriod(StatusFrame.Status_12_Feedback1, 1);
        //Robot.m_driveTrain.driveTrainFrontRightMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        //Robot.m_driveTrain.driveTrainFrontRightMotor.setSelectedSensorPosition(0);
         

    }
    public static double getLeftEncoderRound(){
        int leftEncoderRaw;
        leftEncoderRaw = Robot.m_intake.redlineTestMotor.getSelectedSensorPosition();
        double roundNumber = leftEncoderRaw/4096;
        return roundNumber;
        

    }
    public static void printRightEncoderPosition() {
        double rightEncoderPosition;
        rightEncoderPosition = Robot.m_driveTrain.driveTrainFrontRightMotor.getSelectedSensorPosition();
        System.out.println("Right Encoder Position   : " + rightEncoderPosition);
        System.out.println("Right Round Number : " + rightEncoderPosition/4100);     
    }

    /*public static void zeroEncoder() {
        encoderValue = (Robot.m_drivetrain.exampleLeftTalon.getSelectedSensorPosition()+ Robot.m_drivetrain.exampleRightTalon.getSelectedSensorPosition())/2;
        encoderZeroValue = encoderPosition;
        
    }*/
    
    public static void printEncoderVelocity(){
       // double rightVelocity = Robot.m_driveTrain.driveTrainFrontRightMotor.getSelectedSensorVelocity(); 
        double leftVelocity = Robot.m_intake.redlineTestMotor.getSelectedSensorVelocity();


     //   System.out.println("Right Encoder Velocity is : "+rightVelocity);
       // System.out.println("Left Encoder RPM : "+leftVelocity/4096);

    }


}