package org.usfirst.frc.team354.robot.commands;

import org.usfirst.frc.team354.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OperatorSpinUpShooter extends Command {
	
	private static double[] s_upperSpeed = {0.0,   4456,  4504,   4407,   4553};
	private static double[] s_lowerSpeed = {0.0,   5812,  4916,   4819,   4625};
	private static double[] s_distances =  {84.0,  96.0,  108.0,  120.0,  132.0}; // in inches
											//      7ft    8ft     9ft     10ft

	private int d_rangeIdx = -1;
	
    public OperatorSpinUpShooter() {
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
    	// Spin speeds are based on range
    	double range = Robot.frontRangefinder.getRange();
    	int foundIdx = -1;
    	for (int i = s_distances.length - 1; i >= 0; i--) {
    		if (range >= s_distances[i]) {
    			foundIdx = i + 1;
    			break;
    		}
    	}
    	
    	if (foundIdx < 0 || foundIdx > s_distances.length - 1) {
    		// illegal value, don't start
    		d_rangeIdx = -1;
    	}
    	else {
    		if (foundIdx != d_rangeIdx) {
    			// different than before, spool it up
    			d_rangeIdx = foundIdx;
    			Robot.lowerShooter.setSpeed(s_lowerSpeed[d_rangeIdx]);
    			Robot.upperShooter.setSpeed(-s_upperSpeed[d_rangeIdx]);
    		}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.lowerShooter.setSpeed(0);
    	Robot.upperShooter.setSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.lowerShooter.setSpeed(0);
    	Robot.upperShooter.setSpeed(0);
    }
}
