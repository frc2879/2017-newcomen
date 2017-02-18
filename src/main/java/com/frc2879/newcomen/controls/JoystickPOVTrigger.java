package com.frc2879.newcomen.controls;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Trigger;

public class JoystickPOVTrigger extends Trigger {

	private final Joystick joystick;
	
	public JoystickPOVTrigger(Joystick joystick) {
		// TODO Auto-generated constructor stub
		this.joystick = joystick;
	}
	

	@Override
	public boolean get() {
		// TODO Auto-generated method stub
		return joystick.getPOV() != -1;
	}
	
}
