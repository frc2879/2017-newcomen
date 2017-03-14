package com.frc2879.newcomen.commands;

import com.frc2879.newcomen.Robot;

import edu.wpi.first.wpilibj.command.Command;


/**
 */
public class DriveMecanumStick extends Command{
    
	private double sensitivity;
	
	public DriveMecanumStick(){
		this(1);
    }
	
	public DriveMecanumStick(double sensitivity){
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        super("DriveMecanum");
        requires(Robot.drivetrain);
        this.sensitivity = sensitivity;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    	double x= Robot.oi.getStickX() * sensitivity;
		double y= Robot.oi.getStickY() * sensitivity;
		double twist= Robot.oi.getStickTwist() * 0.5;
		
		
		//Robot.drivetrain.getRobotDrive().mecanumDrive_Cartesian(x,y,twist, 
    	//		Robot.settings.getBoolean("fieldoriented", false) ? Robot.imu.getGyroYaw() : 0);
		
		Robot.drivetrain.customMecanumDrive_Cartesian(x, y, twist);
		
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
