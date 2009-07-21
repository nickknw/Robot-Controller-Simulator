package vehicle.model;

import util.*;
import util.*;
import vehicle.controller.*;

/*****************************************************************************
 *  File:       TouchSensor.java
 *  Authors:    Sean Jagat and Nick Knowlson
 *  Assignment: 4
 *  Class:      CPS235
 *  Instructor: Frank Niscak
 *  Due Date:   November 23, 2007
 *
 *  Purpose:    The TouchSensor class simulates a sensor on the vehicle and
 *              Manages updating itself to the client
 ****************************************************************************/

public class TouchSensor implements Runnable
{
  private static TouchSensor instance = null;
  private boolean bTouching;

  //Private constructor to implement singleton design
  private TouchSensor()
  {
    bTouching = false;
  }

  /**
   * Gets the instance of the TouchSensor class
   * @return The instance of the TouchSensor class
   */
  public static TouchSensor getInstance()
  {
    if (instance == null)
      instance = new TouchSensor();
    return instance;
  }

  /**
   * Updates the touchsensor vallue and updates the client
   */
  public void run()
  {
    while (BatterySensor.getInstance().getBatteryValue() > 0
           && Controller.getInstance().isAlive())
    {
      while (!Controller.getInstance().isRunning())
        sleep();
      sleep();
      boolean bChanged = bTouching;
      if (((int) (Math.random() * 100)) < 10)
      {
        bTouching = true;
        error("Ran into something!");
      }
      else
      {
        bTouching = false;
      }
      if (bChanged != bTouching)
        update();
    }
  }
  
  /**
   * Resets the sensor to its original value
   */
  public synchronized void reset()
  {
    bTouching = false;
    update();
  }

  /*
   * A convenient sleeping method
   */
  private void sleep()
  {
    try
    {
      Thread.sleep(Constants.TIME_TO_SLEEP*2);
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
    Controller.getInstance().write(Constants.TOUCH + Constants.ERROR + sError);
  }

  /*
   * Writes the updated value to the client
   */
  private void update()
  {
    Controller.getInstance().write(
                                   Constants.TOUCH + Constants.UPDATE
                                       + bTouching);
  }

  /**
   * Returns the value of the TouchSensor
   * @return The value of the TouchSensor
   */
  public boolean isTouching()
  {
    return bTouching;
  }
}
