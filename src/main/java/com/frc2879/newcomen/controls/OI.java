package com.frc2879.newcomen.controls;

import com.frc2879.newcomen.Robot;
import com.frc2879.newcomen.commands.*;

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
	
	private Joystick stick;
	private Joystick kidstick;
	
	private JoystickButton[] stickButtons;
	
	private JoystickPOVTrigger pov;
		
	public OI() {
		stick = new Joystick(0);
		kidstick = new Joystick(1);
		
		stickButtons = new JoystickButton[13];
		
		for(int i = 1; i < 13; i++) {
			stickButtons[i] = new JoystickButton(stick, i);
		}
		
		pov = new JoystickPOVTrigger(stick);
				
		//stickButtons[0].whileHeld(command);
		//stickButtons[12].whileHeld(new MoveLiftStick(false));
		//stickButtons[11].whileHeld(new MoveLiftStick(true));
		
		stickButtons[12].toggleWhenPressed(new MoveLiftStick(false));
		stickButtons[11].toggleWhenPressed(new MoveLiftStick(true));
		
		stickButtons[10].whenPressed(new ToggleBooleanSetting("fieldoriented"));
		
		stickButtons[2].whenPressed(new DriveMecanumStick(.19));
		stickButtons[1].whileHeld(new DriveMecanumStick(0.5));

		pov.whileActive(new DriveMecanumPOV(0.25));
		
	}
	
	public JoystickPOVTrigger getPOVTrigger() {
		return pov;
	}
	
	public Joystick getStick() {
		return stick;
	}
	public Joystick getKidStick() {
		return kidstick;
	}
	
	public JoystickButton getStickButton(int i) {
		return stickButtons[i];
	}
	
	public double correctThrottle() {
		return (-(stick.getThrottle()) + 1) / 2;
	}
}

