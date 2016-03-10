package org.usfirst.frc.team354.robot.subsystems;

import org.usfirst.frc.team354.robot.Constants;
import org.usfirst.frc.team354.robot.commands.OperatorUpperArmControl;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class UpperArm extends PIDSubsystem {
	
	public static final double ARM_FULLY_LOWERED = 0.0;
	public static final double ARM_FULLY_EXTENDED = 180.0;
	
	private CANTalon d_motor = new CANTalon(Constants.CAN_ID_UPPER_ARM);
	private AnalogInput d_armAnalogIn = new AnalogInput(Constants.AIN_UPPER_ARM_POT);
	private AnalogPotentiometer d_armPot = new AnalogPotentiometer(d_armAnalogIn, 360, 0);
	
	private double d_setAngle = 0.0;
	
    // Initialize your subsystem here
    public UpperArm() {
    	super("UpperArm", 0.01, 0.0, 0.0);
    	
    	getPIDController().setContinuous(false);
    	getPIDController().setAbsoluteTolerance(1.0); // 1 degree tolerance
    	//enable();
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	
    	LiveWindow.addActuator("UpperArm", "Upper Arm Motor", d_motor);
    	LiveWindow.addSensor("UpperArm", "Upper Arm Potentiometer", d_armPot);
    }
    
    public void setAngle(double angle) {
    	// Enable the PID controller
    	if (!this.getPIDController().isEnabled()) {
    		enable();
    	}
    	d_setAngle = angle;
    	setSetpoint(d_setAngle);
    }
    
    public double getSetpointAngle() {
    	return d_setAngle;
    }
    
    public double getAvgError() {
    	return this.getPIDController().getAvgError();
    }
    
    public void disablePID() {
    	disable();
    }
    
    public double getAngle() {
    	return d_armPot.get();
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new OperatorUpperArmControl());
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	return d_armPot.get();
    }
    
    public void raiseAtSpeed(double speed) {
    	speed = Math.abs(speed);
    	d_motor.set(speed);
    }
    
    public void lowerAtSpeed(double speed) {
    	speed = Math.abs(speed);
    	d_motor.set(-speed);
    }
    
    public void stop() {
    	d_motor.set(0);
    }
    
    protected void usePIDOutput(double output) {
        d_motor.pidWrite(output);
    }
}
