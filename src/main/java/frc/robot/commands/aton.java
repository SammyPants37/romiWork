package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.sensors.RomiGyro;

public class aton extends SequentialCommandGroup {
    
    private RomiGyro gyro;

    public aton(RomiGyro theGyro, double distance, double angle) {
      addCommands(new driveDistance(distance, gyro),
                  new turnToAnAngle(angle, gyro),
                  new driveDistance(distance, gyro));
    }
}
