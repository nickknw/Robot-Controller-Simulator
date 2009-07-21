package client.controller;

import java.awt.event.*;
import client.view.*;
import util.*;


/*****************************************************************************
 *  File:       ButtonListener.java
 *  Authors:    Sean Jagat and Nick Knowlson
 *  Assignment: 4
 *  Class:      CPS235
 *  Instructor: Frank Niscak
 *  Due Date:   November 23, 2007
 *
 *  Purpose:    The ButtonListener class contains all of the listener logic
 *              for all of the buttons.
 ****************************************************************************/

public class ButtonListener implements ActionListener
{
  private static ButtonListener buttonListener = null;

  //private constructor to implement singleton
  private ButtonListener()
  {
  }

  /**
   * Gets the instance of the ButtonListener
   * @return The instance of the ButtonListener
   */
  public synchronized static ButtonListener getInstance()
  {
    if (buttonListener == null)
      buttonListener = new ButtonListener();
    return buttonListener;
  }

  /**
   * Sends a message to the vehicle to center the steering, stop moving, or
   * start/stop/reset the simulation
   */
  public void actionPerformed(ActionEvent event)
  {
    if (event.getActionCommand().equals(Constants.START))
    {
      Controller.getInstance().write(
                                     Constants.VEHICLE + Constants.COMMAND
                                         + Constants.START);
      Controller.getInstance().setMotors();
    }
    else if (event.getActionCommand().equals(Constants.STOP))
    {
      Controller.getInstance().write(
                                     Constants.VEHICLE + Constants.COMMAND
                                         + Constants.STOP);
    }
    else if (event.getActionCommand().equals(Constants.RESET))
    {
      Controller.getInstance().write(
                                     Constants.VEHICLE + Constants.COMMAND
                                         + Constants.RESET);
    }
    else if (event.getActionCommand().equals(Constants.SLOW))
    {
      Controller.getInstance().write(
                                     Constants.VEHICLE + Constants.COMMAND
                                         + Constants.SLOW);
      SliderGroup.getSpeedSlider().setValue(Constants.ZERO);
    }
    else if (event.getActionCommand().equals(Constants.CENTER))
    {
      Controller.getInstance().write(
                                     Constants.VEHICLE + Constants.COMMAND
                                         + Constants.CENTER);
      SliderGroup.getDirectionSlider().setValue(Constants.DEFAULT_STEERING);
    }
    else if (event.getActionCommand().equals(Constants.CONNECT))
    {
      Controller.getInstance().connect(
                                       SimControlsPanel.getHostAddressField()
                                           .getText().trim());
      Controller.getInstance().write(
                                     Constants.VEHICLE + Constants.COMMAND
                                         + Constants.CONNECT);
    }
    else if (event.getActionCommand().equals(Constants.DISCONNECT))
    {
      /*
      else if (event.getActionCommand().equals(Constants.DISCONNECT))
      {
        Controller.getInstance().print("Button disconnect");
        Controller.getInstance().disconnect();
        SimControlsPanel.getHostAddressField().setEnabled(true);
        ButtonGroup.getStartButton().setEnabled(false);
        ButtonGroup.getStopButton().setEnabled(false);
        ButtonGroup.getResetButton().setEnabled(false);
        ButtonGroup.getConnectButton().setText(Constants.CONNECT);
        ButtonGroup.getConnectButton().setActionCommand(Constants.CONNECT);
      }
      */
    }
    else
    {
      Controller.getInstance().print("ButtonListener Error");
    }
  }
}
