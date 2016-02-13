package org.usfirst.frc.team354.robot.subsystems;

import org.usfirst.frc.team354.robot.Constants;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class UpperArm extends PIDSubsystem {

	private CANTalon d_motor = new CANTalon(Constants.CAN_ID_UPPER_ARM);
	private AnalogInput d_armPot = new AnalogInput(Constants.AIN_UPPER_ARM_POT);
	
    // Initialize your subsystem here
    public UpperArm() {
    	super("UpperArm", 0.01, 0.0, 0.0);
    	
    	getPIDController().setContinuous(false);
    	enable();
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
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
