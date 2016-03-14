package org.usfirst.frc.team354.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BusyLoop extends Command {
	
	double d_timeToWait = 0;
	double d_lastTime = 0;
    public BusyLoop(double time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	d_timeToWait = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	d_lastTime = System.currentTimeMillis();
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
