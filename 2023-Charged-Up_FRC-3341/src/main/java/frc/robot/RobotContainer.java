// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Limelight;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;




public class RobotContainer {
  public static Joystick joystick2;
  public static Joystick joystick1;
  private final TankDrive tankDrive;
  private static DriveTrain dt;
  
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
 
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    joystick1 = new Joystick(0);
    joystick2 = new Joystick(1);
    configureButtonBindings();
    // Configure the button bindings
    dt = new DriveTrain();
    tankDrive = new TankDrive(dt, joystick2, joystick1);
    

  }
  public static Joystick getJoy1() {
    return joystick1;

  }
  public static Joystick getJoy2() {
    return joystick2;
  }
 

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */

  private void configureButtonBindings() {}


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  
   public static DriveTrain getDriveTrain(){
    return dt;
  }

  public Command getAutonomousCommand(){
    return m_autoCommand;
  }


