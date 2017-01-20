package com.frc2879.newcomen.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.openrio.toast.lib.registry.Registrar;

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
	    TALONS_LF = 6;
	    TALONS_LB = 8;
	    TALONS_RF = 9;
	    TALONS_RB = 7;

	    talons = new CANTalon[] {
	    		Registrar.canTalon(TALONS_LF), Registrar.canTalon(TALONS_LB),
	            Registrar.canTalon(TALONS_RF), Registrar.canTalon(TALONS_RB)};
	    for (CANTalon t: talons) {
            t.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
            t.enableBrakeMode(false);
            t.set(0);
        }
	    robotDrive = new RobotDrive(talons[0], talons[1], talons[2], talons[3]);
	    robotDrive.setSafetyEnabled(false);
	    talons[1].setInverted(true);
	    talons[3].setInverted(true);
    }

    public void initDefaultCommand() {

        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void stop() {
        robotDrive.stopMotor();
    }

	public  RobotDrive getRobotDrive() {
		// TODO Auto-generated method stub
		return robotDrive  ;
	}
}
    