package org.usfirst.frc.team354.robot.commands;

import org.usfirst.frc.team354.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RaiseUpperArmToAngle extends Command {
	
	private double d_targetAngle;
	
    public RaiseUpperArmToAngle(double angle) {
    	d_targetAngle = angle;
    	requires(Robot.upperArm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.upperArm.setAngle(d_targetAngle);
    	Robot.upperArm.getPIDController().enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return (Robot.upperArm.getPIDController().onTarget() || Math.abs(Robot.upperArm.getPIDController().getError()) < 1.0);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.upperArm.disable();
    	Robot.upperArm.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.upperArm.disable();
    	Robot.upperArm.stop();
    }
}
