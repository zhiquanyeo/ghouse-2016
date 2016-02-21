package org.usfirst.frc.team354.robot.commands.autonomous.base;

import org.usfirst.frc.team354.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Automatically turn to a target angle
 */
public class AutoTurnToAngle extends Command implements PIDOutput {
	
	private PIDController d_pidController;
	private double d_turnRate = 0.0;
	
	private double d_angle = 0.0;
	
	private static final double kP = 0.1;
	private static final double kI = 0.0;
	private static final double kD = 0.0;
	private static final double kF = 0.0;
	
	private static final double kToleranceDegrees = 2.0;
	
    public AutoTurnToAngle(double angle) {
    	d_angle = 0.0;
    	d_pidController = new PIDController(kP, kI, kD, kF, Robot.ahrs, this);
    	d_pidController.setInputRange(-180.0, 180.0);
    	d_pidController.setOutputRange(-1.0, 1.0);
    	d_pidController.setAbsoluteTolerance(kToleranceDegrees);
    	d_pidController.setContinuous(true);
    	
    	requires(Robot.driveSystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	// Reset gyro to 0
    	Robot.ahrs.reset();
    	d_pidController.setSetpoint(d_angle);
    	d_pidController.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// TODO: Verify that this is correct
    	Robot.driveSystem.tankDrive(d_turnRate, -d_turnRate);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return d_pidController.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSystem.tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

	@Override
	public void pidWrite(double output) {
		// Everytime the PIDController tells us to output a value
		// save it, and adjust speed in execute()
		d_turnRate = output;
	}
}
