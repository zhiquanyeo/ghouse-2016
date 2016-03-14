package org.usfirst.frc.team354.robot.commands.autonomous.base;

import org.usfirst.frc.team354.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoSpinUpShooterManualWhileWaiting extends Command {
	
	private double d_upperSpeed = 0;
	private double d_lowerSpeed = 0;
	private long d_timeToWait = 0;
	private long d_lastTime = 0;
	
    public AutoSpinUpShooterManualWhileWaiting(double upperSpd, double lowerSpd, long timeToWait) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	d_upperSpeed = upperSpd;
    	d_lowerSpeed = lowerSpd;
    	d_timeToWait = timeToWait;
    	
    	requires(Robot.upperShooter);
    	requires(Robot.lowerShooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	d_lastTime = System.currentTimeMillis();
    	Robot.upperShooter.setSpeed(-d_upperSpeed);
    	Robot.lowerShooter.setSpeed(d_lowerSpeed);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (System.currentTimeMillis() - d_lastTime > d_timeToWait);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
