package org.usfirst.frc.team354.robot.subsystems;

import org.usfirst.frc.team354.robot.Constants;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Winch extends Subsystem {
    
	private CANTalon d_motor = new CANTalon(Constants.CAN_ID_WINCH);
	private DigitalInput d_limitSwitch = new DigitalInput(Constants.DIN_WINCH_LIMIT_SWITCH);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void startWinch() {
    	// TODO Don't start if the limit switch is set
    	d_motor.set(0.75);
    }
    
    public void stopWinch() {
    	d_motor.set(0.0);
    }
    
    public boolean atLimit() {
    	return !d_limitSwitch.get();
    }
}

