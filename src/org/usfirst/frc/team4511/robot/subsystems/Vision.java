package org.usfirst.frc.team4511.robot.subsystems;

import java.sql.Time;

import org.usfirst.frc.team4511.robot.Robot;
import org.usfirst.frc.team4511.robot.commands.Pair;
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
    
    public Pair<Double, Boolean> getDistanceFromTarget(){
    	double distance = 0;
    	boolean issue;
    	double[] defaultValue = new double[0];
    	double[] widths = Robot.table.getNumberArray("width", defaultValue);
    	if(widths.length != 0){
    	    issue = true;
    	    distance = (835*2)/widths[0];
    	    }
    	else{
    		distance = 0;
    		issue = false;
    	}
    	return new Pair<>(distance, issue);
    }
    
    public Pair<Double, Boolean> getXPos(){
    	int cameraX = 640;
    	double[] defaultValue = new double[0];
    	double[] centers = Robot.table.getNumberArray("centerX", defaultValue);
    	double xPos = 0;
    	boolean issue;
    	if(centers.length == 2){
    		xPos = -(Math.abs((centers[0]-centers[1])/2)-(cameraX/2)/(cameraX/2));
    		issue = true;
    	}else{
    	    xPos = 0;
    	    issue = false;
    	    }
    	return new Pair<>(xPos, issue);
    	}
    	    
	}   	


