package com.frc2879.newcomen.commands;

import com.frc2879.newcomen.RobotModule;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;


/**
 */
public class DriveMecanum extends Command{
    
	public DriveMecanum(){
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        super("DriveMecanum");
        requires(RobotModule.drivetrain);
       
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	 RobotModule.drivetrain.getRobotDrive().mecanumDrive_Cartesian(RobotModule.ui.getXboxController().getX(Hand.kLeft),
    			 														RobotModule.ui.getXboxController().getY(Hand.kLeft),
    			 														RobotModule.ui.getXboxController().getTwist(), 0);
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }


}
