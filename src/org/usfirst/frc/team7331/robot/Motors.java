package org.usfirst.frc.team7331.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Motors {
	/**
	 * Motor Variables
	 * 
	 * */
	private Spark motor_leftRear, motor_leftFront, motor_rightRear, motor_rightFront;
	private TalonSRX motor_conveyor;
	
	/**
	 * Speed Controller Variables
	 * 
	 * */
	private SpeedControllerGroup speedController_left, speedController_right;
	
	/**
	 * Drive Variables
	 * 
	 * */
	private DifferentialDrive drive_wheel;
	
	/**
	 * Declare and load motors and speed controllers
	 * 
	 * */
	public void loadMotorsAndSpeedControllers() {
		//Declare each of the Spark Motor Controllers
		motor_leftFront = new Spark(2);
		motor_leftRear = new Spark(1);
		motor_rightFront= new Spark(3);
		motor_rightRear = new Spark(0);
		
		//Declare each of the TalonSRX Motor Controllers
		motor_conveyor = new TalonSRX(4);
		
		//Set the Speed Controller groups
		speedController_left = new SpeedControllerGroup(motor_leftFront, motor_leftRear);
		speedController_right = new SpeedControllerGroup(motor_rightFront, motor_rightRear);
		
		//Declare the motor drives
		drive_wheel = new DifferentialDrive(speedController_left, speedController_right);
	}

	public SpeedControllerGroup getLeftSpeedController() {
		return speedController_left;
	}
	
	public SpeedControllerGroup getRightSpeedController() {
		return speedController_right;
	}
	
	public DifferentialDrive getMotorDrive() {
		return drive_wheel;
	}
	
	public TalonSRX getConveyerBelt() {
		return motor_conveyor;
	}
}
