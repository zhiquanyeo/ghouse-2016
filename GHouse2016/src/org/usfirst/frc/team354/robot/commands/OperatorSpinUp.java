package org.usfirst.frc.team354.robot.commands;

import org.usfirst.frc.team354.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OperatorSpinUp extends Command {

    public OperatorSpinUp() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.upperShooter);
    	requires(Robot.lowerShooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//For 10feet from front of wall
    	Robot.upperShooter.setSpeed(-4553);
    	Robot.lowerShooter.setSpeed(4625);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.upperShooter.setSpeed(0);
    	Robot.lowerShooter.setSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.upperShooter.setSpeed(0);
    	Robot.lowerShooter.setSpeed(0);
    }
}
