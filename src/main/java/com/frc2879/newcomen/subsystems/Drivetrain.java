package com.frc2879.newcomen.subsystems;

import com.ctre.CANTalon;
import com.frc2879.newcomen.commands.DriveMecanum;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 *
 */
public class Drivetrain extends Subsystem {
	
	public static int TALONS_LF = 0;
	public static int TALONS_LB = 1;
	public static int TALONS_RF = 2;
	public static int TALONS_RB = 3;
	
	public CANTalon[] talons;
	RobotDrive robotDrive;
	 
    // Put methods for controlling this subsystem
    // here. Call these from Commands.


    public Drivetrain() {
        super("Drivetrain");
        //SmartDashboard.putData(this);
        System.out.println("drivetrain init");
        initTalonConfig();

    }

    public void initTalonConfig() {

	    talons = new CANTalon[] {
	    		new CANTalon(TALONS_LF), new CANTalon(TALONS_LB),
	            new CANTalon(TALONS_RF), new CANTalon(TALONS_RB)};
	    
	    talons[0].setInverted(true);
	    talons[1].setInverted(true);
	    talons[2].setInverted(false);
	    talons[3].setInverted(false);
	    
	    for (CANTalon t: talons) {
            t.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
            t.enableBrakeMode(false);
            t.set(0);
        }
	    robotDrive = new RobotDrive(talons[0], talons[1], talons[2], talons[3]);
	    robotDrive.setSafetyEnabled(false);
	}

    public void initDefaultCommand() {

        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    	setDefaultCommand(new DriveMecanum());
    	System.out.println("drivetrain default command set");
    }
    public void stop() {
        robotDrive.stopMotor();
    }

	public  RobotDrive getRobotDrive() {
		// TODO Auto-generated method stub
		return robotDrive  ;
	}
	
	/**
	 * Returns the Talon with the given ID
	 * @param i  The ID of the Talon
	 * @return  The <code>CANTalon</code> object for the given ID
	 */
	public CANTalon getTalon(int id) {
		return talons[id];
	}
}
    