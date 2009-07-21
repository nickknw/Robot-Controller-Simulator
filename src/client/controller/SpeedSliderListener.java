package client.controller;

import javax.swing.event.*;
import javax.swing.*;

/*****************************************************************************
 *  File:       SpeedSliderListener.java
 *  Authors:    Sean Jagat and Nick Knowlson
 *  Assignment: 4
 *  Class:      CPS235
 *  Instructor: Frank Niscak
 *  Due Date:   November 23, 2007
 *
 *  Purpose:    The SpeedSliderListener class is a listener for the
 *              SpeedSlider that sets the steering for the vehicle
 ****************************************************************************/


public class SpeedSliderListener implements ChangeListener
{
  private static SpeedSliderListener instance = null;

//Private constructor to implement singleton design
  private SpeedSliderListener()
  {
  }

  /**
   * Gets the instance of the SpeedSliderListener class
   * @return The instance of the SpeedSliderListener class
   */
  public synchronized static SpeedSliderListener getInstance()
  {
    if (instance == null)
      instance = new SpeedSliderListener();
    return instance;
  }

  /**
   * Sets the speed of the vehicle
   */
  public void stateChanged(ChangeEvent event)
  {
    JSlider source = (JSlider) event.getSource();
    if (!source.getValueIsAdjusting())
    {
      SensorInfo.getInstance().setSliderSpeed(source.getValue());
      Controller.getInstance().setMotors();
    }
  }
}
