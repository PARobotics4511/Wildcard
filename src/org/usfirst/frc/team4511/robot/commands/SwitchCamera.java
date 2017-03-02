package org.usfirst.frc.team4511.robot.commands;

import org.usfirst.frc.team4511.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SwitchCamera extends Command {

	boolean done;
    public SwitchCamera() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	/*if (Robot.CamName == "cam0"){
    		Robot.CamName = "cam2";
    		Robot.CamNumber = 2;
    		done = true;
    	}else if(Robot.CamName == "cam2"){
    		Robot.CamName = "cam0";
    		Robot.CamNumber = 0;
    		done = true;
    	}else{
    		Robot.CamName = "cam2";
    		Robot.CamNumber = 2;
    		done = true;
    	}*/
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
