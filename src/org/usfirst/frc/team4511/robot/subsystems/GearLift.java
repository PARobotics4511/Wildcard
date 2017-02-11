package org.usfirst.frc.team4511.robot.subsystems;

import org.usfirst.frc.team4511.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearLift extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public static CANTalon liftMotor = new CANTalon(RobotMap.lift);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

