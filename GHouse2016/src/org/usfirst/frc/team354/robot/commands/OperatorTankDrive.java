package org.usfirst.frc.team354.robot.commands;

import org.usfirst.frc.team354.robot.Constants;
import org.usfirst.frc.team354.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OperatorTankDrive extends Command {
	private double maxSpeed = 1.0;
	
    public OperatorTankDrive(double maxSpeed) {
    	if (maxSpeed > 1.0) {
    		maxSpeed = 1.0;
    	}
    	if (maxSpeed < 0.0) {
    		maxSpeed = 0.0;
    	}
    	
    	this.maxSpeed = maxSpeed;
    	
        requires(Robot.driveSystem);
    }
    
    public String getName() {
    	return "OperatorTankDrive - " + maxSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double leftSpeed = expo(Robot.oi.getDriverStickLeftY(), Constants.DRIVE_EXPO_VALUE);
    	double rightSpeed = expo(Robot.oi.getDriverStickRightY(), Constants.DRIVE_EXPO_VALUE);
    	
    	leftSpeed *= maxSpeed;
    	rightSpeed *= maxSpeed;
    	
    	Robot.driveSystem.tankDrive(leftSpeed, rightSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    private double expo(double input, double expoValue) {
    	double multiplier = 1.0;
    	if (input < 0) {
    		input = input * -1.0;
    		multiplier = -1.0;
    	}
    	double yVal = (Math.exp(expoValue * input) - 1) / (Math.exp(expoValue) - 1);
    	return multiplier * yVal;
    }
}
