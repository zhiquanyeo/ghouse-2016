package org.usfirst.frc.team354.robot;

public class Constants {
	public static final double DRIVE_EXPO_VALUE = 4.0;
	
	// === Analog Input Channels ===
	// Main Arm
	public static final int AIN_MAIN_ARM_POT = 0;
	
	// Upper Arm
	public static final int AIN_UPPER_ARM_POT = 1;
	
	// === Digital I/O Channels ===
	// Intake System
	public static final int DIN_INTAKE_BEAMBREAK = 0;
	
	// === CAN IDs ===
	// Drive System
	public static final int CAN_ID_DRIVE_LEFT_MASTER = 20;
	public static final int CAN_ID_DRIVE_LEFT_SLAVE = 21;
	public static final int CAN_ID_DRIVE_RIGHT_MASTER = 22;
	public static final int CAN_ID_DRIVE_RIGHT_SLAVE = 23;
	
	// Upper Roller
	public static final int CAN_ID_UPPER_SHOOTER_MASTER = 24;
	public static final int CAN_ID_UPPER_SHOOTER_SLAVE = 25;
	
	// Lower Roller
	public static final int CAN_ID_LOWER_SHOOTER_MASTER = 26;
	public static final int CAN_ID_LOWER_SHOOTER_SLAVE = 27;
	
	// Main Arm
	public static final int CAN_ID_MAIN_ARM = 28;
	
	// Upper Arm
	public static final int CAN_ID_UPPER_ARM = 29;
	
	// Intake
	public static final int CAN_ID_INTAKE = 30;
	
}
