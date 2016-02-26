package org.usfirst.frc.team354.robot.subsystems;

import org.usfirst.frc.team354.robot.Constants;

import edu.wpi.first.wpilibj.AnalogInput;
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
	private AnalogInput d_armPot = new AnalogInput(Constants.AIN_UPPER_ARM_POT);
	
	private double d_setAngle = 0.0;
	
    // Initialize your subsystem here
    public UpperArm() {
    	super("UpperArm", 0.01, 0.0, 0.0);
    	
    	getPIDController().setContinuous(false);
    	enable();
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	
    	LiveWindow.addActuator("UpperArm", "Upper Arm Motor", d_motor);
    	LiveWindow.addSensor("UpperArm", "Upper Arm Potentiometer", d_armPot);
    }
    
    public void setAngle(double angle) {
    	d_setAngle = angle;
    	setSetpoint(d_setAngle);
    }
    
    public double getAngle() {
    	return d_armPot.getAverageVoltage();
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	// TODO: Convert voltage to degrees
    	return d_armPot.getAverageVoltage();
    }
    
    protected void usePIDOutput(double output) {
        d_motor.pidWrite(output);
    }
}
