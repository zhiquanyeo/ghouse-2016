package org.usfirst.frc.team354.robot.subsystems;

import org.usfirst.frc.team354.robot.Constants;
import org.usfirst.frc.team354.robot.commands.OperatorWinchControl;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class Winch extends Subsystem {
    
	private CANTalon d_motor = new CANTalon(Constants.CAN_ID_WINCH);
	private DigitalInput d_lowerLimitSwitch = new DigitalInput(Constants.DIN_WINCH_LOWER_LIMIT_SWITCH);
	private DigitalInput d_barContactSwitch = new DigitalInput(Constants.DIN_WINCH_HOOK_BAR_CONTACT_SWITCH);
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public Winch() {
		LiveWindow.addActuator("Winch", "Winch Motor", d_motor);
		LiveWindow.addSensor("Winch", "Winch Lower Limit Switch", d_lowerLimitSwitch);
		LiveWindow.addSensor("Winch", "Winch Hook-to-Bar Contact Switch", d_barContactSwitch);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//setDefaultCommand(new OperatorWinchControl());
    }
    
    public void driveWinch(double speed) {
    	d_motor.set(speed);
    }
    
    public void startWinch() {
    	// TODO Don't start if the limit switch is set
    	if (!atLowerLimit()) {
    		d_motor.set(0.75);
    	}
    	else {
    		d_motor.set(0);
    	}
    }
    
    public void stopWinch() {
    	d_motor.set(0.0);
    }
    
    public boolean atLowerLimit() {
    	return !d_lowerLimitSwitch.get();
    }
    
    public boolean hookContactingBar() {
    	return !d_barContactSwitch.get();
    }
}

