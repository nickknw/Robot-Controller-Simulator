package vehicle.model;

import util.*;
import vehicle.controller.*;

/*****************************************************************************
 *  File:       SoundSensor.java
 *  Authors:    Sean Jagat and Nick Knowlson
 *  Assignment: 4
 *  Class:      CPS235
 *  Instructor: Frank Niscak
 *  Due Date:   November 23, 2007
 *
 *  Purpose:    The SoundSensor class simulates a sensor on the vehicle and
 *              Manages updating itself to the client
 ****************************************************************************/

public class SoundSensor implements Runnable
{
  private static SoundSensor instance = null;
  private int iNoise;
  private int iTime;

  //Private constructor to implement singleton design
  private SoundSensor()
  {
    iNoise = Constants.DEFAULT_SOUND;
    iTime = Constants.ZERO;
  }

  /**
   * Gets the instance of the SoundSensor class
   * @return The instance of the SoundSensor class
   */
  public static SoundSensor getInstance()
  {
    if (instance == null)
      instance = new SoundSensor();
    return instance;
  }

  
  /**
   * Updates the SoundSensor value and updates the client
   */
  public void run()
  {
    while (BatterySensor.getInstance().getBatteryValue() > 0
           && Controller.getInstance().isAlive())
    {
      while (!Controller.getInstance().isRunning())
        sleep();
      sleep();
      iNoise = (int) (35 * Math.sin(Math.toRadians((iTime += 5))) + 35 + (Math
          .random() * 11));
      update();
    }
  }

  /**
   * Resets the sensor to its original value
   */
  public synchronized void reset()
  {
    iNoise = Constants.DEFAULT_SOUND;
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
      Debug.print("Sound sensor interrupted");
    }
  }

  /*
   * Writes the updated value to the client
   */
  private void update()
  {
    Controller.getInstance().write(Constants.SOUND + Constants.UPDATE + iNoise);
  }

  /**
   * Gets the value of the SoundSensor
   * @return The value of the SoundSensor
   */
  public int getSound()
  {
    return iNoise;
  }
}
