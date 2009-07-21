package client.controller;

import java.util.concurrent.*;
import util.*;


/*****************************************************************************
 *  File:       SensorInfo.java
 *  Authors:    Sean Jagat and Nick Knowlson
 *  Assignment: 4
 *  Class:      CPS235
 *  Instructor: Frank Niscak
 *  Due Date:   November 23, 2007
 *
 *  Purpose:    The SensorInfo class stores all of the information about the
 *              sensors for use by the GUI.
 ****************************************************************************/

public class SensorInfo
{
  private LinkedBlockingQueue<Integer> iLight, iTemperature, iSound;
  private LinkedBlockingQueue<Double> qXPos, qYPos;
  private double dDistance, dCurrentXPos, dCurrentYPos;
  private int iCharge, iLeftMotorLevel, iRightMotorLevel, iDegrees,
      iSliderSpeed, iSliderDirection;
  private boolean bTouch;
  private static SensorInfo instance = null;

  //Private constructor to implement singleton design
  private SensorInfo()
  {
    reset();
  }

  /**
   * Resets the value of all of the sensors and their history
   *
   */
  public void reset()
  {
    qXPos = new LinkedBlockingQueue<Double>();
    qYPos = new LinkedBlockingQueue<Double>();
    iLight = new LinkedBlockingQueue<Integer>();
    iTemperature = new LinkedBlockingQueue<Integer>();
    iSound = new LinkedBlockingQueue<Integer>();
    iCharge = Constants.MAX_BATTERY;
    iSliderSpeed = Constants.DEFAULT_SPEED;
    iSliderDirection = Constants.DEFAULT_STEERING;
  }

  /**
   * Gets the instance of the SensorInfo class
   * @return The instance of the SensorInfo class
   */
  public static SensorInfo getInstance()
  {
    if (instance == null)
      instance = new SensorInfo();
    return instance;
  }

  /**
   * Gets the value of the touch sensor
   * @return The value of the touch sensor
   */
  public boolean getTouch()
  {
    return bTouch;
  }

  /**
   * Sets the value of the touch sensor
   * @param touch The value of the touch sensor
   */
  public synchronized void setTouch(boolean touch)
  {
    bTouch = touch;
  }

  /**
   * Gets the value of the battery sensor 
   * @return The value of the battery sensor
   */
  public int getCharge()
  {
    return iCharge;
  }

  /**
   * Sets the value of the battery sensor
   * @param charge The value of the battery sensor
   */
  public synchronized void setCharge(int charge)
  {
    iCharge = charge;
  }

  /**
   * Get the value of the temperature sensor
   * @return The value of the temperature sensor
   */
  public int getDegrees()
  {
    return iDegrees;
  }

  /**
   * Set the value of the temperature sensor
   * @param iDegrees The value of the temperature sensor
   */
  public synchronized void setDegrees(int iDegrees)
  {
    this.iDegrees = iDegrees;
  }

  /**
   * Gets the direction of the vehicle
   * @return The direction of the vehicle
   */
  public int getDirection()
  {
    return iDegrees % Constants.DEGREES_PER_CIRCLE;
  }

  /**
   * Gets the distance travelled by the vehicle
   * @return The distance travelled by the vehicle
   */
  public double getDistance()
  {
    return dDistance;
  }

  /**
   * Sets the distance travelled by the vehicle
   * @param distance The distance travelled by the vehicle
   */
  public synchronized void setDistance(double distance)
  {
    dDistance = distance;
  }

  /**
   * Gets the speed of the left motor
   * @return The speedof the left motor
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
    iLeftMotorLevel = leftMotorLevel;
  }

  /**
   * Gets the values of the light sensor
   * @return The values of the light sensor
   */
  public Integer[] getLight()
  {
    return (Integer[]) iLight.toArray(new Integer[Constants.SIN_CURVE_HISTORY]);
  }

  /**
   * Sets the values of the light sensor
   * @param light The values of the light sensor
   */
  public synchronized void setLight(Integer light)
  {
    try
    {
      if (iLight.size() > Constants.SIN_CURVE_HISTORY)
        iLight.take();

      iLight.put(light);
    }
    catch(InterruptedException e )
    {
      Debug.print("Set temperature failed; interrupted");
    }
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
    iRightMotorLevel = rightMotorLevel;
  }

  /**
   * Gets the values of the sound sensor
   * @return The values of the sound sensor
   */
  public Integer[] getSound()
  {
    return (Integer[]) iSound.toArray(new Integer[Constants.SIN_CURVE_HISTORY]);
  }

  /**
   * Sets the speed of the vehicle
   * @param iNewSpeed The speed of the vehicle 
   */
  public synchronized void setSliderSpeed(int iNewSpeed)
  {
    iSliderSpeed = iNewSpeed;
  }

  /**
   * Gets the speed of the vehicle
   * @return The speed of the vehicle
   */
  public int getSliderSpeed()
  {
    return iSliderSpeed;
  }

  /**
   * Sets the direction of the vehicle
   * @param iNewDirection The direction of the vehicle
   */
  public synchronized void setSliderDirection(int iNewDirection)
  {
    iSliderDirection = iNewDirection;
  }

  /**
   * Gets the direction of the vehicle
   * @return The direction of the vehicle
   */
  public int getSliderDirection()
  {
    return iSliderDirection;
  }

  /**
   * Sets the value of the sound sensor
   * @param sound The value of the sound sensor 
   */
  public synchronized void setSound(int sound)
  {
    try
    {
      if (iSound.size() > Constants.SIN_CURVE_HISTORY)
        iSound.take();

      iSound.put(sound);
    }
    catch(InterruptedException e )
    {
      Debug.print("Set sound failed; interrupted");
    }
  }

  /**
   * Gets the value of the temperature sensor
   * @return The value of the temperature sensor
   */
  public Integer[] getTemperature()
  {
    return (Integer[]) iTemperature
        .toArray(new Integer[Constants.SIN_CURVE_HISTORY]);
  }

  /**
   * Sets the value of the temperature sensor
   * @param temperature The value of the temperature sensor
   */
  public synchronized void setTemperature(int temperature)
  {
    try
    {
      if (iTemperature.size() > Constants.SIN_CURVE_HISTORY)
        iTemperature.take();

      iTemperature.put(temperature);
    }
    catch(InterruptedException e )
    {
      Debug.print("Set temperature failed; interrupted");
    }
  }

  /**
   * Gets the X position of the vehicle
   * @return The X position of the vehicle
   */
  public Double[] getXPos()
  {
    return (Double[]) qXPos.toArray(new Double[Constants.SIN_CURVE_HISTORY]);
  }

  /**
   * Gets the immediate X position of the vehicle
   * @return The immediate X position of the vehicle
   */
  public double getCurrentXPos()
  {
    return dCurrentXPos;
  }

  /**
   * Gets the immediate Y position of the vehicle
   * @return The immediate Y position of the vehicle
   */
  public double getCurrentYPos()
  {
    return dCurrentYPos;
  }

  /**
   *  Adds an element the to tail of the list of X positions.  If the queue
   *  is larger than 50 elements (a.k.a the chosen number of positions to 
   *  display), then remove the head of the queue.]
   *  @param pos
   */
  public synchronized void setXPos(double pos)
  {
    try
    {
      if (qXPos.size() > Constants.SIN_CURVE_HISTORY)
        qXPos.take();

      dCurrentXPos = pos;
      qXPos.put(pos);
    }
    catch(InterruptedException e )
    {
      Debug.print("Set X failed; interrupted");
    }
  }

  /**
   * Gets the Y position of the vehicle
   * @return The Y position of the vehicle
   */
  public Double[] getYPos()
  {
    return (Double[]) qYPos.toArray(new Double[Constants.SIN_CURVE_HISTORY]);
  }

  /**
   *  Adds an element the to tail of the list of Y positions.  If the queue
   *  is larger than 50 elements (a.k.a the chosen number of positions to 
   *  display), then remove the head of the queue.
   * @param pos
   */
  public synchronized void setYPos(double pos)
  {
    try
    {
      if (qYPos.size() > Constants.SIN_CURVE_HISTORY)
        qYPos.take();

      dCurrentYPos = pos;
      qYPos.put(pos);
    }
    catch(InterruptedException e )
    {
      Debug.print("Set Y failed; interrupted");
    }
  }
}
