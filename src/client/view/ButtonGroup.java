package client.view;

import javax.swing.*;
import java.awt.*;
import util.*;
import client.controller.*;

/*****************************************************************************
 *  File:       ButtonGroup.java
 *  Authors:    Sean Jagat and Nick Knowlson
 *  Assignment: 4
 *  Class:      CPS235
 *  Instructor: Frank Niscak
 *  Due Date:   November 23, 2007
 *
 *  Purpose:    The ButtonGroup class contains all of the JButtons used in the
 *              gui
 ****************************************************************************/


public class ButtonGroup
{
  private static JButton centerButton = null;
  private static JButton slowButton = null;
  private static JButton startButton = null;
  private static JButton stopButton = null;
  private static JButton resetButton = null;
  private static JButton connectButton = null;

  /**
   * Gets the instance of the CenterButton
   * @return The instance of the CenterButton
   */
  public static JButton getCenterButton()
  {
    if (centerButton == null)
    {
      centerButton = new JButton();
      centerButton.setText(Constants.CENTER);
      centerButton.setHorizontalAlignment(SwingConstants.CENTER);
      centerButton.setFont(new Font("Dialog", Font.BOLD, 14));
      centerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      centerButton.setActionCommand(Constants.CENTER);
      centerButton.addActionListener(ButtonListener.getInstance());
    }
    return centerButton;
  }

  /**
   * Gets the instance of the SlowButton
   * @return The instance of the SlowButton
   */
  public static JButton getSlowButton()
  {
    if (slowButton == null)
    {
      slowButton = new JButton();
      slowButton.setText(Constants.STOP);
      slowButton.setHorizontalAlignment(SwingConstants.CENTER);
      slowButton.setFont(Constants.DEFAULT_BUTTON_FONT);
      slowButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      slowButton.setActionCommand(Constants.SLOW);
      slowButton.addActionListener(ButtonListener.getInstance());
    }
    return slowButton;
  }

  /**
   * Gets the instance of the StartButton
   * @return The instance of the STartButton
   */
  public static JButton getStartButton()
  {
    if (startButton == null)
    {
      startButton = new JButton();
      startButton.setText(Constants.START);
      startButton.setHorizontalAlignment(SwingConstants.CENTER);
      startButton.setFont(Constants.DEFAULT_BUTTON_FONT);
      startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      startButton.setActionCommand(Constants.START);
      startButton.addActionListener(ButtonListener.getInstance());
      startButton.setEnabled(false);
    }
    return startButton;
  }

  /**
   * Gets the instance of the StopButton
   * @return The instance of the StopButton
   */
  public static JButton getStopButton()
  {
    if (stopButton == null)
    {
      stopButton = new JButton();
      stopButton.setText(Constants.STOP);
      stopButton.setHorizontalAlignment(SwingConstants.CENTER);
      stopButton.setFont(Constants.DEFAULT_BUTTON_FONT);
      stopButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      stopButton.setActionCommand(Constants.STOP);
      stopButton.addActionListener(ButtonListener.getInstance());
      stopButton.setEnabled(false);
    }
    return stopButton;
  }

  /**
   * Gets the instance of the ResetButton
   * @return The instance of the ResetButton
   */
  public static JButton getResetButton()
  {
    if (resetButton == null)
    {
      resetButton = new JButton();
      resetButton.setText(Constants.RESET);
      resetButton.setHorizontalAlignment(SwingConstants.CENTER);
      resetButton.setFont(Constants.DEFAULT_BUTTON_FONT);
      resetButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      resetButton.setActionCommand(Constants.RESET);
      resetButton.addActionListener(ButtonListener.getInstance());
      resetButton.setEnabled(false);
    }
    return resetButton;
  }
  
  /**
   * Gets the instance of the ConnectButton
   * @return The instance of the ConnectButton
   */
  public static JButton getConnectButton()
  {
    if(connectButton == null)
    {
      connectButton = new JButton();
      connectButton.setText(Constants.CONNECT);
      connectButton.setHorizontalAlignment(SwingConstants.CENTER);
      connectButton.setFont(Constants.DEFAULT_BUTTON_FONT);
      connectButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      connectButton.setActionCommand(Constants.CONNECT);
      connectButton.addActionListener(ButtonListener.getInstance());
    }
    return connectButton;
  }
}
