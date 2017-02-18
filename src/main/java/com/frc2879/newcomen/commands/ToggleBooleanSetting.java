package com.frc2879.newcomen.commands;

import com.frc2879.newcomen.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class ToggleBooleanSetting extends InstantCommand {

	private String name;
	
	public ToggleBooleanSetting(String name) {
		this.name = name;
	}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.settings.put(name, !Robot.settings.getBoolean(name, false));
    }

}
