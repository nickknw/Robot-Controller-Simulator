package vehicle.model;

import util.*;
import vehicle.controller.*;


/*****************************************************************************
 *  File:       BatterySensor.java
 *  Authors:    Sean Jagat and Nick Knowlson
 *  Assignment: 4
 *  Class:      CPS235
 *  Instructor: Frank Niscak
 *  Due Date:   November 23, 2007
 *
 *  Purpose:    The BatterySensor class simulates a sensor on the vehicle and
 *              manages updating itself to the client
 ****************************************************************************/

public class BatterySensor implements Runnable
{
  private static BatterySensor instance = null;
  private final static int POWER_DRAIN = 50;
  private int iMilliVolts;

  //Private constructor to implement singleton design
  private BatterySensor()
  {
    iMilliVolts = Constants.MAX_BATTERY;
  }

  /**
   * Gets the instance of the BatterySensor class
   * @return The instance of the BatterySensor class
   */
  public static BatterySensor getInstance()
  {
    if (instance == null)
      instance = new BatterySensor();
    return instance;
  }

  /**
   * Drains the battery and calls for updates
   */
  public void run()
  {
    while (iMilliVolts > 0 && Controller.getInstance().isAlive())
    {
      while (!Controller.getInstance().isRunning())
        sleep();
      sleep();

      iMilliVolts -= POWER_DRAIN;

      if (iMilliVolts <= Constants.LOW_BATTERY)
        error("Battery low");
      update();

    }
    Controller.getInstance().write(
                                   Constants.VEHICLE + Constants.COMMAND
                                       + Constants.STOP);
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
      Debug.print("Battery Sensor thread interrupted.");
    }
  }

  /*
   * Writes an error message to the client
   */
  private void error(String sError)
  {
    Controller.getInstance()
        .write(Constants.BATTERY + Constants.ERROR + sError);
  }

  /*
   * Writes the updated value to the client
   */
  private void update()
  {
    Controller.getInstance().write(
                                   Constants.BATTERY + Constants.UPDATE
                                       + iMilliVolts);
  }

  /**
   * Resets the sensor to its original value
   */
  public void reset()
  {
    iMilliVolts = Constants.MAX_BATTERY;
    update();
  }

  /**
   * Get the BatterySensor's value
   * @return The BatterySensor's value
   */
  public int getBatteryValue()
  {
    return iMilliVolts;
  }

  /**
   * Sets the BatterySensor's value
   * @param volts The BatterySensor's value
   */
  public synchronized void setBatteryValue(int volts)
  {
    iMilliVolts = volts;
  }

}
