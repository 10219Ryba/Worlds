// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.DriveConstants.ShooterConstants;
import frc.robot.Constants.DriveConstants.XboxPorts;
import frc.robot.commands.AutoDrive;
import frc.robot.commands.ShooterCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */

public class RobotContainer {
private final DriveSubsystem driveSubsystem = new DriveSubsystem();
private final ShooterSubsystem shoot = new ShooterSubsystem();
private final IntakeSubsystem intake = new IntakeSubsystem();

private SendableChooser<Command> autoChooser = new SendableChooser<Command>();

  CommandXboxController xbox1 = new CommandXboxController(XboxPorts.xbox1);
  CommandXboxController xbox2 = new CommandXboxController(XboxPorts.xbox2);
  
  public RobotContainer() {
    configureBindings();
    autoChooser.setDefaultOption("Drive Forwards", new AutoDrive(driveSubsystem, 0.3));
    autoChooser.addOption("Drive Backwards", new AutoDrive(driveSubsystem, -0.3));
    autoChooser.addOption("Speaker Shot", new ShooterCommand(shoot).andThen(new WaitCommand(1)).andThen(Commands.runOnce(shoot::shootOff, shoot)));
    autoChooser.addOption("Speaker Backwards", new ShooterCommand(shoot).andThen(new WaitCommand(1)).andThen(Commands.runOnce(shoot::shootOff, shoot)).andThen(new AutoDrive(driveSubsystem, -1)));
    SmartDashboard.putData(autoChooser);
    driveSubsystem.setDefaultCommand(new RunCommand(
      () -> 
      driveSubsystem.arcadeDrive(
        xbox1.getRightX(),
        -xbox1.getLeftY()),
      driveSubsystem));
  
  

  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {

    xbox1.a().whileTrue(new InstantCommand(() -> {
      intake.bottomIntake.set(1);
    })).onFalse(new InstantCommand(() -> {
      intake.bottomIntake.set(0);
    }));
    // Instant Shoot
    xbox1.b().whileTrue(new InstantCommand(() -> {
      shoot.smOne.set(1);
      shoot.smTwo.set(1);
  })).onFalse(new InstantCommand(() -> {shoot.smOne.set(0); shoot.smTwo.set(0);}));
    
  xbox1.rightTrigger(.6).whileTrue(new ShooterCommand(shoot));
  xbox1.leftTrigger(.6).onTrue(Commands.runOnce(shoot::intake, shoot)).onFalse(Commands.runOnce(shoot::shootOff, shoot));


  }
    

    public Command getAutonomousCommand() {
      return autoChooser.getSelected();
    }
  }


 
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */




