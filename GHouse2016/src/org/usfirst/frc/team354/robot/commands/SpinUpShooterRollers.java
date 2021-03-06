package org.usfirst.frc.team354.robot.commands;

import org.usfirst.frc.team354.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SpinUpShooterRollers extends Command {
	private double rpmLower = -1;
	private double rpmUpper = -1;

    public SpinUpShooterRollers(double rpmLower, double rpmUpper) {
    	
    	this.rpmLower = rpmLower;
    	this.rpmUpper = rpmUpper;
    	
    	requires(Robot.lowerShooter);
    	requires(Robot.upperShooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.lowerShooter.setSpeed(rpmLower);
    	Robot.upperShooter.setSpeed(rpmUpper);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Robot.lowerShooter.getSpeed() == rpmLower && Robot.upperShooter.getSpeed() == rpmUpper);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
