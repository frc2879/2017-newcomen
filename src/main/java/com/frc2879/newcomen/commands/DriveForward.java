package com.frc2879.newcomen.commands;

import com.frc2879.newcomen.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.TimedCommand;

public class DriveForward extends TimedCommand {
	
	private double speed = 0;
	
    public DriveForward(double speed, double timeout) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        super("DriveForward " + speed + " " + timeout, timeout);
        
        this.speed = speed;
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.drivetrain.getConstantMecanumDrive().setY(speed);
    	Robot.drivetrain.customMecanumDrive_Cartesian(0, -speed, 0);
    }

    // Called once after isFinished returns true
    protected void end() {
    	//Robot.drivetrain.getConstantMecanumDrive().setY(0);
    	Robot.drivetrain.stop();
    	System.out.println("DriveForward end");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println("DriveForward interrupted");
        end();
    }
}