package org.usfirst.frc.team4511.robot.commands;

import org.usfirst.frc.team4511.robot.Robot;
import org.usfirst.frc.team4511.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Align extends Command {

	Pair<Double, Boolean> distance;
	Pair<Double, Boolean> xPosition;
	final double POS_THRESHOLD = .1;
	final double NEG_THRESHOLD = -.1;
	final double driveSpeed = .4;
	boolean shouldExit;
	public Align() {
    	requires(Robot.vision);
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	distance = Robot.vision.getDistanceFromTarget();
    	xPosition = Robot.vision.getXPos();
    	System.out.println("Initial Distance: " + distance.t + " Distance Issue:" + distance.u);
    	System.out.println("Initial Position: " + xPosition.t + " Position Issue:" + xPosition.u);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	distance = Robot.vision.getDistanceFromTarget();
    	xPosition = Robot.vision.getXPos();
    	
    	shouldExit |= distance.u || xPosition.u;
    	if(shouldExit){
    		System.out.println("You are not aligned, try again.");
    	}
    	if(xPosition.t < POS_THRESHOLD && xPosition.t >  NEG_THRESHOLD){
    		DriveTrain.drive(driveSpeed, driveSpeed);
    	}else if(xPosition.t > POS_THRESHOLD){
    		DriveTrain.drive(.3, .4);
    	}else if(xPosition.t < NEG_THRESHOLD){
    		DriveTrain.drive(.4, .3);
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
