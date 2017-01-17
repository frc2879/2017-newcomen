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
    	double x,y,speed;
    	x=RobotModule.ui.getXboxController().leftStick.getX();
    	y=RobotModule.ui.getXboxController().leftStick.getY();
    	speed= Math.sqrt((x*x)+(y*y));
    	if (speed <= .10) {
    		x=0;
    		y=0;
    	}
    	else if (speed<=.75){
    		x/=speed;
    		x *= (45/65);
    		x += (605/650);
    		x -= .95;
    		
    		y/=speed;
    		y *= (45/65);
    		y += (605/650);
    		y -= .95;
    	}
    	
    	else if (speed <= .95){
    		x/=speed;
    		x *= (2.5);
    		x -= 1.35;
    		
    		y/=speed;
    		y *= (2.5);
    		y -= 1.35;
    	}
    	else if (speed<=1){
    		x /= speed;
    		y /= speed;
    	}
    	else {
    		x=0;
    		y=0;
    	}
    	 RobotModule.drivetrain.getRobotDrive().mecanumDrive_Cartesian(x,
    			 														RobotModule.ui.getXboxController().leftStick.getY(),
    			 														RobotModule.ui.getXboxController().rightStick.getX(), 0);
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
