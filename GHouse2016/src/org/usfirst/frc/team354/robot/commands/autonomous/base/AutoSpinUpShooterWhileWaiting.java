package org.usfirst.frc.team354.robot.commands.autonomous.base;

import org.usfirst.frc.team354.robot.Constants;
import org.usfirst.frc.team354.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoSpinUpShooterWhileWaiting extends Command {

	private int d_rangeIdx = -1;
	private long d_timeToWait = 0;
	private long d_lastTime = 0;
	
    public AutoSpinUpShooterWhileWaiting(long time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.lowerShooter);
    	requires(Robot.upperShooter);
    	
    	d_timeToWait = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	d_lastTime = System.currentTimeMillis();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// Spin speeds are based on range
    	double range = Robot.frontRangefinder.getRange();
    	int foundIdx = -1;
    	for (int i = Constants.SHOOTER_DISTANCES.length - 1; i >= 0; i--) {
    		if (range >= Constants.SHOOTER_DISTANCES[i]) {
    			foundIdx = i + 1;
    			break;
    		}
    	}
    	
    	if (foundIdx < 0 || foundIdx > Constants.SHOOTER_DISTANCES.length - 1) {
    		// illegal value, don't start
    		d_rangeIdx = -1;
    	}
    	else {
    		if (foundIdx != d_rangeIdx) {
    			// different than before, spool it up
    			d_rangeIdx = foundIdx;
    			Robot.lowerShooter.setSpeed(Constants.LOWER_SHOOTER_SPEED[d_rangeIdx]);
    			Robot.upperShooter.setSpeed(-Constants.UPPER_SHOOTER_SPEED[d_rangeIdx]);
    		}
    	}
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
    	Robot.lowerShooter.setSpeed(0);
    	Robot.upperShooter.setSpeed(0);
    }
}
