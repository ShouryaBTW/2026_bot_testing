//  package frc.robot.commands;

// import edu.wpi.first.wpilibj2.command.Command;
// import frc.robot.Variables;
// import frc.robot.subsystems.DriveSubsystem;
// import frc.robot.subsystems.FeederSubsystem;
// import frc.robot.subsystems.FloorSubsystem;
// import frc.robot.subsystems.IntakeSubsystem;
// import frc.robot.subsystems.PivotSubsystem;
// import frc.robot.subsystems.ShooterSubsystem;
// import frc.robot.utils.APPID;

// public class TeleopShootLive extends Command {

//     // =========================================================
//     // Subsystems
//     // =========================================================

//     private final DriveSubsystem   drive;
//     private final ShooterSubsystem shooter;
//     private final FeederSubsystem  feeder;
//     private final FloorSubsystem   floor;
//     private final IntakeSubsystem  intake;
//     private final PivotSubsystem   pivot;

//     // =========================================================
//     // Turn PID — exact same constants as TurnToTagLive
//     // =========================================================

//     private final APPID turnPID;

//     private static final double kTurnP       = 0.02;
//     private static final double kTurnI       = 0.0;
//     private static final double kTurnD       = 0.0;
//     private static final double kMaxRot      = 0.30;
//     private static final double kMinRot      = 0.05;
//     private static final double kTxTolerance = 2.0;

//     // =========================================================
//     // Shooting constants — mirrors ShootSequence exactly
//     // =========================================================

//     private static final double SHOOT_PIVOT_ANGLE  = 70.0;  // from RunIntake(intake, pivot, 30, 70)
//     private static final double SHOOT_INTAKE_RPS   = 30.0;  // from RunIntake(intake, pivot, 30, 70)
//     private static final double SHOOT_FEEDER_RPS   = 90.0;  // from SetFeederRPS(feeder, 90)
//     private static final double SHOOT_FLOOR_RPS    = 40.0;  // from SetFloorRPS(floor, 40)
//     private static final double STOW_PIVOT_ANGLE   = 119.0; // return to stow on release

//     // =========================================================
//     // Constructor
//     // =========================================================

//     public TeleopShootLive(
//             DriveSubsystem drive,
//             ShooterSubsystem shooter,
//             FeederSubsystem feeder,
//             FloorSubsystem floor,
//             IntakeSubsystem intake,
//             PivotSubsystem pivot) {

//         this.drive   = drive;
//         this.shooter = shooter;
//         this.feeder  = feeder;
//         this.floor   = floor;
//         this.intake  = intake;
//         this.pivot   = pivot;

//         this.turnPID = new APPID(kTurnP, kTurnI, kTurnD, kTxTolerance);
//         this.turnPID.setMaxOutput(kMaxRot);

//         addRequirements(drive, shooter, feeder, floor, intake, pivot);
//     }

//     // =========================================================
//     // Initialize — runs once on button press
//     // =========================================================

//     @Override
//     public void initialize() {
//         turnPID.reset();

//         // Move pivot to shoot position immediately — same as ShootSequence
//         Variables.pivot.pivotPosition = SHOOT_PIVOT_ANGLE;

//         // Pre-spin shooter the moment button is pressed
//         Variables.shooter.shooterRPS = Variables.limelight.hasValidTarget
//                 ? Variables.limelight.shooterRPS
//                 : 67.0;

//         // Make sure feeder/floor/intake are stopped until ready
//         Variables.feeder.feederRPS = 0;
//         Variables.floor.floorRPS   = 0;
//         Variables.intake.intakeRPS = 0;
//     }

//     // =========================================================
//     // Execute — runs every ~20ms
//     // =========================================================

//     @Override
//     public void execute() {

//         // =======================================================
//         // 1. PIVOT — stays at shoot angle the whole time
//         //    PivotSubsystem.periodic() applies this every loop
//         // =======================================================

//         Variables.pivot.pivotPosition = SHOOT_PIVOT_ANGLE;

//         // =======================================================
//         // 2. LIVE SHOOTER RPS FROM LIMELIGHT DISTANCE
//         //    As driver moves, speed updates automatically
//         // =======================================================

//         if (Variables.limelight.hasValidTarget) {
//             Variables.shooter.shooterRPS = Variables.limelight.shooterRPS;
//         }
//         // No target — keep last known RPS, don't drop to zero

//         // =======================================================
//         // 3. CONTINUOUS RE-AIM
//         //    Corrects rotation every loop — defense resistant
//         // =======================================================

//         double rotCmd = 0.0;
//         boolean isAimed = false;

//         if (Variables.limelight.hasValidTarget) {
//             double tx = Variables.limelight.tX;

//             turnPID.setDesiredValue(0.0);
//             rotCmd = -turnPID.calculate(-tx);

//             // Minimum output to overcome static friction near target
//             if (Math.abs(tx) > kTxTolerance && Math.abs(rotCmd) < kMinRot) {
//                 rotCmd = Math.copySign(kMinRot, -tx);
//             }

//             isAimed = Math.abs(tx) <= kTxTolerance;
//         }

//         // Driver keeps full X/Y translation control
//         drive.drive(0.0, 0.0, rotCmd, true);

//         // =======================================================
//         // 4. FIRE GATE — mirrors ShootSequence WaitUntilCommand
//         //    Shooter must be at speed AND aimed before anything feeds
//         //    If defender pushes you — feeder stops, note held safely
//         // =======================================================

//         boolean shooterReady = shooter.atTargetSpeed();
//         boolean shouldFire   = isAimed && shooterReady;

//         if (shouldFire) {
//             // Mirrors: SetFloorRPS(40), SetFeederRPS(90), RunIntake(30, 70)
//             Variables.feeder.feederRPS = SHOOT_FEEDER_RPS;
//             Variables.floor.floorRPS   = SHOOT_FLOOR_RPS;
//             Variables.intake.intakeRPS = SHOOT_INTAKE_RPS;
//         } else {
//             // Not ready — hold the note
//             Variables.feeder.feederRPS = 0;
//             Variables.floor.floorRPS   = 0;
//             Variables.intake.intakeRPS = 0;
//         }
//     }

//     // =========================================================
//     // End — button released, clean up everything
//     // =========================================================

//     @Override
//     public void end(boolean interrupted) {
//         drive.drive(0.0, 0.0, 0.0, true);

//         // Stop all mechanisms
//         Variables.shooter.shooterRPS  = 0;
//         Variables.feeder.feederRPS    = 0;
//         Variables.floor.floorRPS      = 0;
//         Variables.intake.intakeRPS    = 0;

//         // Return pivot to stow — same as after ShootSequence
//         Variables.pivot.pivotPosition = STOW_PIVOT_ANGLE;
//     }

//     @Override
//     public boolean isFinished() {
//         return false;
//     }
// }



package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.OIConstants;
import frc.robot.Variables;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.FloorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.PivotSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.utils.APPID;

public class TeleopShootLive extends Command {

  private final DriveSubsystem driveSubsystem;
  private final ShooterSubsystem shooterSubsystem;
  private final FeederSubsystem feederSubsystem;
  private final FloorSubsystem floorSubsystem;
  private final IntakeSubsystem intakeSubsystem;
  private final PivotSubsystem pivotSubsystem;
  private final XboxController controller;

  private final APPID turnPID;

  private static final double kTurnP = 0.02;
  private static final double kTurnI = 0.0;
  private static final double kTurnD = 0.0;
  private static final double kMaxRot = 0.30;
  private static final double kMinRot = 0.05;
  private static final double kTxTolerance = 2.0;

  public TeleopShootLive(
      DriveSubsystem driveSubsystem,
      ShooterSubsystem shooterSubsystem,
      FeederSubsystem feederSubsystem,
      FloorSubsystem floorSubsystem,
      IntakeSubsystem intakeSubsystem,
      PivotSubsystem pivotSubsystem,
      XboxController controller) {

    this.driveSubsystem   = driveSubsystem;
    this.shooterSubsystem = shooterSubsystem;
    this.feederSubsystem  = feederSubsystem;
    this.floorSubsystem   = floorSubsystem;
    this.intakeSubsystem  = intakeSubsystem;
    this.pivotSubsystem   = pivotSubsystem;
    this.controller       = controller;

    this.turnPID = new APPID(kTurnP, kTurnI, kTurnD, kTxTolerance);
    this.turnPID.setMaxOutput(kMaxRot);

    addRequirements(driveSubsystem, shooterSubsystem, feederSubsystem, floorSubsystem, intakeSubsystem, pivotSubsystem);
  }

  @Override
  public void initialize() {
    turnPID.reset();

    Variables.pivot.pivotPosition = 70;
    Variables.shooter.shooterRPS  = Variables.limelight.hasValidTarget ? Variables.limelight.shooterRPS : 33.0;
    Variables.feeder.feederRPS    = 0;
    Variables.floor.floorRPS      = 0;
    Variables.intake.intakeRPS    = 0;
  }

  @Override
  public void execute() {
    Variables.pivot.pivotPosition = 70;

    if (Variables.limelight.hasValidTarget) {
      Variables.shooter.shooterRPS = Variables.limelight.shooterRPS;
    }

    double xSpeed = -MathUtil.applyDeadband(controller.getLeftY(), OIConstants.kDriveDeadband);
    double ySpeed = -MathUtil.applyDeadband(controller.getLeftX(), OIConstants.kDriveDeadband);

    double rotCmd = 0.0;
    boolean isAimed = false;

    if (Variables.limelight.hasValidTarget) {
      double tx = Variables.limelight.tX;

      turnPID.setDesiredValue(0.0);
      rotCmd = -turnPID.calculate(-tx);

      if (Math.abs(tx) > kTxTolerance && Math.abs(rotCmd) < kMinRot) {
        rotCmd = Math.copySign(kMinRot, -tx);
      }

      isAimed = Math.abs(tx) <= kTxTolerance;
    }

    driveSubsystem.drive(xSpeed, ySpeed, rotCmd, true);

    if (isAimed && shooterSubsystem.atTargetSpeed()) {
      Variables.feeder.feederRPS = 90;
      Variables.floor.floorRPS   = 40;
      Variables.intake.intakeRPS = 30;
    } else {
      Variables.feeder.feederRPS = 0;
      Variables.floor.floorRPS   = 0;
      Variables.intake.intakeRPS = 0;
    }
  }

  @Override
  public void end(boolean interrupted) {
    driveSubsystem.drive(0.0, 0.0, 0.0, true);

    Variables.shooter.shooterRPS  = 0;
    Variables.feeder.feederRPS    = 0;
    Variables.floor.floorRPS      = 0;
    Variables.intake.intakeRPS    = 0;
    Variables.pivot.pivotPosition = 119;
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}