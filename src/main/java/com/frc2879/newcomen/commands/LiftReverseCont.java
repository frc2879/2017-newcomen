package com.frc2879.newcomen.commands;

import com.frc2879.newcomen.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class LiftReverseCont extends Command {


    public LiftReverseCont() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        super("LiftReverseCont");
        requires(Robot.lift);
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.lift.set(-Robot.oi.getXboxController().lt.getX());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true; //(Robot.oi.getXboxController().lt.getX() < 0.1);
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