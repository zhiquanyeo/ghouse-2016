package org.usfirst.frc.team354.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
	// Joysticks
	//public static final int driverJoystick = 0;
	public static final int leftJoystick = 0;
	public static final int rightJoystick = 1;
	public static final int gamepad = 2;
	
	// Buttons
	public static final int GAMEPAD_BUTTON_A = 2;
	public static final int GAMEPAD_BUTTON_B = 3;
	public static final int GAMEPAD_BUTTON_X = 1;
	public static final int GAMEPAD_BUTTON_Y = 4;
	public static final int GAMEPAD_BUTTON_LB = 5;
	public static final int GAMEPAD_BUTTON_RB = 6;
	public static final int GAMEPAD_BUTTON_LT = 7;
	public static final int GAMEPAD_BUTTON_RT = 8;
	public static final int GAMEPAD_BUTTON_BACK = 9;
	public static final int GAMEPAD_BUTTON_START = 10;
}
