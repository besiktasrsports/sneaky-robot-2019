package frc.robot.sensors;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrame;
// import java.lang.Math;

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
        Robot.m_driveTrain.driveTrainFrontLeftMotor.setStatusFramePeriod(StatusFrame.Status_12_Feedback1, 1);
        Robot.m_driveTrain.driveTrainFrontLeftMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        Robot.m_driveTrain.driveTrainFrontLeftMotor.setSelectedSensorPosition(0);
        
        Robot.m_driveTrain.driveTrainFrontRightMotor.setStatusFramePeriod(StatusFrame.Status_12_Feedback1, 1);
        Robot.m_driveTrain.driveTrainFrontRightMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        Robot.m_driveTrain.driveTrainFrontRightMotor.setSelectedSensorPosition(0);
         

    }
    public static int getLeftEncoderPosition(){
        int leftEncoderRaw;
        leftEncoderRaw = Robot.m_driveTrain.driveTrainFrontLeftMotor.getSelectedSensorPosition();
        
        // System.out.println("Left Encoder Raw   : " + leftEncoderRaw);
        // System.out.println("Left Round Number : " + leftEncoderRaw/4096); // Calculating round number 
        // 1024*4 comes from sensor's datasheet.
        
        // double leftEncoderDistance = 2*Math.PI*0.5*leftEncoderRaw/4096; // 2piR feet
        return leftEncoderRaw;

    }
    public static int getRightEncoderPosition() {
        int rightEncoderRaw;
        rightEncoderRaw = Robot.m_driveTrain.driveTrainFrontRightMotor.getSelectedSensorPosition();
        
        // System.out.println("Left Encoder Raw   : " + rightEncoderRaw);
        // System.out.println("Left Round Number : " + rightEncoderRaw/4096); // Calculating round number 
        // 1024*4 comes from sensor's datasheet.
        
        // double rightEncoderDistance = 2*Math.PI*0.5*rightEncoderRaw/4096; // 2piR feet

        return rightEncoderRaw;     
    }

    /*public static void zeroEncoder() {
        encoderValue = (Robot.m_drivetrain.exampleLeftTalon.getSelectedSensorPosition()+ Robot.m_drivetrain.exampleRightTalon.getSelectedSensorPosition())/2;
        encoderRound = encoderValue/4100;
        encoderPosition = encoderRound*Math.PI*15.24;
        encoderZeroValue = encoderPosition;
        
    }*/
    
    public static void printEncoderVelocity(){
        double rightVelocity = Robot.m_driveTrain.driveTrainFrontRightMotor.getSelectedSensorVelocity(); 
        double leftVelocity = Robot.m_driveTrain.driveTrainFrontLeftMotor.getSelectedSensorVelocity();


        System.out.println("Right Encoder Velocity is : "+rightVelocity);
        System.out.println("Left Encoder Velocity is : "+leftVelocity);

    }


}