package com.frc2879.newcomen.commands;

import com.frc2879.newcomen.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class StopMotors extends InstantCommand {

	public StopMotors() {
		super("StopMotors");
	}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.stop();
    }

}
