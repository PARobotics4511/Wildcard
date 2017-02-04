package org.usfirst.frc.team4511.robot.commands;

import org.usfirst.frc.team4511.robot.OI;
import org.usfirst.frc.team4511.robot.Robot;
import org.usfirst.frc.team4511.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoAlign extends Command {
	Pair<Double, Boolean> distance; 
	Pair<Double, Boolean> xPosition;
	JoystickButton button2 = new JoystickButton(OI.stick2, 1);
    public AutoAlign() {
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	distance = Robot.vision.getDistanceFromTarget();
    	xPosition = Robot.vision.getXPos();
    	System.out.println("Position coord: " + xPosition.t + " , " + "Issue: " + xPosition.u);
    	System.out.println("Distance number: " + distance.t + " , " + "Issue " + distance.u);
    	/* Debugging
    	 * System.out.print("Distance: " + distance);
    	System.out.println();
    	System.out.print("Position: " + xPosition);
    	System.out.println();*/
    	if (distance.t <= 20 && distance.u == false){
    			DriveTrain.drive(.3, .3);
    			Timer.delay(2);
    			DriveTrain.drive(-.4, -.4);
    			Timer.delay(2);
    			DriveTrain.stop();
    			System.out.println("Short distance conditions satisfied");
    	}else if (distance.t > 20 && distance.u == false){
    		boolean tooFar = true;
    		while(tooFar){
    			if(button2.get()){
    				tooFar = false;
    				System.out.println("Terminating");
    				break;
    			}
    			distance = Robot.vision.getDistanceFromTarget(); 
    			xPosition = Robot.vision.getXPos();
    			System.out.println("xPos: " + xPosition.t);
    			if(!xPosition.u){
	    			if(xPosition.t < .12 && xPosition.t > -.12){
    					while(xPosition.t < .12 && xPosition.t > -.12){
    					xPosition = Robot.vision.getXPos();	
    					DriveTrain.drive(.4, .4);
	    				if(button2.get()){
	        				tooFar = false;
	        				System.out.println("Terminating");
	        				break;
	        			}
    					}
	    				//System.out.println("Within bounds");
	    			}
	    			else if(xPosition.t > 0.12){
	    				while(xPosition.t > 0.12){
	    					xPosition = Robot.vision.getXPos();
	    					DriveTrain.drive(.25,.4);
	    					if(button2.get()){
	    	    				tooFar = false;
	    	    				System.out.println("Terminating");
	    	    				break;
	    	    			}
	    					System.out.println("Too far to right");
	    				}
	    			}else if(xPosition.t < -0.12){
	    				while(xPosition.t < -.12){
	    					xPosition = Robot.vision.getXPos();
	    					DriveTrain.drive(.4,.25);
	    					if(button2.get()){
	    	    				tooFar = false;
	    	    				System.out.println("Terminating");
	    	    				break;
	    	    			}
	    					System.out.println("Too far to left");
	    				}
	    			}
    			}else{
    				while(xPosition.u){
    				DriveTrain.drive(.25, .25);
    				if(button2.get()){
        				tooFar = false;
        				System.out.println("Terminating");
        				break;
        			}
    				}
    				//System.out.println("No position found inside loop");
    			}
    			
    			if(distance.t <= 20 && distance.u == false){
    	    			DriveTrain.drive(.3, .3);
    	    			Timer.delay(2);
    	    			DriveTrain.drive(-.4, -.4);
    	    			Timer.delay(2);
    	    			DriveTrain.stop();
    	    			System.out.println("Exiting loop");
    	    			tooFar = false;
    			}else if (distance.u == true){
    				distance = Robot.vision.getDistanceFromTarget();
    				while(distance.u){
    					DriveTrain.drive(.25, .25);
    					if(button2.get()){
            				tooFar = false;
            				System.out.println("Terminating");
            				break;
            			}
    				}
    				//System.out.println("No distance found inside loop");
    			}
    		}
    	}else{
    		DriveTrain.drive(.25, .25);
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
