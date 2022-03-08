package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystem.RomiDrivetrain;

public class aton extends SequentialCommandGroup {
    

    public aton(RomiDrivetrain drivetrain, double distance, double angle) {
      addCommands(new driveDistance(distance, drivetrain),
                  new turnToAnAngle(angle, drivetrain),
                  new driveDistance(distance, drivetrain));
      addRequirements(drivetrain);
    }
}
