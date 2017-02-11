package org.usfirst.frc.team4511.robot.subsystems;

import edu.wpi.first.wpilibj.AccumulatorResult;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PhotoEye extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.


	AnalogInput ai;
	int bits;
	
	public PhotoEye(int port){
		ai = new AnalogInput(port);
		AnalogInput.setGlobalSampleRate(62500);
	}
	
	public double getVoltage(){
		double volts = ai.getVoltage();
		return volts;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

