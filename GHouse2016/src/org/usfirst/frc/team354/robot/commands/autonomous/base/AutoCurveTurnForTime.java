package org.usfirst.frc.team354.robot.commands.autonomous.base;

import org.usfirst.frc.team354.robot.Constants;
import org.usfirst.frc.team354.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoCurveTurnForTime extends Command {
	
	private int d_direction = 0;
	private double d_timeToTurn = 0;
	
	private double d_leftSpeed = 0;
	private double d_rightSpeed = 0;
	
	private long d_lastTime = 0;
	
    public AutoCurveTurnForTime(int direction, double timeMs) {
        d_direction = direction;
        d_timeToTurn = timeMs;
        
        if (d_direction == Constants.DIR_TURN_LEFT) {
        	d_rightSpeed = 0.5;
        }
        else if (d_direction == Constants.DIR_TURN_RIGHT) {
        	d_leftSpeed = 0.5;
        }
        
        requires(Robot.driveSystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	d_lastTime = System.currentTimeMillis();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveSystem.tankDrive(d_leftSpeed, d_rightSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (System.currentTimeMillis() - d_lastTime > d_timeToTurn);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSystem.tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveSystem.tankDrive(0, 0);
    }
}
