package vehicle.controller;

import vehicle.model.*;

/*****************************************************************************
 *  File:       Controller.java
 *  Authors:    Sean Jagat and Nick Knowlson
 *  Assignment: 4
 *  Class:      CPS235
 *  Instructor: Frank Niscak
 *  Due Date:   November 23, 2007
 *
 *  Purpose:    The ButtonListener class contains all of the listener logic
 *              for all of the buttons.
 ****************************************************************************/

public class Controller
{
  private static Controller instance = null;
  private Communication communication = null;
  private static boolean bAlive, bRunning;

  //Private constructor to implement singleton design
  private Controller()
  {
    bAlive = false;
    bRunning = false;
    reconnect();
  }

  /**
   * Creates a new connection in which to listen on
   */
  public void reconnect()
  {
    communication = new Communication();
    new Thread(communication).start();
  }

  /**
   * Starts a new instance of all of the sensor threads
   */
  public void startThreads()
  {
    new Thread(BatterySensor.getInstance()).start();
    new Thread(LightSensor.getInstance()).start();
    new Thread(MovementManagerSensor.getInstance()).start();
    new Thread(TemperatureSensor.getInstance()).start();
    new Thread(TouchSensor.getInstance()).start();
    new Thread(SoundSensor.getInstance()).start();
  }

  /**
   * Gets the instance of the Controller class
   * @return The instance of the Controller class
   */
  public static synchronized Controller getInstance()
  {
    if (instance == null)
      instance = new Controller();
    return instance;
  }

  /**
   * Resets the sensors
   */
  public synchronized void reset()
  {
    BatterySensor.getInstance().reset();
    LightSensor.getInstance().reset();
    MovementManagerSensor.getInstance().reset();
    SoundSensor.getInstance().reset();
    TemperatureSensor.getInstance().reset();
    TouchSensor.getInstance().reset();
  }

  /**
   * Writes a message to the client
   * @param sMessage The message to write to the client
   */
  public synchronized void write(String sMessage)
  {
    if (communication != null)
      communication.write(sMessage);
  }

  /**
   * Sets whether or not the vehicle is running
   * @param bRunningNew Whether or not the vehicle is running
   */
  public synchronized void setRunning(boolean bRunningNew)
  {
    bRunning = bRunningNew;
  }

  /**
   * Sets whether or not the vehicle is alive
   * @param bAliveNew Whether or not the vehicle is alive
   */
  public synchronized void setAlive(boolean bAliveNew)
  {
    bAlive = bAliveNew;
  }

  /**
   * Gets whether or not the vehicle is running
   * @return Whether or not the vehicle is running
   */
  public boolean isRunning()
  {
    return bRunning;
  }

  /**
   * Gets whether or not the vehicle is alive
   * @return Whether or not the vehicel is alive
   */
  public boolean isAlive()
  {
    return bAlive;
  }
}
