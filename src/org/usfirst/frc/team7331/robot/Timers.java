package org.usfirst.frc.team7331.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

public class Timers {
	
	private Timer autoTimer;

	/**
	 * Declare and load timers for auto mode
	 * 
	 * */
	public void loadTimers() {
		autoTimer = new Timer();
		autoTimer.start();	
	}
	
	/***
	 * Method to safely get the timer variable from earlier
	 * @return The Timer
	 */
	public Timer getAutoTimer() {
		return autoTimer;
	}
}
