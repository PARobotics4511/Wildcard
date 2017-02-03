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
    	boolean issue;
    	double[] defaultValue = new double[0];
    	double[] widths = Robot.table.getNumberArray("width", defaultValue);
    	if(widths.length != 0){
    	    issue = false;
    	    distance = (835*2)/widths[0];
    	    }
    	else{
    		distance = 0;
    		issue = true;
    	}
    	return new Pair<>(distance, issue);
    }
    
    public Pair<Double, Boolean> getXPos(){
    	int cameraX = 640;
    	double[] defaultValue = new double[0];
    	double[] centers = Robot.table.getNumberArray("centerX", defaultValue);
    	double xPos = 0;
    	boolean issue;
    	if(centers.length != 0){
    		xPos = -(centers[0]-(cameraX/2))/(cameraX/2);
    		issue = false;
    	}else{
    	    xPos = 0;
    	    issue = true;
    	    }
    	return new Pair<>(xPos, issue);
    	}
    	    
	}   	


