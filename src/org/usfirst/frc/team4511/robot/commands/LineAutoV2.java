package org.usfirst.frc.team4511.robot.commands;

import org.usfirst.frc.team4511.robot.OI;
import org.usfirst.frc.team4511.robot.Robot;
import org.usfirst.frc.team4511.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4511.robot.subsystems.GearLift;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LineAutoV2 extends Command {
	boolean done;
	double LINE = 1.8;
	JoystickButton br2 = new JoystickButton(OI.stick2, 2);
    public LineAutoV2() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	requires(Robot.leftEye);
    	requires(Robot.rightEye);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    
    	//LINE = ((Robot.rightEye.getVoltage() + Robot.leftEye.getVoltage())/2 + Robot.middleEye.getVoltage())/2;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	/*double rightEye = Robot.rightEye.getVoltage();
    	double leftEye = Robot.leftEye.getVoltage();
    	//double middleEye = Robot.middleEye.getVoltage();
    	Pair<Double, Boolean> distance = Robot.vision.getDistanceFromTarget();
    	System.out.println("rightEye: " + rightEye);
    	System.out.println("leftEye: " + leftEye);
    	//System.out.println("middleEye: " + middleEye);
    	//System.out.println("Distance: " + distance.t);
    	/*if(rightEye > LINE && leftEye > LINE && middleEye < LINE){
    		DriveTrain.drive(.55, .55);
    	}
    	if((rightEye > LINE && leftEye < LINE) || (rightEye > LINE && middleEye < LINE)){
    		DriveTrain.drive(.08, .15);
    	}
    	if((rightEye < LINE && leftEye > LINE) || (leftEye > LINE && middleEye < LINE)){
    		DriveTrain.drive(.15, .08);
    	}
    	if(distance.t <= 30 && distance.t != 0){
    		DriveTrain.drive(.4, .4);
    		Timer.delay(3);
    		DriveTrain.stop();
    		GearLift.liftMotor.set(.4);
    		Timer.delay(2);
    		DriveTrain.drive(-.4, -.4);
    		Timer.delay(2);
    		done =  true;
    	}
    	if(br2.get()){
    		done = true;
    	}*/
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
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
