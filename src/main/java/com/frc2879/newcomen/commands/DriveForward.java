package com.frc2879.newcomen.commands;

import com.frc2879.newcomen.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class DriveForward extends Command {
	
	private double speed = 0;
	
    public DriveForward(double speed, double timeout) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        super("DriveForward", timeout);
        
        this.speed = speed;
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.getConstantMecanumDrive().setY(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.getConstantMecanumDrive().setY(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}