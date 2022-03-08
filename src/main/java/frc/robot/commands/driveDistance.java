package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystem.RomiDrivetrain;

public class driveDistance extends CommandBase{
    private double distance;
    private RomiDrivetrain drive;

    public driveDistance(double theDistance, RomiDrivetrain drivetrain) {
        drive = drivetrain;
        distance = theDistance;
        drive.resetEncoders();
        addRequirements(drive);

    }

    public void execute() {
        System.out.println("driving!");
        drive.arcadeDrive(0.7, 0.0);
    }

    public boolean isFinished() {
        return drive.getLeftDistanceInch() >= distance;
    }

    public void end(boolean interrupted) {
        drive.arcadeDrive(0, 0);
      }
}
