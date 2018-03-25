package org.usfirst.frc.team7331.robot;

import edu.wpi.first.wpilibj.Joystick;

/*** Arrow Button (POV) degree key
 * 
 * -1 = Nothing
 * 0 = up
 * 45 = up-right
 * 90 = right
 * 135 = down-right
 * 180 = bottom
 * 225 = down-left
 * 270 = left
 * 315 = up-left
 */

public class Controls {
	
	private Joystick logitechController;
	
	/***
	 * Declare and Load all controllers
	 */
	public void loadControls() {
		//Declare the Logitech F310 Controller
		logitechController = new Joystick(0);
	}
	
	
	/***
	 * Method to safely get the joystick variable from earlier
	 * @return The Joystick
	 */
	public Joystick getContoller() {
		return logitechController;
	}
	
	

}