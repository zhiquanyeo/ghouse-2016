package org.usfirst.frc.team354.robot.commands.test;

import org.usfirst.frc.team354.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SystemsTest extends Command {

    public SystemsTest() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSystem);
    	requires(Robot.intakeSystem);
    	requires(Robot.lowerShooter);
    	requires(Robot.upperShooter);
    	requires(Robot.mainArm);
    	requires(Robot.upperArm);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putBoolean("Intake Ball Present", Robot.intakeSystem.ballPresent());
    	SmartDashboard.putNumber("Main Arm Angle", Robot.mainArm.getAngle());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
