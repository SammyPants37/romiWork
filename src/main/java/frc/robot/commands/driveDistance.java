package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.sensors.RomiGyro;
import frc.robot.subsystem.RomiDrivetrain;

public class driveDistance extends CommandBase{
    private double distance;
    private RomiGyro gyro;
    private RomiDrivetrain drive = new RomiDrivetrain(gyro);

    public driveDistance(double theDistance, RomiGyro theGyro) {
        distance = theDistance;
        gyro = theGyro;
        drive.resetEncoders();

    }

    public void execute() {
        drive.arcadeDrive(0.7, 0.0);
    }

    public boolean isFinished() {
        return drive.getLeftDistanceInch() == distance;
    }
}
