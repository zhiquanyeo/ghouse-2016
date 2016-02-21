package org.usfirst.frc.team354.robot.commands;

import org.usfirst.frc.team354.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShootBallAtSpeed extends Command {

    public ShootBallAtSpeed() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	requires(Robot.intakeSystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (Robot.intakeSystem.ballPresent()) {
    		Robot.intakeSystem.startUpperIntake(false);
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	 return !Robot.intakeSystem.ballPresent();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intakeSystem.stopUpperIntake();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
