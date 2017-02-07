package com.frc2879.newcomen.subsystems;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Lift extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public static int TALONS_LIFT = 6;
	
	CANTalon liftTalon;

    public Lift() {
        super("Lift");
        //SmartDashboard.putData(this);
        
        liftTalon = new CANTalon(TALONS_LIFT);
    }

    public void initDefaultCommand() {

        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void set(double outputValue) {
        liftTalon.set(outputValue);
    }
    
    public void stop() {
    	liftTalon.disableControl();
    }
    

}