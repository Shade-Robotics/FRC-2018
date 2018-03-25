/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team7331.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
	private static final String kDefaultAuto = "Default";
	private static final String kCustomAuto = "My Auto";
	private String m_autoSelected;
	private SendableChooser<String> m_chooser = new SendableChooser<>();
	private Timers timerControl;
	private Controls controllers;
	private Motors motors;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_chooser.addDefault("Default Auto", kDefaultAuto);
		m_chooser.addObject("My Auto", kCustomAuto);
		SmartDashboard.putData("Auto choices", m_chooser);
		
		//Declare the Classes for motors, timerControl and controllers
		motors = new Motors();
		controllers = new Controls();
		timerControl = new Timers();
		
		//Load the motors and speed controllers 
		motors.loadMotorsAndSpeedControllers();
		
		//Load the joystick controls
		controllers.loadControls();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional comparisons to
	 * the switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	
	@Override
	public void autonomousInit() {
		//m_autoSelected = m_chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		//System.out.println("Auto selected: " + m_autoSelected);
		
		//Declare all timers in the Timer class
		timerControl = new Timers();
		timerControl.loadTimers();
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		//The values for leftSpeed and rightSpeed are set to negative to make the robot move forward
		//The rightSpeed is slower since the robot seems to deviate to the left
		//Speed for the left side of the robot
		double leftSpeed = -0.9;
		//Speed for the right side of the robot
		double rightSpeed = -0.87;
		
		//Time in seconds for the auto program to run
		double autoTime = 1.5;
		
		//Keep driving the robot straight forward until the value of autoTime has passed
		if (timerControl.getAutoTimer().get() < autoTime) { 
			motors.getMotorDrive().tankDrive(leftSpeed, rightSpeed); //The left side goes faster than the right to prevent right direction deviation
		} else {
			motors.getMotorDrive().tankDrive(0, 0);
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		
		//These values here are meant for modifying the speeds of the wheels and conveyer belt
		double slowDownMotorDrive = 0.6;
		double slowDownConveyerWithTigger = 0.62;
		double slowdownConveyerWithoutTrigger = 1;
		
		if (Math.abs(controllers.getContoller().getRawAxis(2)) > 0.5) { //If the LEFT TRIGGER button is pressed, then make the robot slow down when using the LEFT joystick to move it
			//Make the wheels move with the LEFT JOYSTICK by a factor of the slowDownMotorDrive value
			motors.getMotorDrive().arcadeDrive((controllers.getContoller().getRawAxis(1)) *slowDownMotorDrive, (controllers.getContoller().getRawAxis(0)) * slowDownMotorDrive);
		} else { //If not.. then allow normal speeds
			//Make the wheels move with the LEFT JOYSTICK based on the stick's axis data
			motors.getMotorDrive().arcadeDrive((controllers.getContoller().getRawAxis(1)), (controllers.getContoller().getRawAxis(0)));
		}
		
		if (Math.abs(controllers.getContoller().getRawAxis(3)) > 0.5) { //If the RIGHT TRIGGER button is pressed, then make the conveyer belt slow down when using the RIGHT JOYSTICK to move it
			//Make the conveyer belt move with the RIGHT JOYSTICK by a factor of the value of slowDownConveyerWithTigger
			motors.getConveyerBelt().set(ControlMode.PercentOutput, (controllers.getContoller().getRawAxis(5)) * slowDownConveyerWithTigger);
		} else {
			//Make the conveyer belt move with the RIGHT JOYSTICK by a factor of the value of slowdownConveyerWithoutTrigger
			motors.getConveyerBelt().set(ControlMode.PercentOutput, (controllers.getContoller().getRawAxis(5)) * slowdownConveyerWithoutTrigger);
		}	
		
		
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		//Get the Arrow button layouts
		System.out.println(controllers.getContoller().getPOV());
		//Get the number of buttons on the Logitech Controller
		//System.out.println("Button Count:"+Controls.getContoller().getButtonCount());
	}
}