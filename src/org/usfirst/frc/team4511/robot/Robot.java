
package org.usfirst.frc.team4511.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

import org.usfirst.frc.team4511.robot.commands.Autonomous;
import org.usfirst.frc.team4511.robot.commands.ExampleCommand;
import org.usfirst.frc.team4511.robot.commands.OtherAuto;
import org.usfirst.frc.team4511.robot.commands.SideAuto;
import org.usfirst.frc.team4511.robot.subsystems.Arm;
import org.usfirst.frc.team4511.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4511.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team4511.robot.subsystems.GearLift;
import org.usfirst.frc.team4511.robot.subsystems.PhotoEye;
import org.usfirst.frc.team4511.robot.subsystems.SonarAnalog;
import org.usfirst.frc.team4511.robot.subsystems.Vision;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4511.robot.subsystems.Sonar;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static final Vision vision = new Vision();
	public static final DriveTrain drivetrain = new DriveTrain();
	public static final GearLift lift = new GearLift();
	public static final PhotoEye leftEye = new PhotoEye(2);
	public static final PhotoEye rightEye = new PhotoEye(3);
	//public static final PhotoEye middleEye = new PhotoEye(1);
	public static final PhotoEye armEye = new PhotoEye(0);
	public static final Arm armUno = new Arm(9);
	public static final Arm armDos = new Arm(8);
	public static final Sonar pulseFront = new Sonar(1, 0);
	//public static final Sonar pulseBack = new Sonar(3, 3);
	public static final SonarAnalog analogPulse = new SonarAnalog(1);
	public static OI oi;
	

    Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
    public static NetworkTable table;
    
    public Robot(){
    	table = NetworkTable.getTable("GRIP/Contours");
    }
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
        chooser.addDefault("No Turn", new OtherAuto());
        chooser.addObject("Turn", new Autonomous());
        chooser.addObject("Side", new SideAuto());
        SmartDashboard.putData("Auto mode", chooser);
        CameraServer cam = CameraServer.getInstance();
        cam.startAutomaticCapture("FrontCam", 0);
        CameraServer backCam = CameraServer.getInstance();
        backCam.startAutomaticCapture("BackCam", 1);
        
       
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
        autonomousCommand = chooser.getSelected();
        
		/*String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "Turn":
			autonomousCommand = new Autonomous();
			break;
		case "No Turn":
			autonomousCommand = new OtherAuto();
			break;
		case "Side":
			autonomousCommand = new SideAuto();
			break;
		default:
			autonomousCommand = new OtherAuto();
			break;
		} */
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    private static long lastPrint, currentTime;
    private static boolean wasIn, hasDone;
    
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        SmartDashboard.putDouble("Gear Boi", Robot.armEye.getVoltage()*1000);
        currentTime = System.currentTimeMillis();
        boolean inPlace = (Robot.armEye.getVoltage()*1000) > 3345;
        if (!hasDone){
        	wasIn = inPlace;
        	hasDone = true;
        	lastPrint = System.currentTimeMillis();
        	
        }else if (wasIn != inPlace || lastPrint + 500 < currentTime){
    		wasIn = inPlace;
    		lastPrint = currentTime;
    		if(inPlace){
    			SmartDashboard.putBoolean("Gear in the Carriage?", true);
    		} else {
    			SmartDashboard.putBoolean("Gear in the Carriage?", false);
    		}
    		
    	}
    }
    
    /*public void teleopPeriodic() {
        Scheduler.getInstance().run();
        //System.out.println(Robot.armEye.getVoltage()*1000);
        if((Robot.armEye.getVoltage()*1000) > 3355){
        	System.out.println("Gear in place!");
        }else{
        	System.out.println("No gear! Get good kid.");
        }
    }*/
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
