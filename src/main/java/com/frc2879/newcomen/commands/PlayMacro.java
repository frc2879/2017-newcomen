package com.frc2879.newcomen.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.ctre.CANTalon;
import com.frc2879.newcomen.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class PlayMacro extends Command {
	
	private String macroName;
		
	Scanner scanner;
	
	Timer timer = new Timer();
	
	boolean onTime = true;
	
	double nextDouble;

	public PlayMacro(String macroName) {
		// TODO Auto-generated constructor stub
		super("PlayMacro " + macroName);
		this.macroName = macroName;
		requires(Robot.drivetrain);
	}

    // Called just before this Command runs the first time
    protected void initialize() {
    	try {
			scanner = new Scanner(new File("/home/lvuser/recordedMacro_" + macroName + ".csv"));
			scanner.useDelimiter(",|\\n");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	timer.start();
    	System.out.println("playing macro " + macroName);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	double t_delta;
    	
    	if(onTime)
    		nextDouble = scanner.nextDouble();
    	
    	t_delta = nextDouble - timer.get();
    	
    	if (t_delta <= 0) {
    		for(CANTalon t : Robot.drivetrain.getTalons()) {
				t.set(scanner.nextDouble());
			}
    	
    		onTime = true;
    	} else {
    		onTime = false;
    	}
    	
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return scanner == null || !scanner.hasNextDouble();
    }

    // Called once after isFinished returns true
    protected void end() {
		Robot.drivetrain.stop();
		if (scanner != null) {
			scanner.close();
			scanner = null;
		}
    	System.out.println("done playing macro " + macroName);
	}

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }

}
