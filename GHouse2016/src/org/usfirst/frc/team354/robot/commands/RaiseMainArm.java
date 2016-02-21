package org.usfirst.frc.team354.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team354.robot.Robot;

/**
 *
 */
public class RaiseMainArm extends Command {

    public RaiseMainArm() {
        
    	requires(Robot.mainArm);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.mainArm.raise();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
