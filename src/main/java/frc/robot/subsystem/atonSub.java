package frc.robot.subsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.sensors.RomiGyro;

public class atonSub extends SubsystemBase{

    static RomiGyro gyro;
    private static RomiDrivetrain Drive = new RomiDrivetrain(gyro);

    private static Timer timer = new Timer();
    public static int stage = 1;

    public atonSub(RomiGyro Gyro) {
        gyro = Gyro;
    }

    public static double getTime() {
        return timer.get();
    }

    public static void resetTimer() {
        timer.reset();
    }

    public static void startTimer() {
        stage = 1;
        timer.reset();
        timer.start();
    }

    public static void drive(double speed, double rot) {
        System.out.println("driving!");
        Drive.arcadeDrive(speed, rot);
    }

    public static void stop(){
        Drive.stop();
    }
}
