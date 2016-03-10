package org.usfirst.frc.team354.robot.commands.test;

import org.usfirst.frc.team354.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ShooterTest extends Command {

    public ShooterTest() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.lowerShooter);
    	requires(Robot.upperShooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.lowerShooter.setSpeed(-Robot.oi.getDriverStickLeftY() * 100);
    	//Robot.upperShooter.setSpeed(-Robot.oi.getDriverStickRightY() * 100);
    	
    	SmartDashboard.putNumber("Lower Shooter Speed", Robot.lowerShooter.getSpeed());
    	SmartDashboard.putNumber("Upper Shooter Speed", Robot.upperShooter.getSpeed());
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
