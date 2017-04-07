package com.frc2879.newcomen.commands;

import com.frc2879.newcomen.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class MoveLift extends Command {
	
	private double speed = 0;
	
    public MoveLift(double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        super("MoveLiftStick");
        requires(Robot.lift);
        
        this.speed = speed;
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.lift.set(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.lift.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}