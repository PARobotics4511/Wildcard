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
public class NewAuto extends Command {
	private static double BASE_SPEED = .6;
	boolean done;
	final double LINE = .7;
	private long milliStart;
	private boolean set = false;
	//final double CARPET = .5;
	JoystickButton button2 = new JoystickButton(OI.stick2, 1);
    public NewAuto() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	requires(Robot.leftEye);
    	requires(Robot.rightEye);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.gyro.reset();
    	System.out.println("LINE DRIVE STARTED");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (!set){
    		milliStart = System.currentTimeMillis();
    	}
    	double rightEye = Robot.rightEye.getVoltage();
    	double leftEye = Robot.leftEye.getVoltage();
    	//double middleEye = Robot.middleEye.getVoltage();
    	Pair<Double, Boolean> distance = Robot.vision.getDistanceFromTarget();
    	//System.out.println("leftEye: " + leftEye + "     middleEye: " + middleEye + "     rightEye: " + rightEye + "     Distance: " + distance.t);
    	/*if(rightEye > LINE && leftEye > LINE && middleEye < LINE){
    		DriveTrain.drive(.45, .45);
    	}
    	if((rightEye > LINE && leftEye < LINE) || (rightEye > LINE && middleEye < LINE)){
    		DriveTrain.drive(.25, .35);
    	}
    	if((rightEye < LINE && leftEye > LINE) || (leftEye > LINE && middleEye < LINE)){
    		DriveTrain.drive(.35, .25);
    	}*/
    	double angle;
    	while (true){
    		if (milliStart + 2200 < System.currentTimeMillis()){
    			break;
    		}
    		angle = Robot.drivetrain.gyro.getAngle();
    		Robot.drivetrain.drive(BASE_SPEED - angle / 50, BASE_SPEED + angle / 50);
    	}
    	DriveTrain.stop();
		GearLift.liftMotor.set(.4);
		Timer.delay(.8);
		DriveTrain.drive(-.65, -.65);
		Timer.delay(1.4);
		DriveTrain.drive(.8, .4);
		Timer.delay(1);
		DriveTrain.drive(.65, .65);
		Timer.delay(1);
		DriveTrain.drive(.4,.7);
		Timer.delay(1.2);
		DriveTrain.drive(.65,.65);
		GearLift.liftMotor.set(-.4);
		Timer.delay(2);
		done = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
    	set = false;
    	DriveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
