package com.frc2879.newcomen.commands;

import com.frc2879.newcomen.Robot;
import com.frc2879.newcomen.controls.JoystickPOVTrigger;

import edu.wpi.first.wpilibj.command.Command;

public class DriveMecanumPOV extends Command {

	private double magnitude;
	
	public DriveMecanumPOV(double magnitude) {
		// TODO Auto-generated constructor stub
		super("DriveMecanumPolar");
        requires(Robot.drivetrain);
		this.magnitude = magnitude;
	}
	

	 // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    	Robot.drivetrain.getRobotDrive().mecanumDrive_Polar(magnitude, Robot.oi.getStick().getPOV(), 0);
    	
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

}
