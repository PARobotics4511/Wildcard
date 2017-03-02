package org.usfirst.frc.team4511.robot.commands;

import org.usfirst.frc.team4511.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SideTurnAuto extends Command {

	boolean right;
    public SideTurnAuto(boolean right) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.right = right;
    	requires(Robot.analogPulse);
    	requires(Robot.drivetrain);
    	requires(Robot.pulseFront);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.gyro.reset();
    	Robot.drivetrain.drive(.5, .5);
    	double angle;
    	while (true){
    		angle = Robot.drivetrain.gyro.getAngle();
    		Robot.drivetrain.drive(.5 - angle / 50, .5 + angle / 50);
    		if (Robot.analogPulse.getDistance() > 78.5){
    			break;
    		}
    	}
    	Robot.drivetrain.drive((right ? 1 : -1) * .25, (right ? -1 : 1) *  .25);
    	while (true){
    		if (Math.abs(Robot.drivetrain.gyro.getAngle()) > 30){
    			break;
    		}
    	}
    	Robot.drivetrain.drive(.35, .35);
    	while (true){
    		if (Robot.pulseFront.sonic.getRangeInches() < 8){
    			break;
    		}
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
