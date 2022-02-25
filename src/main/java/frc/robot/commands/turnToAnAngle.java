package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.sensors.RomiGyro;
import frc.robot.subsystem.RomiDrivetrain;

public class turnToAnAngle extends CommandBase{
    
    private double angle;
    private RomiGyro gyro;
    private RomiDrivetrain drive = new RomiDrivetrain(gyro);
    private boolean turnLeft = false;

    public turnToAnAngle(double turnAngle, RomiGyro theGyro) {
        angle = turnAngle;
        gyro = theGyro;
        if (angle > gyro.getAngleZ()) {
            turnLeft = true;
        }
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
        return gyro.getAngleZ() >= angle;
    }

    public void end(boolean interrupted) {
        drive.arcadeDrive(0, 0);
      }
}
