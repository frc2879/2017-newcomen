package com.frc2879.newcomen.commands;

import com.frc2879.newcomen.RobotModule;
import com.frc2879.xboxcontroller.XboxController;

import edu.wpi.first.wpilibj.command.Command;


/**
 */
public class DriveMecanum extends Command{
	
	public final XboxController driveJoystick = new XboxController(0); // move to oi later
    
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
    	RobotModule.drivetrain.getRobotDrive().mecanumDrive_Cartesian(driveJoystick.leftStick.getX(), driveJoystick.leftStick.getY(), driveJoystick.rightStick.getX(),0);
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
