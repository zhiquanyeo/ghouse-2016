package org.usfirst.frc.team354.robot;

public class Constants {
	public static final double DRIVE_EXPO_VALUE = 1.0;
	
	// === Analog Input Channels ===
	// Main Arm
	public static final int AIN_MAIN_ARM_POT = 3;
	
	// Upper Arm
	public static final int AIN_UPPER_ARM_POT = 1;
	
	public static final int AIN_ULTRASONIC = 2;
	
	// === Digital I/O Channels ===
	// Intake System
	public static final int DIN_INTAKE_BEAMBREAK = 2;
	
	// Winch
	public static final int DIN_WINCH_HOOK_BAR_CONTACT_SWITCH = 3;
	public static final int DIN_WINCH_LOWER_LIMIT_SWITCH = 4; 
	
	// Main Arm
	public static final int DIN_MAIN_ARM_LOWER_LIMIT_SWITCH = 5;
	
	// === Pneumatic Channels ===
	// Transmission
	public static final int PNEU_TRANSMISSION_LO = 0;
	public static final int PNEU_TRANSMISSION_HI = 1;
	
	// === CAN IDs ===
	// Drive System
	public static final int CAN_ID_DRIVE_LEFT_MASTER = 30;
	public static final int CAN_ID_DRIVE_LEFT_SLAVE = 32;
	public static final int CAN_ID_DRIVE_RIGHT_MASTER = 20;
	public static final int CAN_ID_DRIVE_RIGHT_SLAVE = 26;
	
	// Upper Roller
	public static final int CAN_ID_UPPER_SHOOTER_MASTER = 24;
	public static final int CAN_ID_UPPER_SHOOTER_SLAVE = 25;
	
	// Lower Roller
	public static final int CAN_ID_LOWER_SHOOTER_MASTER = 21;
	public static final int CAN_ID_LOWER_SHOOTER_SLAVE = 22;
	
	// Main Arm
	public static final int CAN_ID_MAIN_ARM = 27;
	
	// Upper Arm
	public static final int CAN_ID_UPPER_ARM = 29;
	
	// Intake
	public static final int CAN_ID_LOWER_INTAKE = 28;
	public static final int CAN_ID_UPPER_INTAKE = 31;
	
	// Winch
	public static final int CAN_ID_WINCH = 23;
	
	// Directions
	public static final int DIR_TURN_LEFT = 1;
	public static final int DIR_TURN_RIGHT = 2;
	
	// Shooter Values
	public static final double[] UPPER_SHOOTER_SPEED = {0.0,   4456,  4504,   4407,   4553};
	public static final double[] LOWER_SHOOTER_SPEED = {0.0,   5812,  4916,   4819,   4625};
	public static final double[] SHOOTER_DISTANCES  =  {84.0,  96.0,  108.0,  120.0,  132.0}; // in inches
												 		//      7ft    8ft     9ft     10ft
}
