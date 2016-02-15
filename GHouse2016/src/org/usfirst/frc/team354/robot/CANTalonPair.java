package org.usfirst.frc.team354.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.MotorSafety;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedController;

public class CANTalonPair implements SpeedController, MotorSafety, PIDOutput {

	private CANTalon d_master;
	private CANTalon d_slave;
	
	private double d_masterMaxRPM;
	private double d_slaveMaxRPM;
	
	private double d_masterMultiplier = 1.0;
	private double d_slaveMultiplier = 1.0;
	
	private double d_masterFactor = d_slaveMaxRPM / d_masterMaxRPM;
	private double d_slaveFactor = d_masterMaxRPM / d_slaveMaxRPM;
	// Master = d_slaveFactor * Slave
	
	private boolean d_isInverted = false;
	private double d_setSpeed = 0.0;
	
	public CANTalonPair(int masterChannel, int slaveChannel, double masterMaxRPM, double slaveMaxRPM) {
		this(masterChannel, slaveChannel, masterMaxRPM, slaveMaxRPM, false, false);
	}
	
	public CANTalonPair(int masterChannel, int slaveChannel, double masterMaxRPM, double slaveMaxRPM, boolean reverseMaster, boolean reverseSlave) {
		d_master = new CANTalon(masterChannel);
		d_slave = new CANTalon(slaveChannel);
		
		d_masterMaxRPM = masterMaxRPM;
		d_slaveMaxRPM = slaveMaxRPM;
		
		if (d_slaveMaxRPM > d_masterMaxRPM) {
			// Clamp to Master
			d_masterFactor = 1.0;
		}
		else if (d_slaveMaxRPM < d_masterMaxRPM) {
			// Clamp to Slave
			d_slaveFactor = 1.0;
		}
		else {
			// Run both at the same speed
			d_masterFactor = 1.0;
			d_slaveFactor = 1.0;
		}
		
		if (reverseMaster) {
			d_masterMultiplier = -1.0;
		}
		
		if (reverseSlave) {
			d_slaveMultiplier = -1.0;
		}
	}
	
	protected void setTalonOutput(double output) {
		d_setSpeed = output;
		d_master.set(output * d_masterFactor * d_masterMultiplier * (d_isInverted ? -1.0 : 1.0));
		d_slave.set(output * d_slaveFactor * d_slaveMultiplier * (d_isInverted ? -1.0 : 1.0));
	}
	
	@Override
	public void pidWrite(double output) {
		setTalonOutput(output);
	}

	@Override
	public void setExpiration(double timeout) {
		d_master.setExpiration(timeout);
		d_slave.setExpiration(timeout);
	}

	@Override
	public double getExpiration() {
		return d_master.getExpiration();
	}

	@Override
	public boolean isAlive() {
		return d_master.isAlive();
	}

	@Override
	public void stopMotor() {
		disable();
	}

	@Override
	public void setSafetyEnabled(boolean enabled) {
		d_master.setSafetyEnabled(enabled);
		d_slave.setSafetyEnabled(enabled);
	}

	@Override
	public boolean isSafetyEnabled() {
		return d_master.isSafetyEnabled();
	}

	@Override
	public String getDescription() {
		return "CANTalonPair (Master@" + d_masterMaxRPM + ", Slave@" + d_slaveMaxRPM + ")";
	}

	@Override
	public double get() {
		return d_setSpeed;
	}

	@Override
	public void set(double speed, byte syncGroup) {
		set(speed);
	}

	@Override
	public void set(double speed) {
		setTalonOutput(speed);
	}

	@Override
	public void setInverted(boolean isInverted) {
		d_isInverted = isInverted;
	}

	@Override
	public boolean getInverted() {
		return d_isInverted;
	}

	@Override
	public void disable() {
		d_master.disable();
		d_slave.disable();
	}
	
	public double getSpeed() {
		return d_master.getSpeed();
	}
}
