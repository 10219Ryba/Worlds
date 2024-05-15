// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;

public class AutoDrive extends Command {
  Timer autoTimer = new Timer();
  DriveSubsystem driveSubsystem;
  double direction;

  public AutoDrive(DriveSubsystem driveSubsystem, double direction) {
    addRequirements(driveSubsystem);
    this.driveSubsystem = driveSubsystem;
    this.direction = direction;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    autoTimer.reset();
    autoTimer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveSubsystem.arcadeDrive(0, 1*direction);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveSubsystem.driveOff();
    autoTimer.reset();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (autoTimer.hasElapsed(2)) {
      return true;
    }
    return false;
  }
}
