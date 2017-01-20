package com.frc2879.newcomen.commands;

import com.frc2879.newcomen.RobotModule;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;


/**
 */
public class MoveSmallDistance extends Command{
	
	private String direction;
	private boolean finished;
    
	public MoveSmallDistance(String direction){
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        super("MoveTenthInch");
        requires(RobotModule.drivetrain);
        
        this.direction = direction;
        finished = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (direction.toLowerCase().equals("right")){
    		RobotModule.drivetrain.getRobotDrive().mecanumDrive_Cartesian(0.5, 0, 0, 0);
    	} 
    	if (direction.toLowerCase().equals("left")){
    		RobotModule.drivetrain.getRobotDrive().mecanumDrive_Cartesian(-0.5, 0, 0, 0);
    	}
    	if (direction.toLowerCase().equals("forward")){
    		RobotModule.drivetrain.getRobotDrive().mecanumDrive_Cartesian(0, 0.5, 0, 0);
    	} 
    	if (direction.toLowerCase().equals("backward")){
    		RobotModule.drivetrain.getRobotDrive().mecanumDrive_Cartesian(0, -0.5, 0, 0);
    	} 
    	Timer.delay(.1);
    	finished = true;
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotModule.drivetrain.getRobotDrive().mecanumDrive_Cartesian(0, 0, 0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }


}
