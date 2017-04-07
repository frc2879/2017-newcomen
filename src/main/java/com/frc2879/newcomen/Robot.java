package com.frc2879.newcomen;

import java.util.HashMap;

import com.frc2879.newcomen.commands.DriveForward;
import com.frc2879.newcomen.commands.PlayMacro;
import com.frc2879.newcomen.commands.RecordMacro;
import com.frc2879.newcomen.controls.OI;
import com.frc2879.newcomen.subsystems.*;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {


    public static final String name = "2017-Newcomen";
    public static final String version = "0.3.3";

    public static SettingsRegister settings = new SettingsRegister();
    
    public static Drivetrain drivetrain;
    public static Lift lift;
    public static IMU imu;

    public static OI oi;
        
    Command autonomousCommand;
    
    DriveForward driveForward;

    SendableChooser<Command> chooser = new SendableChooser<>();


    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
    	// Initialize all subsystems
    	drivetrain = new Drivetrain();
    	lift = new Lift();
    	imu = new IMU();
    	
        oi = new OI();
        
        Preferences pref = Preferences.getInstance();

		if(!pref.containsKey("Drive Forward Speed"))
			pref.putDouble("Drive Forward Speed", 0.5);
		if(!pref.containsKey("Drive Forward Duration"))
			pref.putDouble("Drive Forward Duration", 5);
        
        
        System.out.println("Loaded " + name + " v" + version);
        chooser.addObject("Auto 1", new PlayMacro("AutoRecord1"));
        chooser.addObject("Auto 2", new PlayMacro("AutoRecord2"));
        chooser.addObject("Auto 3", new PlayMacro("AutoRecord3"));
        chooser.addObject("Auto 4", new PlayMacro("AutoRecord4"));
        chooser.addObject("Auto 5", new PlayMacro("AutoRecord5"));
        chooser.addObject("Auto 6", new PlayMacro("AutoRecord6"));
        chooser.addObject("Auto 7", new PlayMacro("AutoRecord7"));
        chooser.addObject("Auto 8", new PlayMacro("AutoRecord8"));
        chooser.addObject("Auto 9", new PlayMacro("AutoRecord9"));
        chooser.addObject("No Auto", new InstantCommand());
        chooser.addDefault("Drive Forward", driveForward);
        SmartDashboard.putData("Auto mode", chooser);
        
        
        SmartDashboard.putData(new RecordMacro("AutoRecord1"));
        SmartDashboard.putData(new RecordMacro("AutoRecord2"));
        
        SmartDashboard.putData(new RecordMacro("AutoRecord3"));
        SmartDashboard.putData(new RecordMacro("AutoRecord4"));
        SmartDashboard.putData(new RecordMacro("AutoRecord5"));
        SmartDashboard.putData(new RecordMacro("AutoRecord6"));
        SmartDashboard.putData(new RecordMacro("AutoRecord7"));
        SmartDashboard.putData(new RecordMacro("AutoRecord8"));
        SmartDashboard.putData(new RecordMacro("AutoRecord9"));
        
    }


    /**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
     * the robot is disabled.
     */
    @Override
    public void disabledInit() {

    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This autonomous (along with the chooser code above) shows how to select
     * between different autonomous modes using the dashboard. The sendable
     * chooser code works with the Java SmartDashboard. If you prefer the
     * LabVIEW Dashboard, remove all of the chooser code and uncomment the
     * getString code to get the auto name from the text box below the Gyro
     *
     * You can add additional auto modes by adding additional commands to the
     * chooser code above (like the commented example) or additional comparisons
     * to the switch structure below with additional strings & commands.
     */
    @Override
    public void autonomousInit() {
    	System.out.println("auto init");
    	if(chooser.getSelected() == driveForward) {
    		Preferences pref = Preferences.getInstance();
    		DriveForward driveForward2 = new DriveForward(pref.getDouble("Drive Forward Speed", 0), pref.getDouble("Drive Forward Duration", 0));
    		System.out.println("Running " + driveForward2.getName());
    		driveForward2.start();
    		return;
    	}
        autonomousCommand = chooser.getSelected();

        /*
         * String autoSelected = SmartDashboard.getString("Auto Selector",
         * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
         * = new MyAutoCommand(); break; case "Default Auto": default:
         * autonomousCommand = new ExampleCommand(); break; }
         */

        // schedule the autonomous command (example)
		if (autonomousCommand != null) {
			System.out.println("Running " + autonomousCommand.getName());
			autonomousCommand.start();
		}
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null)
            autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This function is called periodically during test mode
     */
    @Override
    public void testPeriodic() {
        LiveWindow.run();
    }
}
