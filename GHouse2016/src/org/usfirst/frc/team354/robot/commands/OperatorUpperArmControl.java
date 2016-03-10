package org.usfirst.frc.team354.robot.commands;

import org.usfirst.frc.team354.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OperatorUpperArmControl extends Command {

    public OperatorUpperArmControl() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.upperArm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double value = Robot.oi.getGamepadLeftStickY();
    	if (value < 0) {
    		value *= 1;
    		Robot.upperArm.raiseAtSpeed(-value);
    	}
    	else if (value > 0) {
    		value *= 1;
    		Robot.upperArm.lowerAtSpeed(value);
    	}
    	else {
    		Robot.upperArm.stop();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.upperArm.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.upperArm.stop();
    }
}
