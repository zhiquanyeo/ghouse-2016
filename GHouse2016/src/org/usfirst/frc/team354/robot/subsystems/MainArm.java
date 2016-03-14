package org.usfirst.frc.team354.robot.subsystems;

import org.usfirst.frc.team354.robot.Constants;
import org.usfirst.frc.team354.robot.commands.OperatorMainArmControl;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class MainArm extends PIDSubsystem {
	
	private CANTalon d_motor = new CANTalon(Constants.CAN_ID_MAIN_ARM);
	private AnalogInput d_armAnalogIn = new AnalogInput(Constants.AIN_MAIN_ARM_POT);
	private AnalogPotentiometer d_armPot = new AnalogPotentiometer(d_armAnalogIn, -360.0, 156.0);
	private DigitalInput d_lowerLimitSwitch = new DigitalInput(Constants.DIN_MAIN_ARM_LOWER_LIMIT_SWITCH);
	
	public static final double ARM_FULLY_LOWERED = 0.0;
	public static final double ARM_BALL_INTAKE = 7.9; // CORRECT VALUE was 8.9
	public static final double ARM_CLIMB_INITIAL = 95.0;
	public static final double ARM_CLIMB_CURL = 66.0;
	public static final double ARM_STARTING_POSITION = 66.0; // CORRECT VALUE
	
	// Positive Speed = UP
	public static final double ARM_RAISE_SPEED = 0.8; //was 0.5
	public static final double ARM_LOWER_SPEED = -0.8; //was -0.5
	
	private double d_setAngle = 0.0;
	
    // Initialize your subsystem here
    public MainArm() {
    	super("MainArm", 0.1, 0.0, 0.0); // P, I, D
    	
    	getPIDController().setContinuous(false);
    	getPIDController().setAbsoluteTolerance(1.0); // 1 degree tolerance
    	//enable();
    	
    	LiveWindow.addActuator("MainArm", "Main Arm Motor", d_motor);
    	LiveWindow.addSensor("MainArm", "Main Arm Potentiometer", d_armPot);
    }
    
    public void setAngle(double angle) {
    	d_setAngle = angle;
    	enable();
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
    
    public boolean isSafeToMove() {
    	return true;
    }
    
    public void raise() {
    	disable(); // Disable PID when moving manually
    	if (!isSafeToMove()) {
    		return;
    	}
    	
    	d_motor.set(ARM_RAISE_SPEED);
    }
    
    public void lower() {
    	disable(); // Disable PID when moving manually
    	if (!isSafeToMove()) {
    		return;
    	}
    	
    	if (!isFullyLowered()) {
    		d_motor.set(ARM_LOWER_SPEED);
    	}
    	else {
    		d_motor.set(0);
    	}
    }
    
    public void raiseAtSpeed(double speed) {
    	disable(); // Disable PID when raising manually
    	if (!isSafeToMove()) {
    		return;
    	}
    	
    	speed = Math.abs(speed);
    	d_motor.set(speed);
    }
    
    public void lowerAtSpeed(double speed) {
    	disable(); // Disable PID when raising manually
    	if (!isSafeToMove()) {
    		return;
    	}
    	
    	speed = Math.abs(speed);
    	// Safety! Don't lower past the hard-stop
    	if (!isFullyLowered()) {
    		d_motor.set(-speed);
    	}
    	else {
    		d_motor.set(0);
    	}
    }
    
    public void stop() {
    	d_motor.set(0);
    }
    
    public boolean isFullyLowered() {
    	return !d_lowerLimitSwitch.get();
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new OperatorMainArmControl());
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	return d_armPot.get();
    }
    
    protected void usePIDOutput(double output) {
    	if (output < 0 && isFullyLowered()) {
    		d_motor.pidWrite(0);
    	}
    	else if (output > 0 && d_armPot.get() > 110) {
    		d_motor.pidWrite(0);
    	}
    	else {
    		d_motor.pidWrite(output);
    	}
    }
}
