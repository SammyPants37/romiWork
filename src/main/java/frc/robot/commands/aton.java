package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystem.atonSub;

public class aton extends CommandBase {
    
    private int stage = atonSub.stage;

    public void execute() {
        if (atonSub.getTime() < 1.5 & stage == 1) {
            atonSub.drive(0.7, 0.0); // drive forwards half speed
          } else if (atonSub.getTime() < 1.5 & stage == 2) {
            atonSub.drive(0.0, 0.4);
          } else if (atonSub.getTime() < 1.5 & stage == 3) {
            atonSub.drive(0.7, 0.0);
          } else if (atonSub.getTime() > 1.5) {
            atonSub.stop();// stop robot
            atonSub.resetTimer();
            stage += 1;
          }
    }

    public boolean isFinished() {
      return stage == 4;
    }
}
