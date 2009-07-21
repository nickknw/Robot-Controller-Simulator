package client.model;

import java.util.Scanner;
import util.*;
import client.controller.*;
import client.view.*;


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
 *              the client to interact with the server (recieves information)
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
    //We have to use slightly odd looking equivalence testing.  Consider
    //Constant.contains(string) to effectively mean string.equals(Constant)

    //if it is an error, catch it first and print it immediately
    if (Constants.ERROR.contains(sMessageType))
    {
      Controller.getInstance().print((sValue));
    }
    if (Constants.VEHICLE.contains(sDevice.trim()))
    {
      if (Constants.COMMAND.contains(sMessageType.trim()))
      {
        if (Constants.STOP.contains(sValue.trim()))
        {
          ButtonGroup.getStartButton().setEnabled(true);
          ButtonGroup.getStopButton().setEnabled(false);
          ButtonGroup.getResetButton().setEnabled(true);
        }
        else if (Constants.START.contains(sValue.trim()))
        {
          ButtonGroup.getStartButton().setEnabled(false);
          ButtonGroup.getStopButton().setEnabled(true);
          ButtonGroup.getResetButton().setEnabled(false);
        }
        else if (Constants.RESET.contains(sValue.trim()))
        {
          SensorInfo.getInstance().reset();
          ButtonGroup.getStartButton().setEnabled(true);
          ButtonGroup.getStopButton().setEnabled(false);
          ButtonGroup.getResetButton().setEnabled(false);
          SliderGroup.getSpeedSlider().setValue(Constants.DEFAULT_SPEED);
          SliderGroup.getDirectionSlider().setValue(Constants.DEFAULT_STEERING);
        }
        else if (Constants.CONNECT.contains(sValue.trim()))
        {
          SimControlsPanel.getHostAddressField().setEnabled(false);
          ButtonGroup.getStartButton().setEnabled(true);
          ButtonGroup.getStopButton().setEnabled(false);
          ButtonGroup.getResetButton().setEnabled(false);
          ButtonGroup.getConnectButton().setText("Connected");
          ButtonGroup.getConnectButton().setEnabled(false);
          //ButtonGroup.getConnectButton().setText(Constants.DISCONNECT);
        }
        /*
        else if (Constants.DISCONNECT.contains(sValue.trim()))
        {
          Controller.getInstance().print("This doesn't do anything");
        }
        */
        else
        {
          Controller.getInstance().print("Message Interpreter error");
        }
      }
    }
    else if (Constants.BATTERY.contains(sDevice))
    {
      if (Constants.UPDATE.contains(sMessageType))
      {
        SensorInfo.getInstance().setCharge(Integer.parseInt(sValue.trim()));
      }
    }
    else if (Constants.LIGHT.contains(sDevice))
    {
      if (Constants.UPDATE.contains(sMessageType))
      {
        SensorInfo.getInstance().setLight(Integer.parseInt(sValue.trim()));
      }
    }
    else if (Constants.MOVEMENT_MANAGER.contains(sDevice))
    {
      if (Constants.UPDATE.contains(sMessageType))
      {
        Scanner scanner = new Scanner(sValue.trim());

        SensorInfo.getInstance().setLeftMotorLevel(
                                                   Integer.parseInt(scanner
                                                       .next()));
        SensorInfo.getInstance().setRightMotorLevel(
                                                    Integer.parseInt(scanner
                                                        .next()));
        SensorInfo.getInstance().setDegrees(Integer.parseInt(scanner.next()));
        SensorInfo.getInstance().setXPos(Double.parseDouble(scanner.next()));
        SensorInfo.getInstance().setYPos(Double.parseDouble(scanner.next()));
        SensorInfo.getInstance()
            .setDistance(Double.parseDouble(scanner.next()));
      }
    }
    else if (Constants.SOUND.contains(sDevice))
    {
      if (Constants.UPDATE.contains(sMessageType))
      {
        SensorInfo.getInstance().setSound(Integer.parseInt(sValue.trim()));
      }
    }
    else if (Constants.TEMPERATURE.contains(sDevice))
    {
      if (Constants.UPDATE.contains(sMessageType))
      {
        SensorInfo.getInstance()
            .setTemperature(Integer.parseInt(sValue.trim()));
      }
    }
    else if (Constants.TOUCH.contains(sDevice))
    {
      if (Constants.UPDATE.contains(sMessageType))
      {
        SensorInfo.getInstance().setTouch(Boolean.parseBoolean(sValue.trim()));
      }
    }
    else
      Controller.getInstance().print(sMessage);

    ClientFrame.getInstance().repaint();
  }
}
