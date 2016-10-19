package org.usfirst.frc.team2872.robot;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    
	RobotDrive myRobot;
	Joystick stick1;
	Joystick stick2;
	Joystick Xbox;
	int autoLoopCounter;
	Victor Winch = new Victor(2);
	Jaguar Winch2 = new Jaguar(3);
	Compressor comp = new Compressor(0);
	DoubleSolenoid Sol = new DoubleSolenoid(0,1);
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	myRobot = new RobotDrive(0,1);
    	stick1 = new Joystick(0);
    	stick2 = new Joystick(1);
    	//Xbox = new Joystick(2);
    	Winch.set(-.15);
    	Winch2.set(.15);
    	Sol.set(DoubleSolenoid.Value.kOff);
    }
    
    /**
     * This function is run once each time the robot enters autonomous mode
     */
    public void autonomousInit() {
    	autoLoopCounter = 0;
    	Sol.set(DoubleSolenoid.Value.kForward);
    	for(int i=0;i<50;i++)
    		Winch.set(-.9);
    	Winch.set(0);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    
    	if(autoLoopCounter < 300) //Check if we've completed 300 loops (approximately 6 seconds)
		{
			myRobot.drive(-0.25, 0.0); 	// drive forwards half speed
			autoLoopCounter++;
			} else {
			myRobot.drive(0.0, 0.0); 	// stop robot
		}

    }
    
    /**
     * This function is called once each time the robot enters tele-operated mode
     */
    public void teleopInit(){
    	 Winch.set(-.15);
    	 Winch2.set(.15);
    }

    /**
     * This function is called periodically during operator control
     */
    
    
    public void teleopPeriodic() {
        myRobot.tankDrive(stick1.getY()*.65,stick2.getY()*.65);
        Winch.set(-.15);
        Winch2.set(.15);
        if(stick1.getRawButton(2))
        	Winch.set(.25);
        if(stick1.getRawButton(3))
        	Winch.set(-.9);
        if(stick1.getRawButton(8))
        	Winch2.set(.9);
        if(stick1.getRawButton(9))
        	Winch2.set(-.25);
        if(stick1.getRawButton(4))
        	Sol.set(DoubleSolenoid.Value.kForward);
        if(stick1.getRawButton(5))
        	Sol.set(DoubleSolenoid.Value.kReverse);
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	LiveWindow.run();
    }
    
}
