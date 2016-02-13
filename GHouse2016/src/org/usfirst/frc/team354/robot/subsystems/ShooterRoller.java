package org.usfirst.frc.team354.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * A class representing a Shooter mechanism controlled by 2 Talon SRX controllers
 * 
 * The primary motor controller will be connected to a CTRE Magnetic Encoder and 
 * will utilize the Closed Loop PID functionality
 */
public class ShooterRoller extends Subsystem {
    
	private CANTalon d_motorMaster;
	private CANTalon d_motorSlave;
	
	private double d_speed = 0.0;
	
	public ShooterRoller(int masterId, int slaveId) {
		this(masterId, slaveId, false);
	}
	
	public ShooterRoller(int masterId, int slaveId, boolean reverse) {
		d_motorMaster = new CANTalon(masterId);
		d_motorSlave = new CANTalon(slaveId);
		
		// Set up slave mode
		d_motorSlave.changeControlMode(TalonControlMode.Follower);
		d_motorSlave.set(d_motorMaster.getDeviceID());
		
		// Set up feedback+closed loop PID for the master
		d_motorMaster.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		d_motorMaster.reverseSensor(false); // TBD: Set to true if we need to
		
		// Set peak and nominal outputs
		d_motorMaster.configNominalOutputVoltage(0.0, 0.0);
		d_motorMaster.configPeakOutputVoltage(12.0, 0.0);
		
		// Set closed loop profile and PID gains
		d_motorMaster.setProfile(0);
		d_motorMaster.setF(0.1097);
		d_motorMaster.setP(0.22);
		d_motorMaster.setI(0.0);
		d_motorMaster.setD(0.0);
		
		// Set the master to run in 'speed' mode, which allows us to specify pure RPMs
		d_motorMaster.changeControlMode(TalonControlMode.Speed);
		d_motorMaster.set(0.0);
		
		d_motorMaster.reverseOutput(reverse);
	}
    
	/**
	 * Set the speed of the shooter (in RPM)
	 * @param speed Requested speed
	 */
	public void setSpeed(double speed) {
		d_speed = speed;
		d_motorMaster.set(d_speed);
	}
	
	/**
	 * Get the speed this shooter is set to
	 * @return Currently requested speed
	 */
	public double getSpeed() {
		return d_speed;
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

