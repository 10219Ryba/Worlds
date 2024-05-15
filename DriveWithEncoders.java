// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;

public class DriveWithEncoders extends Command {

  DriveSubsystem driveSubsystem;
  double distance;

  public DriveWithEncoders(DriveSubsystem driveSubsystem, double distance) {
    addRequirements(driveSubsystem);
    this.driveSubsystem = driveSubsystem;
    this.distance = distance;
  }

  @Override
  public void initialize() {
    driveSubsystem.resetEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (driveSubsystem.getAverageEncoderDistance() < distance) {
      driveSubsystem.arcadeDrive(.5, 0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveSubsystem.driveOff();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (driveSubsystem.getAverageEncoderDistance() >= distance) {
      return true;
    }
    return false;
  }
}
