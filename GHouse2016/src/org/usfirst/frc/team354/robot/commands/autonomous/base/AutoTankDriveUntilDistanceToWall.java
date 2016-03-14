package org.usfirst.frc.team354.robot.commands.autonomous.base;

import org.usfirst.frc.team354.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoTankDriveUntilDistanceToWall extends Command {
	
	private double d_targetDistanceAway = 0;
    public AutoTankDriveUntilDistanceToWall(double distAway) {
    	d_targetDistanceAway = distAway;
    	requires(Robot.driveSystem);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveSystem.tankDrive(0.5, 0.5);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.frontRangefinder.getRange() < d_targetDistanceAway;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSystem.tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveSystem.tankDrive(0, 0);
    }
}
