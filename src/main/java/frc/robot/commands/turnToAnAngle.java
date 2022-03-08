package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystem.RomiDrivetrain;

public class turnToAnAngle extends CommandBase{
    
    private double angle;
    private RomiDrivetrain drive;
    private boolean turnLeft = false;

    public turnToAnAngle(double turnAngle, RomiDrivetrain drivetrain) {
        angle = turnAngle;
        drive = drivetrain;
        if (angle > drive.gyro.getAngleZ()) {
            turnLeft = true;
        }
        addRequirements(drive);
    }

    public void execute() {
        System.out.println("turning!");
        if (turnLeft == true) {
            drive.arcadeDrive(0.0, -0.7);
        } else {
            drive.arcadeDrive(0.0, 0.7);
        }
    }

    public boolean isFinished() {
        return drive.gyro.getAngleZ() >= angle;
    }

    public void end(boolean interrupted) {
        drive.arcadeDrive(0, 0);
      }
}
