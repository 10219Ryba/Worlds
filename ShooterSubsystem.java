// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.DriveConstants.ShooterConstants;

public class ShooterSubsystem extends SubsystemBase {
  /** Creates a new ShooterSubsystem. */
  public final TalonFX smOne, smTwo;


  public ShooterSubsystem() {
    smOne = new TalonFX(ShooterConstants.shootOne);
    smTwo = new TalonFX(ShooterConstants.shootTwo);
    

  }
  public void intake() {
    smOne.set(-1);
    smTwo.set(-1);
  }
  public void shoot() {
    smOne.set(1);
  }
  public void prepShot() {
    smTwo.set(1);
  }
  public void shootOff() {
    smOne.set(0);
    smTwo.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}