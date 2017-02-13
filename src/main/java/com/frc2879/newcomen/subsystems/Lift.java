package com.frc2879.newcomen.subsystems;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Lift extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	private static int TALONS_LIFT = 6;
	
	private CANTalon liftTalon;

    public CANTalon getLiftTalon() {
		return liftTalon;
	}

	public Lift() {
        super("Lift");
        //SmartDashboard.putData(this);
        
        liftTalon = new CANTalon(TALONS_LIFT);
        
        liftTalon.enableBrakeMode(true);
        liftTalon.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
        liftTalon.set(0);
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