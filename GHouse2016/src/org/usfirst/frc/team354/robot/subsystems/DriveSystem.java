package org.usfirst.frc.team354.robot.subsystems;

import org.usfirst.frc.team354.robot.CANTalonPair;
import org.usfirst.frc.team354.robot.Constants;
import org.usfirst.frc.team354.robot.commands.OperatorTankDrive;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

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
	
	private CANTalonPair d_left;
	private CANTalonPair d_right;
	
	private DoubleSolenoid d_transmission;
	
	private RobotDrive d_driveSystem;
	
	public DriveSystem() {
		d_left = new CANTalonPair("LeftTalonPair", Constants.CAN_ID_DRIVE_LEFT_MASTER, Constants.CAN_ID_DRIVE_LEFT_SLAVE, 5310.0, 6200.0, false, false);
		d_right = new CANTalonPair("RightTalonPair", Constants.CAN_ID_DRIVE_RIGHT_MASTER, Constants.CAN_ID_DRIVE_RIGHT_SLAVE, 5310.0, 6200.0, false, false);
		d_transmission = new DoubleSolenoid(Constants.PNEU_TRANSMISSION_LO, Constants.PNEU_TRANSMISSION_HI); // TODO Switch around if we need to
		
		d_driveSystem = new RobotDrive(d_left, d_right);
		LiveWindow.addActuator("DriveSystem", "Transmission", d_transmission);
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	// Set the default command that will run for this
    	// For now, we will set it up as tank drive
    	//setDefaultCommand(new OperatorTankDrive(1.0));
        //setDefaultCommand(new OperatorArcadeDrive(1.0));
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
    	return d_left.getSpeed();
    }
    
    public double getRightSpeed() {
    	return d_right.getSpeed();
    }
    
    public void setHighGear() {
    	d_transmission.set(Value.kForward);
    	Timer.delay(0.25);
    	d_transmission.set(Value.kOff);
    }
    
    public void setLowGear() {
    	d_transmission.set(Value.kReverse);
    	Timer.delay(0.25);
    	d_transmission.set(Value.kOff);
    }
}

