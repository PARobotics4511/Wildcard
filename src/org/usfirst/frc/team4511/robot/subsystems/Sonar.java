package org.usfirst.frc.team4511.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Ultrasonic;
import org.usfirst.frc.team4511.robot.commands.GetSonar;

/**
 *
 */
public class Sonar extends Subsystem {
	
	public Ultrasonic sonic;
	
	public Sonar(int ping, int echo){
		sonic = new Ultrasonic(ping, echo);
		sonic.setAutomaticMode(true);
	}
	
	public double testReadings(){
		//System.out.println(sonic.isEnabled());
		//System.out.println("Rear: " + sonic.getRangeInches());
		return sonic.getRangeInches();
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new GetSonar());
    }
}

