package com.frc2879.newcomen.commands;

import com.frc2879.newcomen.Robot;
import com.frc2879.newcomen.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateWithGyro extends Command {
    
    double distance, angleInDegrees, speed, initialAngle;
    boolean turnLeft;
    
    Drivetrain dt = Robot.drivetrain;

    public RotateWithGyro(double angleInDegrees, double speed, boolean turnLeft, double timeout) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        super("RotateWithGyro", timeout);
        //requires(RobotModule.drivetrain);
        
        this.angleInDegrees = angleInDegrees;
        this.speed = speed;
        this.turnLeft = turnLeft;
    }
    
    public RotateWithGyro(double angleInDegrees, double speed, boolean turnLeft) {
    	this(angleInDegrees, speed, turnLeft, 4);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        initialAngle = Robot.imu.getGyroYaw();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (turnLeft) {
            dt.getConstantMecanumDrive().setRotation(speed);
        }
        else {
        	dt.getConstantMecanumDrive().setRotation(-speed);
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (turnLeft) {
            return (Robot.imu.getGyroYaw() - initialAngle < -angleInDegrees);
        }
        else {
            return (Robot.imu.getGyroYaw() - initialAngle > angleInDegrees);
        }
    }

    // Called once after isFinished returns true
    protected void end() {
        dt.getConstantMecanumDrive().setRotation(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
