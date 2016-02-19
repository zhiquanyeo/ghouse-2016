package org.usfirst.frc.team354.robot.commands.autonomous.base;

import org.usfirst.frc.team354.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoTankDriveForTime extends Command {
	private long d_startTime;
	private long d_driveTime;
	private double d_leftSpeed;
	private double d_rightSpeed;
	
    public AutoTankDriveForTime(double leftSpeed, double rightSpeed, long driveDuration) {
        d_driveTime = driveDuration;
        d_leftSpeed = leftSpeed;
        d_rightSpeed = rightSpeed;
        
        requires(Robot.driveSystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	d_startTime = System.currentTimeMillis();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveSystem.tankDrive(d_leftSpeed, d_rightSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (System.currentTimeMillis() - d_startTime) > d_driveTime;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSystem.tankDrive(0.0, 0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
