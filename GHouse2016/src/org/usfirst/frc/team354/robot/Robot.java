
package org.usfirst.frc.team354.robot;

import org.usfirst.frc.team354.robot.commands.FullyLowerMainArm;
import org.usfirst.frc.team354.robot.commands.auto.routines.AutoRoutineHulkMode;
import org.usfirst.frc.team354.robot.commands.auto.routines.AutoRoutineLowBar;
import org.usfirst.frc.team354.robot.commands.auto.routines.AutoRoutineLowBarWithTurnTake2;
import org.usfirst.frc.team354.robot.commands.auto.routines.AutoRoutineSpyShoot;
import org.usfirst.frc.team354.robot.commands.auto.routines.AutoRoutineSpyShootNoMove;
import org.usfirst.frc.team354.robot.dashboard.DashboardConnection;
import org.usfirst.frc.team354.robot.subsystems.DriveSystem;
import org.usfirst.frc.team354.robot.subsystems.IntakeSystem;
import org.usfirst.frc.team354.robot.subsystems.MainArm;
import org.usfirst.frc.team354.robot.subsystems.ShooterRoller;
import org.usfirst.frc.team354.robot.subsystems.UpperArm;
import org.usfirst.frc.team354.robot.subsystems.Winch;
import org.usfirst.frc.team354.robot.vision.VisionProcessing;
import org.usfirst.frc.team354.robot.vision.VisionSystem;
import org.usfirst.frc.team354.robot.vision.VisionSystem.VisionTarget;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	public static final DriveSystem driveSystem = new DriveSystem();
	public static final MainArm mainArm = new MainArm();
	public static final UpperArm upperArm = new UpperArm();
	public static final ShooterRoller lowerShooter = new ShooterRoller("LowerShooter", 
					Constants.CAN_ID_LOWER_SHOOTER_MASTER, Constants.CAN_ID_LOWER_SHOOTER_SLAVE,
					0.05, 0.0, 0.5, 0.025); // PIDF
	public static final ShooterRoller upperShooter = new ShooterRoller("UpperShooter", 
					Constants.CAN_ID_UPPER_SHOOTER_MASTER, Constants.CAN_ID_UPPER_SHOOTER_SLAVE,
					0.06, 0.0, 0.204, 0.033); // PIDF
	
	public static final IntakeSystem intakeSystem = new IntakeSystem();
	public static final Winch winch = new Winch();
	
	public static final VisionSystem visionSystem = new VisionSystem();
	
	public static final MaxBotixRangefinder frontRangefinder = new MaxBotixRangefinder(Constants.AIN_ULTRASONIC, -2.0);
	
	public static AHRS ahrs;
	
	public static OI oi;

    Command autonomousCommand;
    SendableChooser chooser;
    
    private CameraServer server;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	// Start up VisionSystem
    	visionSystem.initialize();
    	
    	// Set up CameraServer
    	server = CameraServer.getInstance();
        server.setQuality(50);
        //the camera name (ex "cam0") can be found through the roborio web interface
        server.startAutomaticCapture("cam0");
    	
    	// Set up AHRS
    	try {
    		ahrs = new AHRS(I2C.Port.kMXP);
    	}
    	catch (RuntimeException e) {
    		DriverStation.reportError("Error instantiating navX MXP: " + e.getMessage(), true);
    	}
    	
		oi = new OI();
        chooser = new SendableChooser();
        //chooser.addDefault("Do Nothing", new NoOp());
        //chooser.addObject("Low Bar Side Target", new AutoRoutineLowBarSideTarget());
        chooser.addObject("Low Bar Simple", new AutoRoutineLowBar());
        chooser.addObject("Low Bar With Turn", new AutoRoutineLowBarWithTurnTake2());
        chooser.addObject("Hulk Mode", new AutoRoutineHulkMode());
        chooser.addObject("Spy Zone (Move Forward)", new AutoRoutineSpyShoot());
        chooser.addObject("Spy Zone (No Movement)", new AutoRoutineSpyShootNoMove());
        
        // TEST
        chooser.addObject("TEST fully lower arm", new FullyLowerMainArm());
        
        SmartDashboard.putData("Auto mode", chooser);
        
        DashboardConnection.intitialize();
        
        System.out.println("[ROBOT] Robot Initialized");
    }
    
    private boolean okToShoot(VisionTarget target) {
    	if (target == null) return false;
    	double distance = VisionProcessing.distanceToTargetCorrected(target);
    	double ultrasonicDist = frontRangefinder.getRange();
    	//Make sure we are more or less lined up center
    	if (Math.abs(160 - target.getCenterX()) < 10) { //10px margin
    		return true;
    	}
    	return false;
    }
    
    private void updateDashboard() {
    	VisionTarget target = visionSystem.getBestTarget();
    	if (target != null) {
        	SmartDashboard.putNumber("Distance To Target", VisionProcessing.distanceToTargetCorrected(target));
        	double[] vals = new double[4];
    		vals[0] = target.getCenterX();
    		vals[1] = target.getCenterY();
    		vals[2] = target.getWidth();
    		vals[3] = target.getHeight();
    		DashboardConnection.publishNumberArray("targetInfo", vals);
        }
        else {
        	SmartDashboard.putNumber("Distance To Target", -1);
        	double[] vals = new double[4];
    		vals[0] = -1;
    		vals[1] = -1;
    		vals[2] = -1;
    		vals[3] = -1;
    		DashboardConnection.publishNumberArray("targetInfo", vals);
        }
        SmartDashboard.putNumber("Main Arm Angle", mainArm.getAngle());
        SmartDashboard.putNumber("Upper Arm Angle", upperArm.getAngle());
        SmartDashboard.putBoolean("Main Arm Fully Lowered", mainArm.isFullyLowered());
        
        SmartDashboard.putBoolean("Main Arm Safe To Move", mainArm.isSafeToMove());
        SmartDashboard.putNumber("Ultrasonic Distance", frontRangefinder.getRange());
        
        SmartDashboard.putBoolean("Ball In Robot", intakeSystem.ballPresent());
        
        DashboardConnection.publishNumber("mainArmAngle", mainArm.getAngle());
        
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
        autonomousCommand = (Command) chooser.getSelected();
        
		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        updateDashboard();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        updateDashboard();
    }
       
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
