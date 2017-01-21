package org.usfirst.frc.team4511.robot.subsystems;

import org.usfirst.frc.team4511.robot.RobotMap;
import org.usfirst.frc.team4511.robot.commands.Drive;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	
	static CANTalon frontLeft = new CANTalon(RobotMap.fLeft);
	static CANTalon frontRight = new CANTalon(RobotMap.fRight);
	static CANTalon backLeft = new CANTalon(RobotMap.bLeft);
	static CANTalon backRight = new CANTalon(RobotMap.bRight);
	
	static RobotDrive rDrive = new RobotDrive(frontLeft, frontRight, backLeft, backRight);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new Drive());
    }
    
    public static void drive(double stick1, double stick2){
    	rDrive.tankDrive(stick1, stick2);
    }
    
    public static void stop(){
    	rDrive.tankDrive(0,0);
    }
    
}

