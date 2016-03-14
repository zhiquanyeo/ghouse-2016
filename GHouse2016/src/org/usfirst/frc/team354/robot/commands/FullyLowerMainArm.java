package org.usfirst.frc.team354.robot.commands;

import org.usfirst.frc.team354.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FullyLowerMainArm extends Command {

    public FullyLowerMainArm() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.mainArm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.mainArm.isSafeToMove()) {
    		Robot.mainArm.lower();
    	}
    	else {
    		Robot.mainArm.stop();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.mainArm.isFullyLowered();
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