package com.frc2879.newcomen.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.openrio.toast.lib.registry.Registrar;

/**
 *
 */
public class Drivetrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    CANTalon leftTalonR, leftTalonF, rightTalonR, rightTalonF;
    
    public RobotDrive robotDrive;
    
    public Drivetrain() {
        super("Drivetrain");
        SmartDashboard.putData(this);
        robotDrive = new RobotDrive(leftTalonR,leftTalonF,rightTalonR,rightTalonF);
        initTalonConfig();
    }

    public void initTalonConfig() {
    	
    	// only temporary config
        leftTalonR = Registrar.canTalon(5);
        leftTalonF = Registrar.canTalon(6);
        rightTalonR = Registrar.canTalon(7);
        rightTalonF = Registrar.canTalon(8);
        
        leftTalonR.changeControlMode(TalonControlMode.PercentVbus);
        rightTalonR.changeControlMode(TalonControlMode.PercentVbus);
        leftTalonF.changeControlMode(TalonControlMode.PercentVbus);
        rightTalonF.changeControlMode(TalonControlMode.PercentVbus);
        
        leftTalonR.setInverted(false);
        rightTalonR.setInverted(false);
        leftTalonF.setInverted(false);
        rightTalonF.setInverted(false);
        
        leftTalonR.reverseSensor(false);
        rightTalonR.reverseSensor(false);
        leftTalonF.reverseSensor(false);
        rightTalonF.reverseSensor(false);
                
        leftTalonR.setEncPosition(0);
        rightTalonR.setEncPosition(0);
        leftTalonF.setEncPosition(0);
        rightTalonF.setEncPosition(0);
        
        leftTalonR.enableBrakeMode(false);
        rightTalonR.enableBrakeMode(false);
        leftTalonF.enableBrakeMode(false);
        rightTalonF.enableBrakeMode(false);
        
        leftTalonR.set(0);
        rightTalonR.set(0);
        leftTalonF.set(0);
        rightTalonF.set(0);
        
        robotDrive.setSafetyEnabled(false);
    }

    public void initDefaultCommand() {

        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public RobotDrive getRobotDrive() {
        return this.robotDrive;
    }

}

