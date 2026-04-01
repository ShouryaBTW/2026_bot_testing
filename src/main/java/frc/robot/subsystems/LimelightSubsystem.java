// package frc.robot.subsystems;

// import edu.wpi.first.networktables.NetworkTable;
// import edu.wpi.first.networktables.NetworkTableInstance;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import frc.robot.Variables;
// import frc.robot.utils.APTree;
// import frc.robot.utils.Calculations;
// import frc.robot.utils.Pose;

// public class LimelightSubsystem extends SubsystemBase {

//   private static NetworkTable limelight = NetworkTableInstance.getDefault().getTable("limelight-naci");

//   private static APTree tagToDistanceLookup = new APTree();
//   private static APTree distanceToSpeedLookup = new APTree();
  
//   private static final double CAMERA_TO_CENTRE = 0.349;

//   // -------------------------------------------------------
//   //   Per-tag TY → Distance tables
//   // -------------------------------------------------------
//   private static final double[][] T8_DISTANCE_DATA = {
//     // {3.01, 0.46}, // {tY, distance}
//     // {-4.51, 0.76},
//     // {-9.45, 1.08},
//     // {-13.16, 1.46},
//     // {-16.01, 1.84},
//     // {-18.27, 2.26},
//     // {-19.45, 2.58}

//     {3.77, 0.53}, // {tY, distance}
//     {-3.61, 0.89},
//     {-9.61, 1.34},
//     {-14.42, 1.88},
//     {-17.36, 2.63}
//   };

//   private static final double[][] T10_DISTANCE_DATA = {
//     // {19.10, 0.08},
//     // {0.92, 0.53},
//     // {-8.70, 1.03},
//     // {-14.07, 1.53},
//     // {-17.25, 2.06}

//     {19.68, 0.08},
//     {7.61, 0.35},
//     {-1.29, 0.65},
//     {-5.62, 0.87},
//     {-11.34, 1.28},
//     {-15.60, 1.85},
//     {-17.16, 2.11}
//   };

//   private static final double[][] T11_DISTANCE_DATA = {
//     // {4.72, 0.42},
//     // {-5.12, 0.84},
//     // {-10.87, 1.26},
//     // {-15.47, 1.77},
//     // {-17.07, 2.07},
//     // {-18.62, 2.38},
//     // {-19.50, 2.60}

//     {9.74, 0.30},
//     {3.62, 0.48},
//     {-3.05, 0.77},
//     {-8.67, 1.13},
//     {-13.02, 1.54},
//     {-17.75, 2.27},
//     {-19.14, 2.63}
//   };

//   private static final double[][] T7_DISTANCE_DATA = {
//     // {7.24, 0}, // {tY, distance}
//     // {-6.58, 0.36},
//     // {-14.85, 0.83},
//     // {-21.70, 1.87},
//     // {-23.45, 2.46},
//     // {-25.07, 3.14}

//     {-2.58, 0.33}, // {tY, distance}
//     {-9.91, 0.65},
//     {-16.05, 1.14},
//     {-19.13, 1.62},
//     {-20.49, 2.01},
//   };

//     private static final double[][] T6_DISTANCE_DATA = {
//     // {7.55, 0.00}, // {tY, distance}
//     // {-7.03, 0.35},
//     // {-12.95, 0.68},
//     // {-17.47, 1.10},
//     // {-20.53, 1.57},
//     // {-22.52, 2.11},
//     // {-24.04, 2.76},

//     {8.64, 0.05}, // {tY, distance}
//     {-4.27, 0.35},
//     {-11.01, 0.65},
//     {-16.12, 1.16},
//     {-19.97, 1.64},
//     {-21.19, 2.13},
//     {-22.22, 2.58}
//   };

//   // Distance → Shooter RPS
//   private static final double[][] SPEED_DATA = {
//     // {1.51, 60},  // {distance, speed}
//     // {1.92, 62},
//     // {2.34, 64},
//     // {2.5, 66},
//     // {2.75, 67},
//     // {2.80, 67},
//     // {3.25, 69}

//     {1.51, 60},  // {distance, speed}
//     {1.92, 62},
//     {2.34, 64},
//     {2.71, 67},
//     {2.75, 68},
//     {2.80, 70},
//     {3.00, 70},
//     {3.25, 70},
//     {4.00, 70}
//   };

//   public LimelightSubsystem() {
//     distanceToSpeedLookup.InsertValues(SPEED_DATA);
//   }

//   // -------------------------------------------------------
//   //   Tag Classification
//   // -------------------------------------------------------

//   /** Returns the TY→Distance table for a given tag ID, or null if unsupported. */
//   public double[][] getDataForTag(double IDNum) {
//     switch ((int) Math.round(IDNum)) {
//       // case 10: return T10_DISTANCE_DATA;
//       // case 11: return T11_DISTANCE_DATA; //11
//       // case 8: return T8_DISTANCE_DATA;
//       // case 7: return T7_DISTANCE_DATA;
//       // case 12: return T7_DISTANCE_DATA;
//       // case 6: return T6_DISTANCE_DATA;
//       // case 1: return T6_DISTANCE_DATA;

//       // case 26: return T10_DISTANCE_DATA;
//       // case 27: return T11_DISTANCE_DATA;
//       // case 24: return T8_DISTANCE_DATA;
//       // case 28: return T7_DISTANCE_DATA;
//       // case 23: return T7_DISTANCE_DATA;
//       // case 17: return T6_DISTANCE_DATA;
//       // case 22: return T6_DISTANCE_DATA;
//       // default: return null;

//       case 10: return T10_DISTANCE_DATA;
//       case 11: return T11_DISTANCE_DATA;
//       case 8: return T8_DISTANCE_DATA;
//       case 7: return T7_DISTANCE_DATA;
//       case 12: return T7_DISTANCE_DATA;
//       case 6: return T6_DISTANCE_DATA;
//       case 1: return T6_DISTANCE_DATA;

//       case 26: return T10_DISTANCE_DATA;
//       case 18: return T8_DISTANCE_DATA;
//       case 24: return T11_DISTANCE_DATA;
//       case 28: return T7_DISTANCE_DATA;
//       case 23: return T7_DISTANCE_DATA;
//       case 17: return T6_DISTANCE_DATA;
//       case 22: return T6_DISTANCE_DATA;
//       default: return null;
//     }
//   }

//   /** Tags that are valid targets for auto-aiming/rotation. */
//   public boolean isAimTag() {
//     int id = (int) Math.round(Variables.limelight.tID);
//     return id == 2 || id == 5 || id == 10 || id == 13 || id == 12 || id == 1;
//   }

//   // -------------------------------------------------------
//   //   Computed Values
//   // -------------------------------------------------------

//   /** Returns distance to the given tag ID using the current TY reading. */
//   public double getTagDistance() {
//     double[][] data = getDataForTag(Variables.limelight.tID);
//     if (data == null) return 0;

//     tagToDistanceLookup = new APTree();
//     tagToDistanceLookup.InsertValues(data);
//     return tagToDistanceLookup.GetValue(Variables.limelight.tY);
//   }

//   /** Returns the shooter RPS for the given tag ID based on distance. */
//   public double getShooterRPS() {
//     if (getDataForTag(Variables.limelight.tID) == null) return 67.0;
//     return distanceToSpeedLookup.GetValue(Variables.limelight.distanceMeters);
//   }

//   public double getTurnAngle(double currentRobotHeadingDeg) {
//     if (!Variables.limelight.hasValidTarget) {
//         return currentRobotHeadingDeg;
//     }

//     double tx = Variables.limelight.tX;
//     double sideOffsetDeg = 50;

//     if (tx > 0) {
//         // tag is to the right
//         return Calculations.normalizeAngle360(currentRobotHeadingDeg - tx + sideOffsetDeg);
//     } else {
//         // tag is to the left
//         return Calculations.normalizeAngle360(currentRobotHeadingDeg - tx - sideOffsetDeg);
//     }
// }

//   // -------------------------------------------------------
//   //   Raw Limelight Network Table Accessors
//   // -------------------------------------------------------

//   public double getDoubleEntry(String entry) {
//     return limelight.getEntry(entry).getDouble(0);
//   }

//   public double[] getArrayEntry(String entry) {
//     return limelight.getEntry(entry).getDoubleArray(new double[6]);
//   }

//   public Pose getPoseFromTag(double robotYawDeg) {

//     if (!Variables.limelight.hasValidTarget) {
//         return null; // or return current pose instead
//     }

//     double distance = Variables.limelight.distanceMeters + CAMERA_TO_CENTRE;  // already updating in periodic
//     double tx = Variables.limelight.tX;

//     // Bearing from field frame
//     double bearingRad = Math.toRadians(robotYawDeg - tx);

//     // Tag assumed at (0,0)
//     double robotX = -distance * Math.cos(bearingRad);
//     double robotY = -distance * Math.sin(bearingRad);

//     return new Pose(robotX, robotY, robotYawDeg);
//   }

//   // -------------------------------------------------------
//   //   Rumble Tags
//   // -------------------------------------------------------

//   public boolean isRumbleTagVisible() {
//     if (!Variables.limelight.hasValidTarget) {
//       return false;
//     }

//     int id = (int) Math.round(Variables.limelight.tID);
//     return id == 10 || id == 8 || id == 24 || id == 18 || id == 26 || id == 2;
//   }

//   // -------------------------------------------------------
//   //   Periodic
//   // -------------------------------------------------------

//   @Override
//   public void periodic() {
//     Variables.limelight.hasValidTarget = limelight.getEntry("tv").getDouble(0) == 1;
//     SmartDashboard.putBoolean("HAS TARGET", Variables.limelight.hasValidTarget);

//     if (Variables.limelight.hasValidTarget) {
//         Variables.limelight.tID = getDoubleEntry("tid");
//         Variables.limelight.tA  = getDoubleEntry("ta");
//         Variables.limelight.tX  = getDoubleEntry("tx");
//         Variables.limelight.tY  = getDoubleEntry("ty");

//         Variables.limelight.distanceMeters = getTagDistance();
//         Variables.limelight.turnAngle = getTurnAngle(Variables.drive.robotHeading);
//         Variables.limelight.shooterRPS     = getShooterRPS();

//         // SmartDashboard.putNumber("tid", Variables.limelight.tID);
//         // SmartDashboard.putNumber("ta", Variables.limelight.tA);
//         // SmartDashboard.putNumber("ty", Variables.limelight.tY);
//         // SmartDashboard.putNumber("tx", Variables.limelight.tX);

//         SmartDashboard.putNumber("distanceMeters", Variables.limelight.distanceMeters);
//         SmartDashboard.putNumber("turnAngle", Variables.limelight.turnAngle);
//         SmartDashboard.putNumber("shooterRPS", Variables.limelight.shooterRPS);
//     }
//   }
// }



package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Variables;
import frc.robot.utils.APTree;
import frc.robot.utils.Calculations;
import frc.robot.utils.Pose;

public class LimelightSubsystem extends SubsystemBase {

  private static NetworkTable limelight = NetworkTableInstance.getDefault().getTable("limelight-naci");

  private static APTree tagToDistanceLookup = new APTree();
  private static APTree distanceToSpeedLookup = new APTree();

  private static final double CAMERA_TO_CENTRE = 0.349;

  // -------------------------------------------------------
  //   Cache — holds last valid limelight data for 300ms
  // -------------------------------------------------------

  private final Timer cacheTimer = new Timer();
  private static final double CACHE_TIMEOUT = 1.0; // 300ms

  private double cachedDistanceMeters = 0;
  private double cachedShooterRPS     = 67.0; // safe fallback
  private double cachedTX             = 0;
  private boolean cacheValid          = false;

  // ... all your existing distance data tables stay exactly the same ...

  private static final double[][] T8_DISTANCE_DATA = {
    {3.77, 0.53},
    {-3.61, 0.89},
    {-9.61, 1.34},
    {-14.42, 1.88},
    {-17.36, 2.63}
  };

  private static final double[][] T10_DISTANCE_DATA = {
    {19.68, 0.08},
    {7.61, 0.35},
    {-1.29, 0.65},
    {-5.62, 0.87},
    {-11.34, 1.28},
    {-15.60, 1.85},
    {-17.16, 2.11}
  };

  private static final double[][] T11_DISTANCE_DATA = {
    {9.74, 0.30},
    {3.62, 0.48},
    {-3.05, 0.77},
    {-8.67, 1.13},
    {-13.02, 1.54},
    {-17.75, 2.27},
    {-19.14, 2.63}
  };

  private static final double[][] T7_DISTANCE_DATA = {
    {-2.58, 0.33},
    {-9.91, 0.65},
    {-16.05, 1.14},
    {-19.13, 1.62},
    {-20.49, 2.01},
  };

  private static final double[][] T6_DISTANCE_DATA = {
    {8.64, 0.05},
    {-4.27, 0.35},
    {-11.01, 0.65},
    {-16.12, 1.16},
    {-19.97, 1.64},
    {-21.19, 2.13},
    {-22.22, 2.58}
  };

  private static final double[][] SPEED_DATA = {
    {1.51, 60},
    {1.92, 62},
    {2.34, 64},
    {2.71, 67},
    {2.75, 68},
    {2.80, 70},
    {3.00, 70},
    {3.25, 70},
    {4.00, 70}
  };

  public LimelightSubsystem() {
    distanceToSpeedLookup.InsertValues(SPEED_DATA);
    cacheTimer.start();
  }

  // ... all your existing methods stay exactly the same ...

  public double[][] getDataForTag(double IDNum) {
    switch ((int) Math.round(IDNum)) {
      case 10: return T10_DISTANCE_DATA;
      case 11: return T11_DISTANCE_DATA;
      case 8:  return T8_DISTANCE_DATA;
      case 7:  return T7_DISTANCE_DATA;
      case 12: return T7_DISTANCE_DATA;
      case 6:  return T6_DISTANCE_DATA;
      case 1:  return T6_DISTANCE_DATA;
      case 26: return T10_DISTANCE_DATA;
      case 18: return T8_DISTANCE_DATA;
      case 24: return T11_DISTANCE_DATA;
      case 28: return T7_DISTANCE_DATA;
      case 23: return T7_DISTANCE_DATA;
      case 17: return T6_DISTANCE_DATA;
      case 22: return T6_DISTANCE_DATA;
      default: return null;
    }
  }

  public boolean isAimTag() {
    int id = (int) Math.round(Variables.limelight.tID);
    return id == 2 || id == 5 || id == 10 || id == 13 || id == 12 || id == 1;
  }

  public double getTagDistance() {
    double[][] data = getDataForTag(Variables.limelight.tID);
    if (data == null) return 0;
    tagToDistanceLookup = new APTree();
    tagToDistanceLookup.InsertValues(data);
    return tagToDistanceLookup.GetValue(Variables.limelight.tY);
  }

  public double getShooterRPS() {
    if (getDataForTag(Variables.limelight.tID) == null) return 67.0;
    return distanceToSpeedLookup.GetValue(Variables.limelight.distanceMeters);
  }

  public double getTurnAngle(double currentRobotHeadingDeg) {
    if (!Variables.limelight.hasValidTarget) {
      return currentRobotHeadingDeg;
    }
    double tx = Variables.limelight.tX;
    double sideOffsetDeg = 50;
    if (tx > 0) {
      return Calculations.normalizeAngle360(currentRobotHeadingDeg - tx + sideOffsetDeg);
    } else {
      return Calculations.normalizeAngle360(currentRobotHeadingDeg - tx - sideOffsetDeg);
    }
  }

  public double getDoubleEntry(String entry) {
    return limelight.getEntry(entry).getDouble(0);
  }

  public double[] getArrayEntry(String entry) {
    return limelight.getEntry(entry).getDoubleArray(new double[6]);
  }

  public Pose getPoseFromTag(double robotYawDeg) {
    if (!Variables.limelight.hasValidTarget) {
      return null;
    }
    double distance = Variables.limelight.distanceMeters + CAMERA_TO_CENTRE;
    double tx = Variables.limelight.tX;
    double bearingRad = Math.toRadians(robotYawDeg - tx);
    double robotX = -distance * Math.cos(bearingRad);
    double robotY = -distance * Math.sin(bearingRad);
    return new Pose(robotX, robotY, robotYawDeg);
  }

  public boolean isRumbleTagVisible() {
    if (!Variables.limelight.hasValidTarget) {
      return false;
    }
    int id = (int) Math.round(Variables.limelight.tID);
    return id == 10 || id == 8 || id == 24 || id == 18 || id == 26 || id == 2;
  }

  // -------------------------------------------------------
  //   Periodic — only change from your original is the cache logic
  // -------------------------------------------------------

  @Override
  public void periodic() {
    Variables.limelight.hasValidTarget = limelight.getEntry("tv").getDouble(0) == 1;
    SmartDashboard.putBoolean("HAS TARGET", Variables.limelight.hasValidTarget);

    if (Variables.limelight.hasValidTarget) {
      // Fresh data from limelight — update everything and refresh cache
      Variables.limelight.tID = getDoubleEntry("tid");
      Variables.limelight.tA  = getDoubleEntry("ta");
      Variables.limelight.tX  = getDoubleEntry("tx");
      Variables.limelight.tY  = getDoubleEntry("ty");

      Variables.limelight.distanceMeters = getTagDistance();
      Variables.limelight.turnAngle      = getTurnAngle(Variables.drive.robotHeading);
      Variables.limelight.shooterRPS     = getShooterRPS();

      // Update cache with fresh values and reset timer
      cachedDistanceMeters = Variables.limelight.distanceMeters;
      cachedShooterRPS     = Variables.limelight.shooterRPS;
      cachedTX             = Variables.limelight.tX;
      cacheValid           = true;
      cacheTimer.reset();

    } else if (cacheValid && cacheTimer.get() < CACHE_TIMEOUT) {
      // No tag visible BUT cache is still within 300ms — use cached values
      Variables.limelight.distanceMeters = cachedDistanceMeters;
      Variables.limelight.shooterRPS     = cachedShooterRPS;
      Variables.limelight.tX             = cachedTX;

    } else {
      // Cache expired — mark it invalid so we dont use stale data
      cacheValid = false;
    }

    SmartDashboard.putNumber("distanceMeters",   Variables.limelight.distanceMeters);
    SmartDashboard.putNumber("turnAngle",         Variables.limelight.turnAngle);
    SmartDashboard.putNumber("shooterRPS",        Variables.limelight.shooterRPS);
    SmartDashboard.putBoolean("Cache Active",     cacheValid && !Variables.limelight.hasValidTarget);
  }
}
