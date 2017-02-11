package com.frc2879.newcomen.commands;

import com.frc2879.newcomen.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;


/**
 */
public class DriveMecanum extends Command{
    
	public DriveMecanum(){
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        super("DriveMecanum");
        requires(Robot.drivetrain);
       
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	 //Robot.drivetrain.getRobotDrive().mecanumDrive_Cartesian(Robot.oi.getXboxController().leftStick.getX(),
    		//	 														Robot.oi.getXboxController().leftStick.getY(),
    		//	 														Robot.oi.getXboxController().rightStick.getX(), 0);
    	double x= Robot.oi.getStick().getX();
		double y= Robot.oi.getStick().getY();
		double z= Robot.oi.getStick().getZ();
		if (z<=.1){
			z=0;
		}
		if(Math.sqrt(x*x+y*y)<=.1){
			x=0;
			y=0;
		}
		
    	Robot.drivetrain.getRobotDrive().mecanumDrive_Cartesian(x,y,z, 0);

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
