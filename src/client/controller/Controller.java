package client.controller;

import util.*;
import client.view.*;


/*****************************************************************************
 *  File:       Controller.java
 *  Authors:    Sean Jagat and Nick Knowlson
 *  Assignment: 4
 *  Class:      CPS235
 *  Instructor: Frank Niscak
 *  Due Date:   November 23, 2007
 *
 *  Purpose:    The Controller class is a general purpose class used to
 *              manipulate the client
 ****************************************************************************/

public class Controller
{
  private static Controller instance = null;
  private Communication communication = null;
  private String sHost;

  //Private constructor to implement Singleton design
  private Controller()
  {

  }

  /**
   * Gets the instance of the Controller class
   * @return The instance of the Controller class
   */
  public static Controller getInstance()
  {
    if (instance == null)
      instance = new Controller();
    return instance;
  }

  /**
   * Connects to the last connected to host
   *
   */
  public void connect()
  {
    communication = new Communication(sHost);
    new Thread(communication).start();
  }

  /**
   * Connects to a new host
   * @param sHost
   */
  public void connect(String sHost)
  {
    this.sHost = sHost;
    communication = new Communication(sHost);
    new Thread(communication).start();
  }
  
  /**
   * Disconnects from the host
   */
  public void disconnect()
  {
    communication.finalize();
  }

  /**
   * Returns the host last connected to
   * @return The name of the host last connected to
   */
  public String getHost()
  {
    return sHost;
  }

  /**
   * Appends a message to the log text area
   * @param sMessage The messages to append to the log text area
   */
  public void print(String sMessage)
  {
    LogTextArea.getInstance().append(sMessage + "\n");
  }

  /**
   * Writes a message over the communication socket
   * @param sMessage The message to write over the communication socket
   */
  public synchronized void write(String sMessage)
  {
    communication.write(sMessage);
  }

  /**
   * Writes to the vehicle server what to set the motor levels to
   *
   */
  public synchronized void setMotors()
  {

    double dRightPercentage = SensorInfo.getInstance().getSliderDirection()
                              / Constants.PERCENT;
    double dLeftPercentage = 1 - dRightPercentage;
    int iSpeed = SensorInfo.getInstance().getSliderSpeed();


    int iRightMotorLevel = (int) (iSpeed * dRightPercentage);

    int iLeftMotorLevel = (int) (iSpeed * dLeftPercentage);


    Controller.getInstance().write(
                                   Constants.MOVEMENT_MANAGER
                                       + Constants.COMMAND
                                       + Constants.SET_RIGHT_MOTOR
                                       + iRightMotorLevel);

    Controller.getInstance().write(
                                   Constants.MOVEMENT_MANAGER
                                       + Constants.COMMAND
                                       + Constants.SET_LEFT_MOTOR
                                       + iLeftMotorLevel);
  }

}
