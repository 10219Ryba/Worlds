
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class DriveConstants {
    // Drive Motors
    public static int rR = 2;
    public static int rF = 3;
    public static int lR = 4;
    public static int lF = 5;
    public static int kTurnToleranceDeg = 5;
    public static int kTurnRateToleranceDegPerS = 10;
  public static class IntakeContants {
    public static int intakeOne = 8;
    public static int intakeTwo = 9;
  }
    // Left Encoder
    public static int[] LeftEncoderPorts = new int[] {0, 1};
    public static boolean LeftEncoderReversed = false;
    // Right Encoder
    public static int[] RightEncoderPorts = new int[] {2, 3};
    public static boolean RightEncoderReversed = true;
    // Encoder Info
    public static final int EncoderCPR = 1024;
    public static final double WheelDiameterInches = 6;
    public static final double EncoderDistancePerPulse = 
    (WheelDiameterInches * Math.PI) / (double) EncoderCPR;
    // Gyro Info
    public static final boolean gyroReversed = false;
  public static class ShooterConstants {
    public static int shootOne = 6;
    public static int shootTwo = 7;
  }
  public static class XboxPorts {
    public static int xbox1 = 0;
    public static int xbox2 = 1;
  }
  public static class PID {
    public static int TurnP = 1;
    public static int TurnI = 0;
    public static int TurnD = 0;
  }
  }
}
