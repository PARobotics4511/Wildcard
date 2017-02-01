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
	Pair<Double, Boolean> distance; 
	Pair<Double, Boolean> xPosition;
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
    	/* Debugging
    	 * System.out.print("Distance: " + distance);
    	System.out.println();
    	System.out.print("Position: " + xPosition);
    	System.out.println();*/
    	if (distance.t <= 20 && distance.u == true){
    			DriveTrain.drive(.3, .3);
    			Timer.delay(2);
    			DriveTrain.drive(-.4, -.4);
    			Timer.delay(2);
    			DriveTrain.stop();
    	}else if (distance.t > 20 && distance.u == true){
    		boolean tooFar = true;
    		while(tooFar){
    			if(!xPosition.u){
	    			if(xPosition.t < .1 && xPosition.t > -.1){
	    				DriveTrain.drive(.4, .4);
	    				continue;
	    			}
	    			else if(xPosition.t > 0){
	    				while(xPosition.t > 0.12){
	    					DriveTrain.drive(.30,.5);
	    					continue;
	    				}
	    			}else if(xPosition.t < 0){
	    				while(xPosition.t < -.12){
	    					DriveTrain.drive(.5,.30);
	    					continue;
	    				}
	    			}
    			}else{
    				DriveTrain.drive(.25, .25);
    			}
    			distance = Robot.vision.getDistanceFromTarget(); 
    			if(distance.t <= 20 && distance.u == true){
    	    			DriveTrain.drive(.3, .3);
    	    			Timer.delay(2);
    	    			DriveTrain.drive(-.4, -.4);
    	    			Timer.delay(2);
    	    			DriveTrain.stop();
    	    			tooFar = false;
    			}else if (distance.u == false){
    				DriveTrain.drive(.25, .25);
    				continue;
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
