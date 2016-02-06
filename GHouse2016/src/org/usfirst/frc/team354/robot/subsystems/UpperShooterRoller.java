package org.usfirst.frc.team354.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class UpperShooterRoller extends PIDSubsystem {
	private static final int CAN_SRX_MASTER = 24;
	private static final int CAN_SRX_SLAVE = 25;
	
	private CANTalon d_motorMaster = new CANTalon(CAN_SRX_MASTER);
	private CANTalon d_motorSlave = new CANTalon(CAN_SRX_SLAVE);
	
    // Initialize your subsystem here
    public UpperShooterRoller() {
    	// P, I, D, F
    	super("UpperShooterRoller", 1.0, 0.0, 0.0, 0.025);
    	
    	// Set up slave mode
    	d_motorSlave.changeControlMode(TalonControlMode.Follower);
    	d_motorSlave.set(d_motorMaster.getDeviceID());
    	
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
    	return d_motorMaster.getSpeed();
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	d_motorMaster.pidWrite(output);
    }
}
