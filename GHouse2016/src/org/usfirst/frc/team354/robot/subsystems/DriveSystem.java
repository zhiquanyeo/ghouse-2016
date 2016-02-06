package org.usfirst.frc.team354.robot.subsystems;

import org.usfirst.frc.team354.robot.commands.OperatorTankDrive;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The DriveSystem for the 2016 G-House Pirates Robot consists of 
 * 4 TalonSRX Speed controllers operating over CAN.
 * 
 * 2 motors power each side, with one motor per side designated as
 * 'master' and the other as 'slave'. The 'master' TalonSRX will have
 * control over any sensors, and the 'slave' device will just follow
 * the set output of the 'master'.
 */
public class DriveSystem extends Subsystem {
    
	private static final int CAN_SRX_LEFT_MASTER = 20;
	private static final int CAN_SRX_LEFT_SLAVE = 21;
	private static final int CAN_SRX_RIGHT_MASTER = 22;
	private static final int CAN_SRX_RIGHT_SLAVE = 23;
	
	private CANTalon d_leftMaster;
	private CANTalon d_leftSlave;
	private CANTalon d_rightMaster;
	private CANTalon d_rightSlave;
	
	private RobotDrive d_driveSystem;
	
	public DriveSystem() {
		d_leftMaster = new CANTalon(CAN_SRX_LEFT_MASTER);
		d_leftSlave = new CANTalon(CAN_SRX_LEFT_SLAVE);
		d_rightMaster = new CANTalon(CAN_SRX_RIGHT_MASTER);
		d_rightSlave = new CANTalon(CAN_SRX_RIGHT_SLAVE);
		
		d_leftSlave.changeControlMode(TalonControlMode.Follower);
		d_leftSlave.set(d_leftMaster.getDeviceID());
		
		d_rightSlave.changeControlMode(TalonControlMode.Follower);
		d_rightSlave.set(d_rightMaster.getDeviceID());
		
		d_driveSystem = new RobotDrive(d_leftMaster, d_rightMaster);
		
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	// Set the default command that will run for this
    	// For now, we will set it up as tank drive
        setDefaultCommand(new OperatorTankDrive(0.75));
    }
    
    public void tankDrive(double leftValue, double rightValue) {
    	d_driveSystem.tankDrive(leftValue, rightValue);
    }
    
    public void tankDrive(GenericHID leftStick, GenericHID rightStick) {
    	d_driveSystem.tankDrive(leftStick, rightStick);
    }
    
    public void tankDrive(double leftValue, double rightValue, boolean squaredInputs) {
    	d_driveSystem.tankDrive(leftValue, rightValue, squaredInputs);
    }
    
    public void tankDrive(GenericHID leftStick, GenericHID rightStick, boolean squaredInputs) {
    	d_driveSystem.tankDrive(leftStick, rightStick, squaredInputs);
    }
    
    public void arcadeDrive(double moveValue, double rotateValue) {
    	d_driveSystem.arcadeDrive(moveValue, rotateValue);
    }
    
    public void arcadeDrive(double moveValue, double rotateValue, boolean squaredInputs) {
    	d_driveSystem.arcadeDrive(moveValue, rotateValue, squaredInputs);
    }
    
    public void arcadeDrive(GenericHID stick) {
    	d_driveSystem.arcadeDrive(stick);
    }
    
    public void arcadeDrive(GenericHID stick, boolean squaredInputs) {
    	d_driveSystem.arcadeDrive(stick, squaredInputs);
    }
    
    public double getLeftSpeed() {
    	return d_leftMaster.getSpeed();
    }
    
    public double getRightSpeed() {
    	return d_rightMaster.getSpeed();
    }
}

