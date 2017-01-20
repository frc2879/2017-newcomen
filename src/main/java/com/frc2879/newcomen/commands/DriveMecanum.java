package com.frc2879.newcomen.commands;

import com.frc2879.newcomen.RobotModule;

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

    		 x /= speed;
    		 x *= (speed * (45/65) - (45/650) + .05);
    		 
    		 y /= speed;
    		 y *= (speed * (45/65) - (45/650) + .05);
    		
    	}
    	
    	else if (speed <= .95){
    		
    		x /= speed;
    		x *= (speed * (2.5) - (2.5*.75) + .5);
   		 
    		y /= speed;
    		y *= (speed * (2.5) - (2.5*.75) + .5);
    	
    	}
    	else if (speed<=1){
    		x /= speed;
    		y /= speed;
    	}
    	else {
    		x=0;
    		y=0;
    	}
    	 RobotModule.drivetrain.getRobotDrive().mecanumDrive_Cartesian(x, y, RobotModule.ui.getXboxController().rightStick.getX(), 0);
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
