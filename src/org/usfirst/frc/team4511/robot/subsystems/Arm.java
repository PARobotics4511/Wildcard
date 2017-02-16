package org.usfirst.frc.team4511.robot.subsystems;

import org.usfirst.frc.team4511.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Arm extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	Counter counter;
	DigitalInput limitSwitch;
	public Arm(int port){
		limitSwitch = new DigitalInput(port);
		counter = new Counter(limitSwitch);
	}
	 
	CANTalon armMotor = new CANTalon(RobotMap.lift);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public boolean isSwitchSet() {
		System.out.println(!limitSwitch.get());
		return !limitSwitch.get();
	}
	public void initializeCounter() {
		System.out.println("Counter initialized");
		counter.reset();
	}
	public void armUp() {
		System.out.println("Arm up!");
		armMotor.set(0.5);
	}
	public void armDown() {
		System.out.println("Arm down!");
		armMotor.set(-0.5);
	}
	public void armStop() {
		System.out.println("Stop Arm");
		armMotor.set(0.0);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

