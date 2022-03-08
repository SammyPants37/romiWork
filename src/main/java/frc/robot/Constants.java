/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

public class Constants {

    public static final class DriveConstants {
        public static final int leftMotorPort = 0;
        public static final int rightMotorPort = 1;
        public static final int leftEncoderA = 4;
        public static final int leftEncoderB = 5;
        public static final int rightEncoderA = 6;
        public static final int rightEncoderB = 7;
        public static final double wheelDiameter = 2.83;
        public static final double pulsesPerRev = 1440;
        public static final double inchesPerPulse = Math.PI * wheelDiameter / pulsesPerRev;
        public static final double wheelTrack = 5.25; // 5.86;
        public static final double inchesPerDegree = Math.PI * wheelDiameter / 360;

    }
}
