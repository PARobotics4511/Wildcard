package org.usfirst.frc.team4511.robot.subsystems;

import org.usfirst.frc.team4511.robot.commands.GetSonarAnalog;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SonarAnalog extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	AnalogInput ai;
	int bits;
	
	public SonarAnalog(int port){
		ai = new AnalogInput(port);
		System.out.println("ANALOG SONAR: " + port);
		AnalogInput.setGlobalSampleRate(62500);
	}
	
	public double getDistance(){
		double milvolts = ai.getVoltage() * 1000;
		double scaleFactor = 0.1;
		double distance = milvolts * scaleFactor;
		return distance;
	}
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new GetSonarAnalog());
    }
}

