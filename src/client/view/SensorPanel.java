package client.view;

import java.awt.Dimension;

import java.awt.GridLayout;


import javax.swing.*;

/*****************************************************************************
 *  File:       SensorPanel.java
 *  Authors:    Sean Jagat and Nick Knowlson
 *  Assignment: 4
 *  Class:      CPS235
 *  Instructor: Frank Niscak
 *  Due Date:   November 23, 2007
 *
 *  Purpose:    The SensorPanel class contains all of the sensors used in the
 *              gui
 ****************************************************************************/


@SuppressWarnings("serial")
public class SensorPanel extends JPanel
{
  private static SensorPanel instance = null;

  //Private constructor to implement singleton design
  private SensorPanel()
  {
    GridLayout gridLayout = new GridLayout();
    gridLayout.setRows(1);
    gridLayout.setVgap(1);
    gridLayout.setHgap(1);
    setPreferredSize(new Dimension(0, 150));
    setLayout(gridLayout);
    add(BatterySensorPanel.getInstance(), null);
    add(LightSensorPanel.getInstance(), null);
    add(SoundSensorPanel.getInstance(), null);
    add(TemperatureSensorPanel.getInstance(), null);
    add(TouchSensorPanel.getInstance(), null);
  }

  /**
   * Gets the instance of the SensorPanel
   * @return The instance of the SensorPanel
   */
  public static SensorPanel getInstance()
  {
    if (instance == null)
      instance = new SensorPanel();
    return instance;
  }
}
