// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxAlternateEncoder.Type;
import com.revrobotics.SparkMaxAlternateEncoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.revrobotics.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.CommandBase;
import com.revrobotics.RelativeEncoder;
import java.lang.Math;

public class Drivebase extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  public Drivebase() {
        m_leftGroup.setInverted(true);
  }

    CANSparkMax m_rightMaster = new CANSparkMax(1, MotorType.kBrushless);
    CANSparkMax m_rightSlave = new CANSparkMax(2, MotorType.kBrushless);
    CANSparkMax m_leftMaster = new CANSparkMax(3, MotorType.kBrushless);
    CANSparkMax m_leftSlave = new CANSparkMax(4, MotorType.kBrushless);

    MotorControllerGroup m_rightGroup = new MotorControllerGroup(m_rightMaster, m_rightSlave);
    MotorControllerGroup m_leftGroup = new MotorControllerGroup(m_leftMaster, m_leftSlave);


    RelativeEncoder m_rightEncoder = m_rightMaster.getAlternateEncoder(Type.kQuadrature, 4096);
    RelativeEncoder m_leftEncoder = m_leftMaster.getAlternateEncoder(Type.kQuadrature, 4096);

    DifferentialDrive m_differentialDrive = new DifferentialDrive(m_rightGroup, m_leftGroup);
    

  public void arcadeDrive(double speed, double rotation) {
    m_differentialDrive.arcadeDrive(speed,rotation);
  }

  public void resetEncoders() {
    m_rightEncoder.setPosition(0);
    m_leftEncoder.setPosition(0);
  }


  public double getLeftDistanceInch() {
    return (m_leftEncoder.getPosition()/4096 * Constants.diameter * Math.PI);
  }

  public double getRightDistanceInch() {
    return (m_rightEncoder.getPosition()/4096 * Constants.diameter * Math.PI);
  }

  public double getAverageDistanceInch() {
    return getLeftDistanceInch() + getRightDistanceInch();
  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  public CommandBase exampleMethodCommand() {

    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
