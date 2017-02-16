package org.usfirst.frc.team4511.robot.commands;

import org.usfirst.frc.team4511.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArmUp extends Command {

    public ArmUp() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.armDos);
    }
    
    protected void initialize() {
    	Robot.armDos.initializeCounter();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.armDos.armUp();

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.armDos.isSwitchSet();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.armDos.armStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
