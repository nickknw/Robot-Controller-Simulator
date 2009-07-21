package vehicle.model;

import util.*;
import java.util.*;
import vehicle.controller.*;

/*****************************************************************************
 *  File:       MessageInterpreter.java
 *  Authors:    Sean Jagat and Nick Knowlson
 *  Assignment: 4
 *  Class:      CPS235
 *  Instructor: Frank Niscak
 *  Due Date:   November 23, 2007
 *
 *  Purpose:    The MessageInterpreter class interprets a string and calls
 *              the appropriate methods based on the message.  It allows for
 *              the server to interact with the client (recieves information)
 ****************************************************************************/

public class MessageInterpreter implements Runnable
{
  String sMessage = null;
  String sDevice = null, sMessageType = null, sValue = null;

  //Private constructor to prevent creation without a message
  @SuppressWarnings("unused")
  private MessageInterpreter()
  {
  }

  /**
   * Creates a new instance of the message interpreter class
   * @param sMessage The message to be interpreted
   */
  public MessageInterpreter(String sMessage)
  {
    this.sMessage = sMessage;
  }

  /**
   * Separates the parts of the message and calls to interpret 
   */
  public void run()
  {
    Scanner scanner = new Scanner(sMessage);
    sDevice = scanner.next();
    sMessageType = scanner.next();
    sValue = scanner.nextLine();
    interpret(); //seperated for ease of reading
  }

  /**
   * Interprets the message and calls the appropriate action depending on the
   * message
   *
   */
  private void interpret()
  {
    if (Constants.VEHICLE.contains(sDevice))
    {
      if (Constants.COMMAND.contains(sMessageType))
      {
        if (Constants.STOP.contains(sValue.trim()))
        {
          Controller.getInstance().setRunning(false);
          Controller.getInstance().setAlive(true);
          Controller.getInstance().write(
                                         Constants.VEHICLE + Constants.COMMAND
                                             + Constants.STOP);
          Debug.print("Stopped running");
        }
        else if (Constants.START.contains(sValue.trim()))
        {
          if (!Controller.getInstance().isAlive())
          {
            Controller.getInstance().setAlive(true);
            Controller.getInstance().setRunning(true);
            Controller.getInstance().startThreads();
          }
          Controller.getInstance().setRunning(true);
          Controller.getInstance().write(
                                         Constants.VEHICLE + Constants.COMMAND
                                             + Constants.START);
          Debug.print("Started running");
        }
        else if (Constants.RESET.contains(sValue.trim()))
        {
          Controller.getInstance().setRunning(false);
          Controller.getInstance().setAlive(false);
          Controller.getInstance().reset();
          Controller.getInstance().write(
                                         Constants.VEHICLE + Constants.COMMAND
                                             + Constants.RESET);
          Debug.print("Reset");
        }
        else if (Constants.CONNECT.contains(sValue.trim()))
        {
          Controller.getInstance().write(
                                         Constants.VEHICLE + Constants.COMMAND
                                             + Constants.CONNECT);
          Debug.print("Connection received.");
        }
      }
    }
    else if (Constants.BATTERY.contains(sDevice))
    {
      if (Constants.COMMAND.contains(sMessageType))
      {
        int iMilliVolts = Integer.parseInt(sValue.trim());
        BatterySensor.getInstance().setBatteryValue(iMilliVolts);
      }
    }
    else if (Constants.MOVEMENT_MANAGER.contains(sDevice))
    {
      if (Constants.COMMAND.contains(sMessageType))
      {
        if (sValue.contains(Constants.SET_LEFT_MOTOR))
        {
          MovementManagerSensor.getInstance()
              .setLeftMotorLevel(
                                 Integer.parseInt(sValue
                                     .substring(
                                                Constants.SET_LEFT_MOTOR
                                                    .length()).trim()));
        }
        else if (sValue.contains(Constants.SET_RIGHT_MOTOR))
        {
          MovementManagerSensor.getInstance()
              .setRightMotorLevel(
                                  Integer.parseInt(sValue
                                      .substring(
                                                 Constants.SET_RIGHT_MOTOR
                                                     .length()).trim()));
        }
      }
    }
    else
      Controller.getInstance().write(
                                     "General " + Constants.ERROR
                                         + " Cannot find device!");
  }
}
