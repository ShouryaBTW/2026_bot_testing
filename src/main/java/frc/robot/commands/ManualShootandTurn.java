package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.FloorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.PivotSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ManualShootandTurn extends SequentialCommandGroup {
  public ManualShootandTurn(
      ShooterSubsystem shooter,
      FeederSubsystem feeder,
      FloorSubsystem floor,
      DriveSubsystem drive,
      IntakeSubsystem intake,
      PivotSubsystem pivot,
      double shooterRPS
  ) {
    addCommands(
        new TurnToTagLive(drive),

        new ParallelCommandGroup(
            new RunCommand(() -> drive.setX(), drive),

            new ParallelCommandGroup(
                new SetShooterRPS(shooter, shooterRPS),

                new SequentialCommandGroup(
                    new WaitUntilCommand(() -> shooter.atTargetSpeed()),

                    new ParallelCommandGroup(
                        new SetFloorRPS(floor, 40),
                        new SetFeederRPS(feeder, 90),
                        new RunIntake(intake, pivot, 30, 70)
                    )
                )
            )
        )
    );
  }
}