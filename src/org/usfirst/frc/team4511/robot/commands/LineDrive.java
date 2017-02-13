package org.usfirst.frc.team4511.robot.commands;

import org.usfirst.frc.team4511.robot.OI;
import org.usfirst.frc.team4511.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4511.robot.subsystems.*;

/**
 *
 */
public class LineDrive extends Command {

	boolean done;
	final double LINE = 2.3;
	//final double CARPET = .5;
	JoystickButton button2 = new JoystickButton(OI.stick2, 1);
    public LineDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	requires(Robot.leftEye);
    	requires(Robot.rightEye);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double rightEye = Robot.rightEye.getVoltage();
    	double leftEye = Robot.leftEye.getVoltage();
    	double middleEye = Robot.middleEye.getVoltage();
    	Pair<Double, Boolean> distance = Robot.vision.getDistanceFromTarget();
    	System.out.println("rightEye: " + rightEye);
    	System.out.println("leftEye: " + leftEye);
    	System.out.println("middleEye: " + middleEye);
    	System.out.println("Distance: " + distance.t);
    	if(rightEye < LINE && leftEye < LINE && middleEye > LINE){
    		DriveTrain.drive(.55, .55);
    	}
    	if((rightEye < LINE && leftEye > LINE) || (rightEye < LINE && middleEye > LINE)){
    		DriveTrain.drive(.2, .4);
    	}
    	if((rightEye > LINE && leftEye < LINE) || (leftEye < LINE && middleEye > LINE)){
    		DriveTrain.drive(.4, .2);
    	}
    	/*if(distance.t <= 20){
    		DriveTrain.drive(.4, .4);
    		Timer.delay(2);
    		DriveTrain.stop();
    		GearLift.liftMotor.set(.4);
    		Timer.delay(2);
    		DriveTrain.drive(-.4, -.4);
    		Timer.delay(2);
    	}*/
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return button2.get();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
