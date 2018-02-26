package org.usfirst.frc.team7331.robot;

import edu.wpi.first.wpilibj.Joystick;


/***
 * 
 * @author Team SHADE
 * -1 = Nothing
 * 0 = up
 * 90 = right 
 * 180 = bottom
 * 270 = left
 */
public class Controls {
	
	private static Joystick logitechController;
	
	public static void loadControls() {
		//Declare the Logitech F310 Controller
		logitechController = new Joystick(0);
	}
	
	public static Joystick getContoller() {
		return logitechController;
	}

}