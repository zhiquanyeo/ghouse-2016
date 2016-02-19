package org.usfirst.frc.team354.robot.commands.autonomous.base;

import org.usfirst.frc.team354.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoArcadeDriveForTime extends Command {
	private long d_startTime;
	private long d_driveDuration;
	private double d_driveVal;
	private double d_turnVal;
	
    public AutoArcadeDriveForTime(double driveVal, double turnVal, long driveTime) {
        d_driveDuration = driveTime;
        d_driveVal = driveVal;
        d_turnVal = turnVal;
        requires(Robot.driveSystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	d_startTime = System.currentTimeMillis();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveSystem.arcadeDrive(d_driveVal, d_turnVal);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (System.currentTimeMillis() - d_startTime) > d_driveDuration;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSystem.arcadeDrive(0.0, 0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
