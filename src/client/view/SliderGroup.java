package client.view;

import javax.swing.*;
import client.controller.*;
import java.util.*;

/*****************************************************************************
 *  File:       SliderGroup.java
 *  Authors:    Sean Jagat and Nick Knowlson
 *  Assignment: 4
 *  Class:      CPS235
 *  Instructor: Frank Niscak
 *  Due Date:   November 23, 2007
 *
 *  Purpose:    The SliderGroup class contains all of the JSliders used in the
 *              gui
 ****************************************************************************/

public class SliderGroup
{
  private static JSlider directionSlider = null;
  private static JSlider speedSlider = null;

  /**
   * Gets the instance of the DirectionSlider
   * @return The instance of the DirectionSlider
   */
  public static JSlider getDirectionSlider()
  {
    if (directionSlider == null)
    {
      directionSlider = new JSlider();
      directionSlider.setMinimum(0);
      directionSlider.setMaximum(100);
      directionSlider.setValue(50);
      Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
      labelTable.put(0, new JLabel("Left Motor"));
      labelTable.put(50, new JLabel("Equal"));
      labelTable.put(100, new JLabel("Right Motor"));
      directionSlider.setLabelTable(labelTable);
      directionSlider.setPaintLabels(true);
      directionSlider.addChangeListener(DirectionSliderListener.getInstance());
    }
    return directionSlider;
  }

  /**
   * Gets the instance of the SpeedSlider
   * @return the instance of the SpeedSlider
   */
  public static JSlider getSpeedSlider()
  {
    if (speedSlider == null)
    {
      speedSlider = new JSlider();
      speedSlider.setMinimum(-20);
      speedSlider.setMaximum(50);
      speedSlider.setValue(5);
      speedSlider.setMajorTickSpacing(10);
      speedSlider.setMinorTickSpacing(5);
      speedSlider.setPaintTicks(true);
      speedSlider.setPaintLabels(true);
      speedSlider.setOrientation(JSlider.VERTICAL);
      speedSlider.addChangeListener(SpeedSliderListener.getInstance());
    }
    return speedSlider;
  }

}
