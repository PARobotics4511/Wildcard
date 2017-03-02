package org.usfirst.frc.team4511.robot.subsystems;

import org.usfirst.frc.team4511.robot.Robot;
import org.usfirst.frc.team4511.robot.commands.Pair;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Vision extends Subsystem {
   
    public Vision(){
    	
    }
    
    public void initDefaultCommand() {
       
    }
    
    public Pair<Double, Boolean> getDistanceFromTarget(){
    	double distance = 0;
    	double[] defaultValue = new double[0];
    	double[] widths = Robot.table.getNumberArray("width", defaultValue);
    	/*if(widths.length == 2){
    	    distance = (835*2)/widths[0];
    	}*/
    	if(widths.length > 0){
    		distance = (835*2/widths[0]);
    	}
    	return new Pair<>(distance, widths.length != 2);
    }
    
    public Pair<Double, Boolean> getXPos(){
    	int cameraX = 640;
    	double[] defaultValue = new double[0];
    	double[] centers = Robot.table.getNumberArray("centerX", defaultValue);
    	System.out.println(centers.length);
    	double xPos = 0;
    	if(centers.length == 2){
    		xPos = -((centers[1]-centers[0])-(cameraX/2))/(cameraX/2);
    	}
    	return new Pair<>(xPos, centers.length != 2);
    }   	

////////////////////////////////////////////////////TESTING NEW



/*	public Pair<Double, Boolean> getTestDistanceFromTarget(){
		double distance = 0;
		double[] defaultValue = new double[0];
		double[] widths = Robot.table.getNumberArray("width", defaultValue);
		if(widths.length == 2){
	    	distance = (835*2)/widths[0];
		}
		if(widths.length > 0){
			distance = (835*2/widths[0]);
		}
		return new Pair<>(distance, widths.length != 2);
	}

	public Pair<Double, Boolean> getTestXPos(){
		int cameraX = 640;
		double[] defaultValue = new double[0];
		double[] centers = Robot.table.getNumberArray("centerX", defaultValue);
		System.out.println(centers.length);
		double xPos = 0;
		if(centers.length == 2){
			xPos = -((centers[1]-centers[0])-(cameraX/2))/(cameraX/2);
		}
		return new Pair<>(xPos, centers.length != 2);
	}*/
	    
}
