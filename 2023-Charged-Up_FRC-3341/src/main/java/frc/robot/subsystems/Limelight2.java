// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.fasterxml.jackson.databind.deser.impl.NullsAsEmptyProvider;

import edu.wpi.first.net.PortForwarder;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight2 extends SubsystemBase {
  /** Creates a new Limelight. */

  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight-shuzah");
  private Joystick joystick1 = RobotContainer.getJoy1();
  private Joystick joystick2 = RobotContainer.getJoy2();

  private double txNum2;
  private double tyNum2;
  private double taNum2;
  private int tvNum2;
  // pipeline2 0 - reflective tape
  // pipeline2 1 to 7 - april tags
  // pipeline2 8 - cube
  // pipeline2 9 - cone
  public int pipeline2 = 0;

  // This gets the tx, or the horizontal offset
  // from the crosshair in degrees (-27.0 to 27.0)
  NetworkTableEntry tx2 = table.getEntry("tx2");

  // This gets the ty, or the vertical offset
  // from the crosshair in degrees (-20.5 to 20.5)
  NetworkTableEntry ty2 = table.getEntry("ty2");

  // This gets the ta, or how much in % of the target
  // is visible (0.0-100.0)
  NetworkTableEntry ta2 = table.getEntry("ta2");

  // This gets the tv, which sees if the limelight
  // has a valid target (1) or no valid target (0)
  NetworkTableEntry tv2 = table.getEntry("tv2");

  public Limelight2() {
    // We have to add these ports so that we can connect to
    // the limelight with our code through the robot's wifi
    // PortForwarder.add(5800, "http://10.33.41.11:5801/", 5800);
    PortForwarder.add(5801, "http://limelight-shuzah.local:5801", 5801);
    PortForwarder.add(5802, "http://limelight-shuzah.local:5801", 5802);
    PortForwarder.add(5803, "http://limelight-shuzah.local:5801", 5803);
    PortForwarder.add(5804, "http://limelight-shuzah.local:5801", 5804);
    PortForwarder.add(5805, "http://limelight-shuzah.local:5801", 5805);
    PortForwarder.add(5800, "http://limelight-shuzah.local:5801", 5800);
  }

  public void changepipeline2(int pipeline2) {
    table.getEntry("pipeline2").setNumber(pipeline2);
  }

  public double get_tx2() {
    return txNum2;
  }

  public double get_ty2() {
    return tyNum2;
  }

  public double get_ta2() {
    return taNum2;
  }

  public int get_tv2() {
    return tvNum2;
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    if (joystick1.getRawButtonPressed(3)) {
      pipeline2 = 0; // reflective tape
      changepipeline2(pipeline2);
    } else if (joystick1.getRawButtonPressed(4)) {
      pipeline2 = 9; // cone
      changepipeline2(pipeline2);
    } else if (joystick1.getRawButtonPressed(5)) {
      pipeline2 = 8; // square
      changepipeline2(pipeline2);
    } else if (joystick1.getRawButtonPressed(6)) {
      pipeline2 = 1; // april tag 1
      changepipeline2(pipeline2);
    } else if (joystick1.getRawButtonPressed(7)) {
      pipeline2 = 2; // april tag 2
      changepipeline2(pipeline2);
    } else if (joystick1.getRawButtonPressed(8)) {
      pipeline2 = 3; // april tag 3
      changepipeline2(pipeline2);
    } else if (joystick1.getRawButtonPressed(9)) {
      pipeline2 = 4; // april tag 4
      changepipeline2(pipeline2);
    } else if (joystick1.getRawButtonPressed(10)) {
      pipeline2 = 5; // april tag 5
      changepipeline2(pipeline2);
    } else if (joystick1.getRawButtonPressed(11)) {
      pipeline2 = 6; // april tag 6
      changepipeline2(pipeline2);
    } 

  
    tx2 = table.getEntry("tx2");
    ty2 = table.getEntry("ty2");
    ta2 = table.getEntry("ta2");
    tv2 = table.getEntry("tv2");

    // tvNum can be either 1 or 0, so we instantiate by adding (int) in front
    // We will be assigning tvNum to the int (1 or 0) that limelight returns
    tvNum2 = (int) tv2.getDouble(0.0);

    // We will be assigning tyNum to the double (-27.0 to 27.0) that limelight
    // returns
    txNum2 = tx2.getDouble(0.0);

    // We will be assigning tyNum to the double (-20.5 to 20.5) that limelight
    // returns
    tyNum2 = ty2.getDouble(0.0);

    // We will be assigning taNum to the double (0.0-100.0) that limelight returns
    taNum2 = ta2.getDouble(0.0);
    // This will output the x (horizontal offset) from the target in SmartDashboard
    SmartDashboard.putNumber("LimelightX2", txNum2);

    // This will output the y (vertical offset) from the target in SmartDashboard
    SmartDashboard.putNumber("LimelightY2", tyNum2);

    // This will output the area of the target in SmartDashboard
    SmartDashboard.putNumber("LimelightArea2", taNum2);

    // This will output the value of the target in SmartDashboard (0 or 1)
    SmartDashboard.putNumber("LimelightV2", tvNum2);

    // SmartDashboard.putNumber("pipeline2Number", pipeline2);
    // Actual pipeline2 number not representative
    SmartDashboard.putNumber("pipelineName2", table.getEntry("pipeline2").getDouble(0));// Actual piepline
  }
}
