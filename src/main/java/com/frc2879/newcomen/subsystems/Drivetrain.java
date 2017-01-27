package com.frc2879.newcomen.subsystems;

import com.ctre.CANTalon;
import com.frc2879.newcomen.commands.DriveMecanum;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *
 */
public class Drivetrain extends Subsystem {
	public static int TALONS_LF, TALONS_LB, TALONS_RF, TALONS_RB;
	public CANTalon[] talons;
	RobotDrive robotDrive;
	 
    // Put methods for controlling this subsystem
    // here. Call these from Commands.


    public Drivetrain() {
        super("Drivetrain");
        SmartDashboard.putData(this);
        initTalonConfig();

    }

    public void initTalonConfig() {
	    TALONS_LF = 1;
	    TALONS_LB = 2;
	    TALONS_RF = 3;
	    TALONS_RB = 4;

	    talons = new CANTalon[] {
	    		new CANTalon(TALONS_LF), new CANTalon(TALONS_LB),
	            new CANTalon(TALONS_RF), new CANTalon(TALONS_RB)};
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
    }
    public void stop() {
        robotDrive.stopMotor();
    }

	public  RobotDrive getRobotDrive() {
		// TODO Auto-generated method stub
		return robotDrive  ;
	}
}
    