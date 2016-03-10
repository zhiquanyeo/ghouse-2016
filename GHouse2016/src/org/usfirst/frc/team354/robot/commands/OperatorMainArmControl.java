package org.usfirst.frc.team354.robot.commands;

import org.usfirst.frc.team354.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OperatorMainArmControl extends Command {

    public OperatorMainArmControl() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.mainArm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double value = Robot.oi.getGamepadRightStickY();
    	if (value < 0) {
    		value *= 0.75;
    		Robot.mainArm.raiseAtSpeed(-value);
    	}
    	else if (value > 0) {
    		value *= 0.4;
    		if (!Robot.mainArm.isFullyLowered()) {
    			Robot.mainArm.lowerAtSpeed(value);
    		}
    		else {
    			Robot.mainArm.stop();
    		}
    	}
    	else {
    		Robot.mainArm.stop();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.mainArm.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.mainArm.stop();
    }
}
