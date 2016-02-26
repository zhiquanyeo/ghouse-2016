package org.usfirst.frc.team354.robot.subsystems;

import org.usfirst.frc.team354.robot.Constants;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class IntakeSystem extends Subsystem {
	
	private static final double FORWARD_FEED_SPEED = 0.5;
	private static final double REVERSE_FEED_SPEED = -0.5;
	
    private CANTalon d_lowerMotor = new CANTalon(Constants.CAN_ID_LOWER_INTAKE);
    private CANTalon d_upperMotor = new CANTalon(Constants.CAN_ID_UPPER_INTAKE);
    private DigitalInput d_beamBreak = new DigitalInput(Constants.DIN_INTAKE_BEAMBREAK);
    
    public IntakeSystem() {
    	LiveWindow.addActuator("Intake", "Upper Motor", d_upperMotor);
    	LiveWindow.addActuator("Intake", "Lower Motor", d_lowerMotor);
    	LiveWindow.addSensor("Intake", "Ball Presence", d_beamBreak);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void startLowerIntake(boolean reverse) {
    	if (reverse) {
    		d_lowerMotor.set(REVERSE_FEED_SPEED);
    	}
    	else {
    		d_lowerMotor.set(FORWARD_FEED_SPEED);
    	}
    }
    
    public void startUpperIntake(boolean reverse) {
    	if (reverse) {
    		d_upperMotor.set(REVERSE_FEED_SPEED);
    	}
    	else {
    		d_upperMotor.set(FORWARD_FEED_SPEED);
    	}
    }
    
    public void stopLowerIntake() {
    	d_lowerMotor.set(0);
    }
    
    public void stopUpperIntake() {
    	d_upperMotor.set(0);
    }
    
    public boolean ballPresent() {
    	// Beam Break switch returns LOW when the beam is broken
    	return !d_beamBreak.get();
    }
}

