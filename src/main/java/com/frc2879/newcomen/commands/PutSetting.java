package com.frc2879.newcomen.commands;

import com.frc2879.newcomen.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class PutSetting extends InstantCommand {

	private String name;
	private Object value;
	
	public PutSetting(String name, Object value) {
		this.name = name;
		this.value = value;
	}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.settings.put(name, value);
    }

}
