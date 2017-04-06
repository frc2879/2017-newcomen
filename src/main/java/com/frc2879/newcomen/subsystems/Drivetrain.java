package com.frc2879.newcomen.subsystems;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.frc2879.newcomen.commands.DriveMecanumStick;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 *
 */
public class Drivetrain extends Subsystem {
	
	private static int TALONS_FRONT_LEFT = 2;
	private static int TALONS_FRONT_RIGHT = 3;
	private static int TALONS_REAR_LEFT = 4;
	private static int TALONS_REAR_RIGHT = 5;
	
	private static int ENC_CODES_PER_REV = 40;
	
	private CANTalon[] talons;
	private RobotDrive robotDrive;
	
	private ConstantMecanumDrive constantMecanumDrive;
	 
    // Put methods for controlling this subsystem
    // here. Call these from Commands.


    public Drivetrain() {
        super("Drivetrain");
        //SmartDashboard.putData(this);
        System.out.println("drivetrain init");
        constantMecanumDrive = new ConstantMecanumDrive();
        initTalonConfig();

    }
    
    public ConstantMecanumDrive getConstantMecanumDrive() {
    	return constantMecanumDrive;
    }
    

    public void initTalonConfig() {

	    talons = new CANTalon[] {
	    		new CANTalon(TALONS_FRONT_LEFT), new CANTalon(TALONS_FRONT_RIGHT),
	            new CANTalon(TALONS_REAR_LEFT), new CANTalon(TALONS_REAR_RIGHT)};
	    
	    talons[MotorType.kFrontLeft.value].setInverted(true);
	    talons[MotorType.kFrontRight.value].setInverted(false);
	    talons[MotorType.kRearLeft.value].setInverted(true);
	    talons[MotorType.kRearRight.value].setInverted(false);
	    
	    for (CANTalon t: talons) {
            t.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
            t.enableBrakeMode(false);
            t.setFeedbackDevice(FeedbackDevice.QuadEncoder);
            //t.configEncoderCodesPerRev(ENC_CODES_PER_REV);
            
            // front right at 100%
            //Pos (rot): 899.755 	Velocity (RPM):2366.25
            // Pos:143961 	Velocity:631
            
            
            t.setEncPosition(0);
            t.set(0);
        }
	    robotDrive = new RobotDrive(talons[MotorType.kFrontLeft.value], talons[MotorType.kRearLeft.value], 
	    							talons[MotorType.kFrontRight.value], talons[MotorType.kRearRight.value]);
	    robotDrive.setSafetyEnabled(false);
	}

    public void initDefaultCommand() {

        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    	setDefaultCommand(new DriveMecanumStick());
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
	
	public CANTalon[] getTalons() {
		return talons;
	}
	
	public CANTalon getTalon(MotorType mtype) {
		return talons[mtype.value];
	}
	
	public double[] mecanumSpeeds_Cartesian(double x, double y, double rotation) {
		double[] wheelSpeeds = new double[4];
		double k1, k2, k3, k4, 
		pr, // primary rotation
		// wheel positions relative to center of mass:
		x1 = -11.25, 
		y1 = 6.75,
		x2 = 11.25, 
		y2 = y1,
		x3 = x1, 
		y3 = -7.25, 
		x4 = x2, 
		y4 = y3,
		rotationRatio, maxRotVel, rr, multiplier = 1;
		// split motion into other axes,
		k1 = (x + y);
		k2 = (-x + y);
		k3 = (-x + y);
		k4 = (x + y);
		rotationRatio = -y1 / y4;
		maxRotVel = (x1 - y1 - x2 - y2 + x3 + y3 + x4 - y4);
		// scaled primary rotation (pr) (pos. is counter clock)
		pr = (k1 * (x1 - y1) + (k2 * (x2 + y2)) + (k3 * (x3 + y3)) + (k4 * (x4 - y4))) / maxRotVel;
		// scaled additional required rotation
		rr = pr + rotation;
		
		if (Math.abs((x + y + (rotationRatio * rr))) > 1) {
			multiplier /= (x + y + (rotationRatio * rr));
		}
		if (Math.abs((-x + y - rr) * multiplier) > 1) {
			multiplier /= (-x + y - rr);
		}
		if (Math.abs((-x + y + rr) * multiplier) > 1) {
			multiplier /= (-x + y + rr);
		}
		if (Math.abs((x + y - (rotationRatio * rr)) * multiplier) > 1) {
			multiplier /= (x + y - (rotationRatio * rr));
		}
		
		multiplier = Math.abs(multiplier);

		wheelSpeeds[MotorType.kFrontLeft.value] = (k1 + rr) * multiplier;
		wheelSpeeds[MotorType.kFrontRight.value] = (k2 - rr) * multiplier;
		wheelSpeeds[MotorType.kRearLeft.value] = (k3 + (rotationRatio * rr)) * multiplier;
		wheelSpeeds[MotorType.kRearRight.value] = (k4 - (rotationRatio * rr)) * multiplier;

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
	
	public class ConstantMecanumDrive {
		private double x;
		private double y;
		private double rotation;
		/**
		 * @return the x
		 */
		public double getX() {
			return x;
		}
		/**
		 * @param x the x to set
		 */
		public void setX(double x) {
			this.x = x;
			setDrive();
		}
		/**
		 * @return the y
		 */
		public double getY() {
			return y;
		}
		/**
		 * @param y the y to set
		 */
		public void setY(double y) {
			this.y = y;
			setDrive();
		}
		/**
		 * @return the rotation
		 */
		public double getRotation() {
			return rotation;
		}
		/**
		 * @param rotation the rotation to set
		 */
		public void setRotation(double rotation) {
			this.rotation = rotation;
			setDrive();
		}
		
		public void setAll(double x, double y, double rotation) {
			this.x = x;
			this.y = y;
			this.rotation = rotation;
			
			setDrive();
		}
		
		public void setDrive() {
			setTalons(mecanumSpeeds_Cartesian(x, y, rotation));
		}
		
		public void stop() {
			setAll(0, 0, 0);
		}
		
	}
	
}
    