package org.usfirst.frc.team354.robot.commands;

import org.usfirst.frc.team354.robot.Constants;
import org.usfirst.frc.team354.robot.Robot;
import org.usfirst.frc.team354.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OperatorTankDrive extends Command {
	private double d_maxSpeed = 1.0;
	private boolean d_isLowGear = false;
	private boolean d_isReverse = false;
	
	public OperatorTankDrive(double maxSpeed) {
		this(maxSpeed, false);
	}
	
	public OperatorTankDrive(double maxSpeed, boolean reverse) {
		d_isReverse = reverse;
    	if (maxSpeed > 1.0) {
    		maxSpeed = 1.0;
    	}
    	if (maxSpeed < 0.0) {
    		maxSpeed = 0.0;
    	}
    		
    	d_maxSpeed = maxSpeed;
    	d_isLowGear = true;
    	
        requires(Robot.driveSystem);
    }
    
    public String getName() {
    	return "OperatorTankDrive - " + d_maxSpeed + " " + (d_isLowGear ? "(LOW GEAR)" : "(HIGH GEAR)");
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (d_isLowGear) {
    		Robot.driveSystem.setLowGear();
    	}
    	else {
    		Robot.driveSystem.setHighGear();
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double leftSpeed = expo(-Robot.oi.getLeftStickY(), Constants.DRIVE_EXPO_VALUE);
    	double rightSpeed = expo(-Robot.oi.getRightStickY(), Constants.DRIVE_EXPO_VALUE);
    	
    	if (d_isReverse) {
    		leftSpeed = -leftSpeed;
    		rightSpeed = -rightSpeed;
    	}
    	
    	// If the right stick trigger is depressed, set to HIGH gear
    	if (d_isLowGear && Robot.oi.getButtonState(RobotMap.rightJoystick, 1)) {
    		Robot.driveSystem.setHighGear();
    		d_isLowGear = false;
    	}
    	else if (!d_isLowGear && !Robot.oi.getButtonState(RobotMap.rightJoystick, 1)) {
    		// we were in high gear and button is released
    		Robot.driveSystem.setLowGear();
    		d_isLowGear = true;
    	}
    	
    	leftSpeed *= d_maxSpeed;
    	rightSpeed *= d_maxSpeed;
    	
    	if (d_isReverse) {
    		Robot.driveSystem.tankDrive(rightSpeed, leftSpeed);
    	}
    	else {
    		Robot.driveSystem.tankDrive(leftSpeed, rightSpeed);
    	}
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
