package vehicle.model;

import util.*;
import vehicle.controller.*;

/*****************************************************************************
 *  File:       LightSensor.java
 *  Authors:    Sean Jagat and Nick Knowlson
 *  Assignment: 4
 *  Class:      CPS235
 *  Instructor: Frank Niscak
 *  Due Date:   November 23, 2007
 *
 *  Purpose:    The LightSensor class simulates a sensor on the vehicle and
 *              Manages updating itself to the client
 ****************************************************************************/

public class LightSensor implements Runnable
{
  private static LightSensor instance = null;
  private int iBrightness;
  private int iTime;

  //Private constructor to implement singleton design
  private LightSensor()
  {
    iBrightness = Constants.DEFAULT_BRIGHTNESS;
    iTime = Constants.ZERO;
  }

  /**
   * Gets the instance of the LightSensor class
   * @return The instance of the LightSensor class
   */
  public static LightSensor getInstance()
  {
    if (instance == null)
      instance = new LightSensor();
    return instance;
  }

  /**
   * Updates the LightSensor value and updates the client
   */
  public void run()
  {
    while (BatterySensor.getInstance().getBatteryValue() > 0
           && Controller.getInstance().isAlive())
    {
      while (!Controller.getInstance().isRunning())
        sleep();
      sleep();
      //from 0 to 100
      iBrightness = (int) (45 * Math.sin(Math.toRadians((iTime += 5))) + 45 + (Math
          .random() * 11));
      update();
    }
  }
  
  /**
   * Resets the sensor to its original value
   */
  public synchronized void reset()
  {
    iBrightness = Constants.DEFAULT_BRIGHTNESS;
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
      Debug.print("Light sensor interrupted");
    }
  }

  /*
   * Writes the updated value to the client
   */
  private void update()
  {
    Controller.getInstance().write(
                                   Constants.LIGHT + Constants.UPDATE
                                       + iBrightness);
  }

  /**
   * Gets the value of the LightSensor
   * @return The value of the LightSensor
   */
  public int getLight()
  {
    return iBrightness;
  }
}
