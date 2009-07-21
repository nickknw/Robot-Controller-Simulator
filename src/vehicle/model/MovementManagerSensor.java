package vehicle.model;

import vehicle.controller.*;
import util.*;


/*****************************************************************************
 *  File:       MovementManagerSensor.java
 *  Authors:    Sean Jagat and Nick Knowlson
 *  Assignment: 4
 *  Class:      CPS235
 *  Instructor: Frank Niscak
 *  Due Date:   November 23, 2007
 *
 *  Purpose:    The MovementManagerSensor class manages the movement of the
 *              vehicle
 ****************************************************************************/

public class MovementManagerSensor implements Runnable
{
  private static MovementManagerSensor instance = null;
  private int iLeftMotorLevel = Constants.DEFAULT_SPEED,
      iRightMotorLevel = Constants.DEFAULT_SPEED, iDegrees;
  private double dXPos, dYPos, dDistance;

  //Private constructor to implement singleton design
  private MovementManagerSensor()
  {
  }

  /**
   * Gets the instance of the MovementManagerSensor class
   * @return The instance of the MovementManagerSensor class
   */
  public static MovementManagerSensor getInstance()
  {
    if (instance == null)
      instance = new MovementManagerSensor();
    return instance;
  }

  /**
   * Calculates the movement of the vehicle
   */
  public void run()
  {

    //while battery is still alive, keep on running
    while (BatterySensor.getInstance().getBatteryValue() > 0
           && Controller.getInstance().isAlive())
    {
      while (!Controller.getInstance().isRunning())
        sleep();
      sleep();
      /*This is a simulation of the direction it will face
       0 is straight up, 90 is right, 180 is down, and 270 is left. */
      iDegrees = (iDegrees + (iLeftMotorLevel - iRightMotorLevel));


      //make it move in the direction its facing and update the
      //coordinates
      dDistance += ((iLeftMotorLevel + iRightMotorLevel) / 2);
      dXPos += findXComponent((iLeftMotorLevel + iRightMotorLevel) / 2.0,
                              iDegrees);
      dYPos += findYComponent((iLeftMotorLevel + iRightMotorLevel) / 2.0,
                              iDegrees);


      update();
    }
  }

  /*
   * A convenient sleeping method
   */
  private void sleep()
  {
    try
    {
      Thread.sleep(Constants.TIME_TO_SLEEP);
    }
    catch(InterruptedException e )
    {
      Debug.printError("Movement thread interrupted");
    }
  }

  /*
   * Updates the movement information to the controller
   */
  private void update()
  {
    Controller.getInstance().write(
                                   Constants.MOVEMENT_MANAGER
                                       + Constants.UPDATE + iLeftMotorLevel
                                       + " " + iRightMotorLevel + " "
                                       + iDegrees + " " + dXPos + " " + dYPos
                                       + " " + dDistance);
  }

  /**
   * Returns the battery level of the vehicle
   * @return The battery level of the vehicle
   */
  public int getPowerLevel()
  {
    return (iLeftMotorLevel * iRightMotorLevel) / 2;
  }

  /*
   * Finds the X difference to apply to the X position
   */
  private double findXComponent(double dHypotenuse, int iAngle)
  {
    return (dHypotenuse * Math.sin(Math.toRadians(iAngle)));
  }

  /*
   * Finds the Y difference to apply to the Y position
   */
  private double findYComponent(double dHypotenuse, int iAngle)
  {
    return (dHypotenuse * Math.cos(Math.toRadians(iAngle)));
  }

  /**
   * Gets the speed of the left motor
   * @return The speed of the left motor
   */
  public int getLeftMotorLevel()
  {
    return iLeftMotorLevel;
  }

  /**
   * Sets the speed of the left motor
   * @param leftMotorLevel The speed of the left motor
   */
  public synchronized void setLeftMotorLevel(int leftMotorLevel)
  {
    this.iLeftMotorLevel = leftMotorLevel;
  }

  /**
   * Gets the speed of the right motor
   * @return The speed of the right motor
   */
  public int getRightMotorLevel()
  {
    return iRightMotorLevel;
  }

  /**
   * Sets the speed of the right motor
   * @param rightMotorLevel The speed of the right motor
   */
  public synchronized void setRightMotorLevel(int rightMotorLevel)
  {
    this.iRightMotorLevel = rightMotorLevel;
  }

  /**
   * Gets the current X position of the vehicle
   * @return The current X position of the vehicle
   */
  public double getXPos()
  {
    return dXPos;
  }

  /**
   * Gets the current Y position of the vehicle
   * @return The current Y position of the vehicle
   */
  public double getYPos()
  {
    return dYPos;
  }

  /**
   * Resets the MovementManagerSensor to its initial state and updates the
   * values
   *
   */
  public synchronized void reset()
  {
    dYPos = 0;
    dXPos = 0;
    iRightMotorLevel = 0;
    iLeftMotorLevel = 0;
    dDistance = 0;
    iDegrees = 0;
    update();
  }
}
