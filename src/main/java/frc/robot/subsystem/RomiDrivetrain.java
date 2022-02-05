// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystem;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class RomiDrivetrain {
  private static final double kCountsPerRevolution = 1440.0;
  private static final double kWheelDiameterInch = 2.75591; // 70 mm

  // The Romi has the left and right motors set to
  // PWM channels 0 and 1 respectively
  private final static Spark m_leftMotor = new Spark(0);
  private final static Spark m_rightMotor = new Spark(1);

  // The Romi has onboard encoders that are hardcoded
  // to use DIO pins 4/5 and 6/7 for the left and right
  private final Encoder m_leftEncoder = new Encoder(4, 5);
  private final Encoder m_rightEncoder = new Encoder(6, 7);

  // Set up the differential drive controller
  private final static DifferentialDrive m_diffDrive = new DifferentialDrive(m_leftMotor, m_rightMotor);

  int P, I, D = 1;
  
  int integral, previous_error, setpoint = 0;
  private Gyro gyro;
  private double error;
  private double derivative;
  private double rcw;

  /** Creates a new RomiDrivetrain. */
  public RomiDrivetrain() {
    // Use inches as unit for encoder distances
    m_leftEncoder.setDistancePerPulse((Math.PI * kWheelDiameterInch) / kCountsPerRevolution);
    m_rightEncoder.setDistancePerPulse((Math.PI * kWheelDiameterInch) / kCountsPerRevolution);
    resetEncoders();
  }

  public RomiDrivetrain(Gyro Gyro){
    gyro = Gyro;
    P = 1;
    I = 0;
    D = 0;
  }

  public void setSetpoint(int Setpoint)  {
      setpoint = Setpoint;
  }

  public void PID(){
      error = setpoint - gyro.getAngle(); // Error = Target - Actual
      integral += (error*.02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
      derivative = (error - previous_error) / .02;
      rcw = P*error + I*integral + D*derivative;
    }

  public void arcadeDrive(double xaxisSpeed, double zaxisRotate) {
    PID();
    m_diffDrive.arcadeDrive(xaxisSpeed, rcw+zaxisRotate);
  }

  public void stop() {
    m_diffDrive.arcadeDrive(0.0, 0.0);
  }

  public void resetEncoders() {
    m_leftEncoder.reset();
    m_rightEncoder.reset();
  }

  public double getLeftDistanceInch() {
    return m_leftEncoder.getDistance();
  }

  public double getRightDistanceInch() {
    return m_rightEncoder.getDistance();
  }
}
