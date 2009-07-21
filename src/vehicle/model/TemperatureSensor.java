package vehicle.model;

import util.*;
import util.*;
import vehicle.controller.*;

/*****************************************************************************
 *  File:       TemperatureSensor.java
 *  Authors:    Sean Jagat and Nick Knowlson
 *  Assignment: 4
 *  Class:      CPS235
 *  Instructor: Frank Niscak
 *  Due Date:   November 23, 2007
 *
 *  Purpose:    The TemperatureSensor class simulates a sensor on the vehicle and
 *              manages updating itself to the client
 ****************************************************************************/

public class TemperatureSensor implements Runnable
{
  private static TemperatureSensor instance = null;
  private int iTemperature;
  private int iTime;

  //Private constructor to implement singleton design
  private TemperatureSensor()
  {
    iTemperature = Constants.DEFAULT_TEMPERATURE;
    iTime = Constants.ZERO;
  }

  /**
   * Gets an instance
   * @return
   */
  public static TemperatureSensor getInstance()
  {
    if (instance == null)
      instance = new TemperatureSensor();
    return instance;
  }

  /**
   * Updates the TemperatureSensor value and sends the value to the client
   */
  public void run()
  {
    while (BatterySensor.getInstance().getBatteryValue() > 0
           && Controller.getInstance().isAlive())
    {
      while (!Controller.getInstance().isRunning())
        sleep();
      sleep();
      iTemperature = (int) (20 * Math.sin(Math.toRadians((iTime += 5))) + 15 + (Math
          .random() * 11));
      if (iTemperature < 0)
        error("Too cold:  " + iTemperature + " degrees.");
      else if (iTemperature > 40)
        error("Too hot:  " + iTemperature + " degrees.");

      update();
    }
  }

  /**
   * Resets the sensor to its original value
   */
  public synchronized void reset()
  {
    iTemperature = Constants.DEFAULT_TEMPERATURE;
    iTime = Constants.ZERO;
    update();
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
      Debug.print("Temperature sensor interrupted");
    }
  }

  /*
   * Writes an error message to the client
   */
  private void error(String sError)
  {
    Controller.getInstance().write(
                                   Constants.TEMPERATURE + Constants.ERROR
                                       + sError);
  }

  /*
   * Writes the updated value to the client
   */
  private void update()
  {
    Controller.getInstance().write(
                                   Constants.TEMPERATURE + Constants.UPDATE
                                       + iTemperature);
  }

  /**
   * Gets the value of the TemperatureSensor
   * @return The value of the TemperatureSensor
   */
  public int getTemperature()
  {
    return iTemperature;
  }
}
