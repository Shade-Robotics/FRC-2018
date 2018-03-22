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
	private static Spark motor_leftRear, motor_leftFront, motor_rightRear, motor_rightFront;
	private static TalonSRX motor_conveyor;
	
	/**
	 * Speed Controller Variables
	 * 
	 * */
	private static SpeedControllerGroup speedController_left, speedController_right;
	
	/**
	 * Drive Variables
	 * 
	 * */
	private static DifferentialDrive drive_wheel;
	
	/**
	 * Declare and load motors and speed controllers
	 * 
	 * */
	public static void loadMotorsAndSpeedControllers() {
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
		
		//Fix inverted motors
		speedController_left.setInverted(true);
		speedController_right.setInverted(true);
		
		//Declare the motor drives
		drive_wheel = new DifferentialDrive(speedController_left, speedController_right);
	}

	public static SpeedControllerGroup getLeftSpeedController() {
		return speedController_left;
	}
	
	public static SpeedControllerGroup getRightSpeedController() {
		return speedController_right;
	}
	
	public static DifferentialDrive getMotorDrive() {
		return drive_wheel;
	}
	
	public static TalonSRX getConveyorBelt() {
		return motor_conveyor;
	}
}
