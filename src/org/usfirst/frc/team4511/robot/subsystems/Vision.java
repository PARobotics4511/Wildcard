package org.usfirst.frc.team4511.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class Vision extends Subsystem {
    NetworkTable table;
   
    public Vision(){
    	table = NetworkTable.getTable("GRIP/Contours");
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public double getDistanceFromTarget(){
    	double distance;
    	double[] defaultValue = new double[0];
    	double[] widths = table.getNumberArray("width", defaultValue);
    	distance = (1060*2)/widths[0];
    	return distance;
    }
}

