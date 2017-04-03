package com.frc2879.newcomen.commands;

import com.frc2879.newcomen.Robot;
import com.frc2879.newcomen.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForwardDistance extends Command {
    
    private double initialDistLeft, initialDistRight, distance, speed;
    
    private double error = 0;
    
    Drivetrain dt = Robot.drivetrain;

    public DriveForwardDistance(double speed, double distance, double timeout) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        super("DriveForwardDistance", timeout);
        //requires(Robot.drivetrain);
        this.speed = speed;
        this.distance = distance - error;
    }
    
    public DriveForwardDistance(double speed, double distance) {
        this(speed, distance, 15);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
        initialDistLeft = dt.getTalon(MotorType.kFrontLeft).getPosition();
        initialDistRight = dt.getTalon(MotorType.kFrontRight).getPosition();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        dt.getConstantMecanumDrive().setY(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return ((Math.abs(dt.getTalon(MotorType.kFrontLeft).getPosition() - initialDistLeft) > (distance)) 
                || (Math.abs(dt.getTalon(MotorType.kFrontRight).getPosition() - initialDistRight) > (distance)));
    }

    // Called once after isFinished returns true
    protected void end() {
        dt.getConstantMecanumDrive().setY(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}