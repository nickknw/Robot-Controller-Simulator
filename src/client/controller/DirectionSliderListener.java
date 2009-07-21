package client.controller;

import javax.swing.event.*;
import javax.swing.*;

/*****************************************************************************
 *  File:       DirectionSliderListener.java
 *  Authors:    Sean Jagat and Nick Knowlson
 *  Assignment: 4
 *  Class:      CPS235
 *  Instructor: Frank Niscak
 *  Due Date:   November 23, 2007
 *
 *  Purpose:    The DirectionSliderListener class is a listener for the
 *              DirectionSlider that sets the steering for the vehicle
 ****************************************************************************/


public class DirectionSliderListener implements ChangeListener
{
  private static DirectionSliderListener instance = null;

  //Private constructor to implement singleton design
  private DirectionSliderListener()
  {
  }

  /**
   * Gets the instance of the DirectionSliderListener class
   * @return The instance of the DirectionSliderListener class
   */
  public synchronized static DirectionSliderListener getInstance()
  {
    if (instance == null)
      instance = new DirectionSliderListener();
    return instance;
  }

  /**
   * Sets the direction of the vehicle
   */
  public void stateChanged(ChangeEvent event)
  {
    JSlider source = (JSlider) event.getSource();
    if (!source.getValueIsAdjusting())
    {
      SensorInfo.getInstance().setSliderDirection(source.getValue());
      Controller.getInstance().setMotors();
    }
  }
}
