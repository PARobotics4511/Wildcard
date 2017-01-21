package org.usfirst.frc.team4511.robot.commands;

import org.usfirst.frc.team4511.robot.Robot;
import org.usfirst.frc.team4511.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoAlign extends Command {

    public AutoAlign() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double distance = Robot.vision.getDistanceFromTarget();
    	double gyroPosition = Robot.drivetrain.gyro.getAngle();
    	if (distance <= 20){
    		if(gyroPosition < 3 && gyroPosition > -3){
    			DriveTrain.drive(-.2, -.2);
    			Timer.delay(2);
    			DriveTrain.stop();
    			Timer.delay(1);
    			DriveTrain.drive(.4, .4);
    			Timer.delay(2);
    			DriveTrain.stop();
    		}
    	}else if (distance > 20){
    		if(gyroPosition < 5 && gyroPosition > -5){
    			new AutoDriveStraight();
    		}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	DriveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
