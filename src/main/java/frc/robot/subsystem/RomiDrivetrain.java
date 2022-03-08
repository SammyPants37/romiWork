// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystem;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class RomiDrivetrain extends SubsystemBase{
  // private static final double kCountsPerRevolution = 1440.0;
  // private static final double kWheelDiameterInch = 2.75591; // 70 mm

  // The Romi has the left and right motors set to
  // PWM channels 0 and 1 respectively
  private final static PWMTalonSRX m_leftMotor = new PWMTalonSRX(Constants.DriveConstants.LEFT_MOTOR_PORT);
  private final static PWMTalonSRX m_rightMotor = new PWMTalonSRX(Constants.DriveConstants.RIGHT_MOTOR_PORT);

  // The Romi has onboard encoders that are hardcoded
  // to use DIO pins 4/5 and 6/7 for the left and right
  private final static Encoder m_leftEncoder = new Encoder(Constants.DriveConstants.LEFT_ENCODER_PORTS[0],
  Constants.DriveConstants.LEFT_ENCODER_PORTS[1]);
  private final static Encoder m_rightEncoder = new Encoder(Constants.DriveConstants.RIGHT_ENCODER_PORTS[0],
  Constants.DriveConstants.RIGHT_ENCODER_PORTS[1]);

  // Set up the differential drive controller
  private final static DifferentialDrive m_diffDrive = new DifferentialDrive(m_leftMotor, m_rightMotor);
  

  int P, I, D = 1;
  int integral, previous_error, setpoint = 0;
  public ADXRS450_Gyro gyro;
  double error;
  double derivative;
  double rcw;


  public RomiDrivetrain(ADXRS450_Gyro gyro){
    m_rightMotor.setInverted(true);
    m_diffDrive.setRightSideInverted(false);
    this.gyro = gyro;
    I = 0;
    D = 0;
  }

  public void setSetpoint(int setpoint) {
      this.setpoint = setpoint;
  }

  public void PID(){
      error = setpoint - gyro.getAngle(); // Error = Target - Actual
      this.integral += (error*.02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
      derivative = (error - this.previous_error) / .02;
      this.rcw = P*error + I*this.integral + D*derivative;
  }

  public void arcadeDrive(double speed, double rot) {
      // PID();
      m_diffDrive.arcadeDrive(speed, rcw+rot);
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
