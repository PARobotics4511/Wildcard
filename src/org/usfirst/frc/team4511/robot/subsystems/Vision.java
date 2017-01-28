package org.usfirst.frc.team4511.robot.subsystems;

import java.sql.Time;

import org.usfirst.frc.team4511.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class Vision extends Subsystem {
   
    public Vision(){
    	
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public double getDistanceFromTarget(){
    	double distance = 0;
    	double[] defaultValue = new double[0];
    	double[] widths = Robot.table.getNumberArray("width", defaultValue);
    	if (widths.length == 0){
    		boolean issue = true;
    		while(issue){
    	    	widths = Robot.table.getNumberArray("width", defaultValue);
    	    	if(widths.length != 0){
    	    		issue = false;
    	    		distance = (835*2)/widths[0];
    	    	}else{
    	    		DriveTrain.drive(.15, .15);
    	    	}
    	    	
    		}
    	}
    	else{
    		distance = (835*2)/widths[0];
    	}
    	return distance;
    }
    
    public double getXPos(){
    	int cameraX = 640;
    	double[] defaultValue = new double[0];
    	double[] centers = Robot.table.getNumberArray("centerX", defaultValue);
    	double xPos = 0;
    	if(centers.length == 0){
    		boolean issue = true;
    		while(issue){
    			centers = Robot.table.getNumberArray("centerX", defaultValue);
    			if(centers.length != 0){
    				xPos = -((centers[0]-(cameraX/2))/(cameraX/2));
    				issue = false;
    			}else{
    	    		DriveTrain.drive(.15, .15);
    	    	}
    		}
    	    
    	}else{
    		xPos = -((centers[0]-(cameraX/2))/(cameraX/2));
    	}
    	return xPos;
    }
}

