
package org.usfirst.frc.team354.robot;

import org.usfirst.frc.team354.robot.commands.autonomous.base.AutoTurnToVisionTarget;
import org.usfirst.frc.team354.robot.commands.test.ShooterTest;
import org.usfirst.frc.team354.robot.commands.test.SystemsTest;
import org.usfirst.frc.team354.robot.subsystems.DriveSystem;
import org.usfirst.frc.team354.robot.subsystems.IntakeSystem;
import org.usfirst.frc.team354.robot.subsystems.MainArm;
import org.usfirst.frc.team354.robot.subsystems.ShooterRoller;
import org.usfirst.frc.team354.robot.subsystems.UpperArm;
import org.usfirst.frc.team354.robot.subsystems.Winch;
import org.usfirst.frc.team354.robot.vision.VisionSystem;

import com.kauailabs.navx.frc.AHRS;

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
	public static final ShooterRoller lowerShooter = new ShooterRoller("LowerShooter", Constants.CAN_ID_LOWER_SHOOTER_MASTER, Constants.CAN_ID_LOWER_SHOOTER_SLAVE);
	public static final ShooterRoller upperShooter = new ShooterRoller("UpperShooter", Constants.CAN_ID_UPPER_SHOOTER_MASTER, Constants.CAN_ID_UPPER_SHOOTER_SLAVE);
	public static final IntakeSystem intakeSystem = new IntakeSystem();
	public static final Winch winch = new Winch();
	
	public static final VisionSystem visionSystem = new VisionSystem();
	
	public static AHRS ahrs;
	
	public static OI oi;

    Command autonomousCommand;
    SendableChooser chooser;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	// Start up VisionSystem
    	visionSystem.initialize();
    	
    	// Set up CameraServer
    	
    	// Set up AHRS
    	try {
    		ahrs = new AHRS(I2C.Port.kMXP);
    	}
    	catch (RuntimeException e) {
    		DriverStation.reportError("Error instantiating navX MXP: " + e.getMessage(), true);
    	}
    	
		oi = new OI();
        chooser = new SendableChooser();
        chooser.addDefault("Turn To Vision Target", new AutoTurnToVisionTarget());
        chooser.addObject("Systems Test", new SystemsTest());
        chooser.addObject("Shooter Test", new ShooterTest());
        // chooser.addDefault("Default Auto", new ExampleCommand());
//        chooser.addObject("My Auto", new MyAutoCommand());
        SmartDashboard.putData("Auto mode", chooser);
        
        System.out.println("[ROBOT] Robot Initialized");
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
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
