package com.frc2879.newcomen.subsystems;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.frc2879.newcomen.commands.DriveMecanum;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 *
 */
public class Drivetrain extends Subsystem {
	
	public static int TALONS_FRONT_LEFT = 2;
	public static int TALONS_FRONT_RIGHT = 3;
	public static int TALONS_REAR_LEFT = 4;
	public static int TALONS_REAR_RIGHT = 5;
	
	public static int ENC_CODES_PER_REV = 2048;
	
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
	    		new CANTalon(TALONS_FRONT_LEFT), new CANTalon(TALONS_FRONT_RIGHT),
	            new CANTalon(TALONS_REAR_LEFT), new CANTalon(TALONS_REAR_RIGHT)};
	    
	    talons[MotorType.kFrontLeft.value].setInverted(true);
	    talons[MotorType.kFrontRight.value].setInverted(true);
	    talons[MotorType.kRearLeft.value].setInverted(false);
	    talons[MotorType.kRearRight.value].setInverted(false);
	    
	    for (CANTalon t: talons) {
            t.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
            t.enableBrakeMode(false);
            t.setFeedbackDevice(FeedbackDevice.QuadEncoder);
            t.configEncoderCodesPerRev(ENC_CODES_PER_REV);
            t.setEncPosition(0);
            t.set(0);
        }
	    robotDrive = new RobotDrive(talons[MotorType.kFrontLeft.value], talons[MotorType.kFrontRight.value], 
	    							talons[MotorType.kRearLeft.value], talons[MotorType.kRearRight.value]);
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
	
	public CANTalon getTalon(MotorType mtype) {
		return talons[mtype.value];
	}
	
	public double[] mecanumSpeeds_Cartesian(double x, double y, double rotation) {
		double[] wheelSpeeds = new double[4];
		wheelSpeeds[MotorType.kFrontLeft.value] = x + y + rotation;
	    wheelSpeeds[MotorType.kFrontRight.value] = -x + y - rotation;
	    wheelSpeeds[MotorType.kRearLeft.value] = -x + y + rotation;
	    wheelSpeeds[MotorType.kRearRight.value] = x + y - rotation;
	    return wheelSpeeds;
	}
	
	public void setTalons(double[] values) {
		talons[MotorType.kFrontLeft.value].set(values[MotorType.kFrontLeft.value]);
	    talons[MotorType.kFrontRight.value].set(values[MotorType.kFrontRight.value]);
	    talons[MotorType.kRearLeft.value].set(values[MotorType.kRearLeft.value]);
	    talons[MotorType.kRearRight.value].set(values[MotorType.kRearRight.value]);
	}
	
	public void customMecanumDrive_Cartesian(double x, double y, double rotation) {
		setTalons(mecanumSpeeds_Cartesian(x, -y, rotation)); // -y to account for reversed y from joystick
	}
	
}
    