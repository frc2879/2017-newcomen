package com.frc2879.newcomen.commands;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.ctre.CANTalon;
import com.frc2879.newcomen.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class RecordMacro extends Command {
	
	private String macroName;
	
	FileWriter recordFile;
	
	Timer timer = new Timer();

	public RecordMacro(String macroName) {
		// TODO Auto-generated constructor stub
		super("RecordMacro");
		this.macroName = macroName;
	}

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	try {
			recordFile = new FileWriter("/home/lvuser/recordedMacro_" + macroName + ".csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	try {
			recordFile.append("" + timer.get());
			
			for(CANTalon t : Robot.drivetrain.getTalons()) {
				recordFile.append("," + t.get());
			}
			
			recordFile.append("\n");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
	protected void end() {
		timer.stop();
		try {
			if (recordFile != null) {
				recordFile.flush();
				recordFile.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }

}
