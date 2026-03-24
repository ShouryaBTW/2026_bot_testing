package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.DriveSubsystem;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private final RobotContainer m_robotContainer;

  private final SendableChooser<String> m_autoChooser = new SendableChooser<>();
  private static final String kAutoLeft   = "Left Side Auto";
  private static final String kAutoRight  = "Right Side Auto";

  public Robot() {
    m_robotContainer = new RobotContainer();

    m_autoChooser.setDefaultOption("Left Side Auto", kAutoLeft);
    m_autoChooser.addOption("Right Side Auto", kAutoRight);
    SmartDashboard.putData("Auto Chooser", m_autoChooser);

    initializeGyro();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void autonomousInit() {
    String autoSelected = m_autoChooser.getSelected();
    SmartDashboard.putString("Auto Running", autoSelected);

    switch (autoSelected) {
      case kAutoLeft:
        m_autonomousCommand = m_robotContainer.getLeftSideAuto();
        break;
      case kAutoRight:
        m_autonomousCommand = m_robotContainer.getRightSideAuto();
        break;
      default:
        m_autonomousCommand = m_robotContainer.getLeftSideAuto();
        break;
    }

    if (m_autonomousCommand != null) {
      CommandScheduler.getInstance().schedule(m_autonomousCommand);
    }
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}

  private void initializeGyro() {
    DriveSubsystem drive = m_robotContainer.getDriveSubsystem();

    new Thread(() -> {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      drive.resetGyroToZero();
    }).start();
  }
}