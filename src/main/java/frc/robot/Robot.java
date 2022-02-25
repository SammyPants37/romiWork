// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
// import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
// import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.aton;
import frc.robot.sensors.RomiGyro;
// import edu.wpi.first.wpilibj2.command.CommandScheduler;
// import frc.robot.commands.aton;
import frc.robot.subsystem.RomiDrivetrain;
// import frc.robot.subsystem.atonSub;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  // SendableChooser<Command> m_chooser = new SendableChooser<>();
  private final XboxController controller = new XboxController(0);
  private RomiGyro gyro = new RomiGyro();
  private RomiDrivetrain driveTrain = new RomiDrivetrain(gyro);

  // private final Timer timer = new Timer();
  // private int stage = 1;
  private double rot = 0.0;
  private double speed = 0.0;

  // private static boolean GyroInverted = false;
  
  private Command aton = new aton(gyro, 15.0, 180.0);
  // private Command m_autonomousCommand;
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // m_chooser.setDefaultOption("Simple Auto", aton);  
    // m_chooser.addOption("Complex Auto", aton);
    // m_autonomousCommand = m_chooser.getSelected();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // CommandScheduler.getInstance().run();
    
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    
    driveTrain.resetEncoders();
    // stage = 1;
    // timer.reset();
    // timer.start();
    // atonSub.startTimer();
    // aton.schedule();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {

    // if (timer.get() < 1.5 & stage == 1) {
    //   RomiDrivetrain.arcadeDrive(0.7, 0.0); // drive forwards half speed
    // } else if (timer.get() < 1.5 & stage == 2) {
    //   RomiDrivetrain.arcadeDrive(0.0, 0.4);
    // } else if (timer.get() < 1.5 & stage == 3) {
    //   RomiDrivetrain.arcadeDrive(0.7, 0.0);
    // } else if (timer.get() > 1.5) {
    //   RomiDrivetrain.stop(); // stop robot
    //   timer.reset();
    //   stage += 1;
    // }
    aton.execute();
  }
  

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    // if (m_autonomousCommand != null) {
    //   m_autonomousCommand.cancel();
    // }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    // rot setting
    if (controller.getRawAxis(4) >= 0.1 | controller.getRawAxis(4) <= -0.1) {
      rot = controller.getRawAxis(4);
    } else {
      rot = 0;
    }
    // speed setting
    if (controller.getRawAxis(1) >= 0.1 | controller.getRawAxis(1) <= -0.1) {
      speed = controller.getRawAxis(1);
    } else {
      speed = 0;
    }
    driveTrain.arcadeDrive(-speed, rot);
    // System.out.println(Math.IEEEremainder(gyro.getAngleZ(), 360) * (GyroInverted ? -1.0 : 1.0));
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
