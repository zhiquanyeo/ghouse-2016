package org.usfirst.frc.team354.robot.commands;

import org.usfirst.frc.team354.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RaiseMainArmToAngle extends Command {
	
	private double d_targetAngle = 0;
    public RaiseMainArmToAngle(double angle) {
    	d_targetAngle = angle;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.mainArm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.mainArm.setAngle(d_targetAngle);
    	Robot.mainArm.getPIDController().enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return (Robot.mainArm.getPIDController().onTarget() || Math.abs(Robot.mainArm.getPIDController().getError()) < 1.0);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.mainArm.disable();
    	Robot.mainArm.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.mainArm.disable();
    	Robot.mainArm.stop();
    }
}
