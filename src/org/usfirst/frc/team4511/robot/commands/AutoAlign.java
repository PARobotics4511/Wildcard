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
	final double POS_THRESHOLD = .1;
	final double NEG_THRESHOLD = -.1;
	final double TARGET_DISTANCE = 10;
	final double driveSpeed = .4;
	final double turnSpeed = .3;
	boolean shouldExit;
	boolean commandDone;
	public AutoAlign() {
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
    		DriveTrain.drive(turnSpeed, driveSpeed);
    	}else if(xPosition.t < NEG_THRESHOLD){
    		DriveTrain.drive(driveSpeed, turnSpeed);
    	}else if(distance.t <= TARGET_DISTANCE){
    		DriveTrain.drive(-driveSpeed, -driveSpeed);
    		Timer.delay(2);
    		DriveTrain.stop();
    		commandDone = true;
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(commandDone || shouldExit){
        	return true;
        }
    	return false;
    }


    // Called once after isFinished returns true
    protected void end() {
    	DriveTrain.stop();
    	shouldExit = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
