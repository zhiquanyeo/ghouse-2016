package org.usfirst.frc.team354.robot;

import org.usfirst.frc.team354.robot.commands.ActivateGateMode;
import org.usfirst.frc.team354.robot.commands.OperatorIntakeControl;
import org.usfirst.frc.team354.robot.commands.OperatorTankDrive;
import org.usfirst.frc.team354.robot.commands.RaiseMainArmToAngle;
import org.usfirst.frc.team354.robot.subsystems.MainArm;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	
	private final Joystick leftStick;
	private final Joystick rightStick;
	private final Joystick gamepad;
	
	public OI() {
		leftStick = new Joystick(RobotMap.leftJoystick);
		rightStick = new Joystick(RobotMap.rightJoystick);
		gamepad = new Joystick(RobotMap.gamepad);
		
		// Reverse Drive
		getButton(RobotMap.leftJoystick, 1).whileHeld(new OperatorTankDrive(1.0, true));
		
		//getButton(RobotMap.gamepad, 4).whileHeld(new RaiseMainArm());
		//getButton(RobotMap.gamepad, 2).whileHeld(new LowerMainArm());
		
		// Intake System
		getButton(RobotMap.gamepad, RobotMap.GAMEPAD_BUTTON_A).whileHeld(new OperatorIntakeControl(false)); // Forward (toward the robot)
		getButton(RobotMap.gamepad, RobotMap.GAMEPAD_BUTTON_B).whileHeld(new OperatorIntakeControl(true)); // Backward (away from the robot)
		getButton(RobotMap.gamepad, RobotMap.GAMEPAD_BUTTON_Y).whileHeld(new ActivateGateMode()); // Activate Gate Mode
		
		getButton(RobotMap.gamepad, RobotMap.GAMEPAD_BUTTON_BACK).whenPressed(new RaiseMainArmToAngle(MainArm.ARM_STARTING_POSITION));
		getButton(RobotMap.gamepad, RobotMap.GAMEPAD_BUTTON_START).whenPressed(new RaiseMainArmToAngle(MainArm.ARM_BALL_INTAKE));
	}
	
	public double getLeftStickY() {
		return leftStick.getRawAxis(1);
	}
	
	public double getRightStickY() {
		return rightStick.getRawAxis(1);
	}
	
	public double getGamepadLeftStickY() {
		return gamepad.getRawAxis(1);
	}
	
	public double getGamepadRightStickY() {
		return gamepad.getRawAxis(3);
	}
	
	
	public JoystickButton getButton(int stick, int button) {
		switch (stick) {
			case RobotMap.leftJoystick:
				return new JoystickButton(leftStick, button);
			case RobotMap.rightJoystick:
				return new JoystickButton(rightStick, button);
			case RobotMap.gamepad:
				return new JoystickButton(gamepad, button);
			default:
				return null;
		}
	}
	
	public boolean getButtonState(int stick, int button) {
		return getButton(stick, button).get();
	}
}

