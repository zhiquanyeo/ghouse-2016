package org.usfirst.frc.team354.robot.subsystems;

import org.usfirst.frc.team354.robot.Constants;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class MainArm extends PIDSubsystem {
	
	private CANTalon d_motor = new CANTalon(Constants.CAN_ID_MAIN_ARM);
	private AnalogInput d_armPot = new AnalogInput(Constants.AIN_MAIN_ARM_POT);
	
	public static final double ARM_FULLY_LOWERED = 0.0;
	public static final double ARM_BALL_INTAKE = 20.0;
	public static final double ARM_CLIMB_INITIAL = 95.0;
	public static final double ARM_CLIMB_CURL = 66.0;
	public static final double ARM_RAISE_SPEED = 0.5;
	public static final double ARM_LOWER_SPEED = -0.3;
	
	private double d_setAngle = 0.0;
	
    // Initialize your subsystem here
    public MainArm() {
    	super("MainArm", 0.1, 0.0, 0.0); // P, I, D
    	
    	getPIDController().setContinuous(false);
    	enable();
    	
    	LiveWindow.addActuator("MainArm", "Main Arm Motor", d_motor);
    	LiveWindow.addSensor("MainArm", "Main Arm Potentiometer", d_armPot);
    }
    
    public void setAngle(double angle) {
    	d_setAngle = 0.0;
    	setSetpoint(d_setAngle);
    }
    
    public double getAngle() {
    	return d_armPot.getAverageVoltage();
    }
    
    public void raise() {
    	d_motor.set(ARM_RAISE_SPEED);
    }
    
    public void lower() {
    	d_motor.set(ARM_LOWER_SPEED);
    }
    
    public void stop() {
    	d_motor.set(0);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	return d_armPot.getAverageVoltage();
    }
    
    protected void usePIDOutput(double output) {
        d_motor.pidWrite(output);
    }
}
