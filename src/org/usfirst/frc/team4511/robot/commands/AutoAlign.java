package org.usfirst.frc.team4511.robot.commands;

import org.usfirst.frc.team4511.robot.Robot;
import org.usfirst.frc.team4511.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4511.robot.subsystems.Vision;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoAlign extends Command {
	double distance; 
	double xPosition;
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
    	distance = Robot.vision.getDistanceFromTarget();
    	//double gyroPosition = Robot.drivetrain.gyro.getAngle();
    	xPosition = Robot.vision.getXPos();
    	/*System.out.print("Distance: " + distance);
    	System.out.println();
    	System.out.print("Position: " + xPosition);
    	System.out.println();*/
    	if (distance <= 20){
    		//if(gyroPosition < 3 && gyroPosition > -3){
    			DriveTrain.drive(.3, .3);
    			Timer.delay(2);
    			DriveTrain.drive(-.4, -.4);
    			Timer.delay(2);
    			DriveTrain.stop();
    		//}
    	}else if (distance > 20){
    		//if(gyroPosition < 5 && gyroPosition > -5){
    		boolean tooFar = true;
    		while(tooFar){
    			if(Robot.vision.getXPos() < .1 && Robot.vision.getXPos() > -.1){
    				DriveTrain.drive(.4, .4);
    			}
    			else if(Robot.vision.getXPos() > 0){
    				while(Robot.vision.getXPos() > 0.12){
    					DriveTrain.drive(.30,.45);
    				}
    			}else if(Robot.vision.getXPos() < 0){
    				while(Robot.vision.getXPos() < -.12){
    					DriveTrain.drive(.45,.30);
    				}
    			}
    			distance = Robot.vision.getDistanceFromTarget(); 
    			if(distance <= 20){
    				//if(gyroPosition < 3 && gyroPosition > -3){
    	    			DriveTrain.drive(.3, .3);
    	    			Timer.delay(2);
    	    			DriveTrain.drive(-.4, -.4);
    	    			Timer.delay(2);
    	    			DriveTrain.stop();
    	    			tooFar = false;
    				//}
    			}
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
