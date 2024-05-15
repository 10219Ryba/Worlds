// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class IntakeShootCommand extends Command {
  IntakeSubsystem intakeSubsystem;
  ShooterSubsystem shooterSubsystem;
  Timer intakeTimer = new Timer();

  public IntakeShootCommand(IntakeSubsystem intakeSubsystem, ShooterSubsystem shooterSubsystem) {
    addRequirements(intakeSubsystem);
    addRequirements(shooterSubsystem);
    this.intakeSubsystem = intakeSubsystem;
    this.shooterSubsystem = shooterSubsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    intakeTimer.restart();
    shooterSubsystem.shootFromIntake();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (intakeTimer.hasElapsed(1.5)) {
      shooterSubsystem.shootFromIntake();
      intakeSubsystem.pushNote();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intakeSubsystem.intakeOff();
    shooterSubsystem.shootOff();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
