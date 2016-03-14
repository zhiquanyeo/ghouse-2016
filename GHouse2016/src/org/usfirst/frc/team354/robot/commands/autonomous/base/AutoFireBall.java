package org.usfirst.frc.team354.robot.commands.autonomous.base;

import org.usfirst.frc.team354.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoFireBall extends Command {
	
	private long d_lastTime = 0;
	
    public AutoFireBall() {
        requires(Robot.intakeSystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	d_lastTime = System.currentTimeMillis();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intakeSystem.startUpperIntake(false);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (System.currentTimeMillis() - d_lastTime > 2000);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intakeSystem.stopUpperIntake();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.intakeSystem.stopUpperIntake();
    }
}
