package org.usfirst.frc.team354.robot.commands;

import org.usfirst.frc.team354.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OperatorIntakeControl extends Command {
	
	private boolean d_reverse = false;
	
	public OperatorIntakeControl() {
		this(false);
	}
	
    public OperatorIntakeControl(boolean reverse) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intakeSystem);
    	d_reverse = reverse;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intakeSystem.startLowerIntake(d_reverse);
    	if (!d_reverse && !Robot.intakeSystem.ballPresent()) {
    		Robot.intakeSystem.startUpperIntake(d_reverse);
    	}
    	else if (d_reverse) {
    		Robot.intakeSystem.startUpperIntake(d_reverse);
    	}
    	else {
    		Robot.intakeSystem.stopUpperIntake();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (!d_reverse) {
    		// If we are intaking a ball, keep going until the ball is in
    		return Robot.intakeSystem.ballPresent();
    	}
    	else {
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intakeSystem.stopLowerIntake();
    	Robot.intakeSystem.stopUpperIntake();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.intakeSystem.stopLowerIntake();
    	Robot.intakeSystem.stopUpperIntake();
    }
}
