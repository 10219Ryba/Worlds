// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;
import com.ctre.phoenix6.hardware.TalonFX;
import frc.robot.Constants.DriveConstants.PID;

public class DriveSubsystem extends SubsystemBase {
    

    // Right Motors
    private final CANSparkMax rRM = new CANSparkMax(DriveConstants.rR, MotorType.kBrushed);
    private final CANSparkMax rFM = new CANSparkMax(DriveConstants.rF, MotorType.kBrushed); 

    // Left Motors 
    private final CANSparkMax lRM = new CANSparkMax(DriveConstants.lR, MotorType.kBrushed);
    private final CANSparkMax lFM = new CANSparkMax(DriveConstants.lF, MotorType.kBrushed);

    // Drive
    private final DifferentialDrive drive = new DifferentialDrive(lRM::set, rRM::set); // Creates Leaders

    // Left Encoder
    private final Encoder leftEncoder = new Encoder(
      DriveConstants.LeftEncoderPorts[0],
      DriveConstants.LeftEncoderPorts[1],
      DriveConstants.LeftEncoderReversed
    );

    // Right Encoder
    private final Encoder rightEncoder = new Encoder(
      DriveConstants.RightEncoderPorts[0],
      DriveConstants.RightEncoderPorts[1],
      DriveConstants.RightEncoderReversed
    );

    // Gyro
    private final AHRS gyro = new AHRS(SPI.Port.kMXP);


  public DriveSubsystem() {



    // Registers Sensors For Use On Dashboards
    SendableRegistry.addChild(drive, rRM);
    SendableRegistry.addChild(drive, lRM);
    
    // Motors Follow Leaders
    rFM.follow(rRM);
    lFM.follow(lRM);

    // Inverts One Motor
    rRM.setInverted(true);

    //Encoders Distance Per Pulse
    leftEncoder.setDistancePerPulse(DriveConstants.EncoderDistancePerPulse);
    rightEncoder.setDistancePerPulse(DriveConstants.EncoderDistancePerPulse);
  }
  
  // Lets Drive With Arcade Controls
  public void arcadeDrive(double throttle, double rotation) {
    drive.arcadeDrive(throttle, rotation);
  }

  ///////////START/////////////OF//////////////ENCODERS/////////////////////////
  // Resets Encoder Values to Zero                                            //
  public void resetEncoders() {                                               //
    leftEncoder.reset();                                                      //
    rightEncoder.reset();                                                     //
  }                                                                           //
                                                                              //
  // Gets Average distance of the two encoders                                //
  public double getAverageEncoderDistance() {                                 //
    return (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2.0;    //
  }                                                                           //
                                                                              //
  // Gets Left Encoder                                                        //
  public Encoder getLeftEncoder() {                                           //
    return leftEncoder;                                                       //
  }                                                                           //
                                                                              //
  // Gets Right Encoder                                                       //
  public Encoder getRightEncoder() {                                          //
    return rightEncoder;                                                      //
  }                                                                           //
  ///////////////END//////////////OF///////////ENCODERS/////////////////////////

  ////////START/////////////////OF///////////////////GYRO///////////////////////////////////////////////
  // Zeroes the heading of the robot                                                                  //
  public void zeroHeading() {                                                                         //
    gyro.reset();                                                                                     //
  }                                                                                                   //
                                                                                                      //
  // Returns the heading of the robot                                                                 //
  public double getHeading() {                                                                        //
    return Math.IEEEremainder(gyro.getAngle(), 360) * (DriveConstants.gyroReversed ? -1.0 : 1.0);  //
  }                                                                                                   //
                                                                                                      //
  // Returns turn rate of robot                                                                       //
  public double getTurnRate() {                                                                       //
    return gyro.getRate() * (DriveConstants.gyroReversed ? -1.0 : 1.0);                               //
  }                                                                                                   //
  public void outputValues() {                                                                        //
                                  
  }                                                                                                   //
//////////END////////////////////////OF/////////////////////GYRO////////////////////////////////////////
  
  
  
  @Override
  public void periodic() {
    SmartDashboard.putNumber("GyroYawAngle", gyro.getYaw());                                      //
    SmartDashboard.putNumber("GyroRollAngle", gyro.getRoll()); 
  }

}