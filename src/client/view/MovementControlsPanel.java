package client.view;

import java.awt.*;
import javax.swing.*;


/*****************************************************************************
 *  File:       MovementControlsPanel.java
 *  Authors:    Sean Jagat and Nick Knowlson
 *  Assignment: 4
 *  Class:      CPS235
 *  Instructor: Frank Niscak
 *  Due Date:   November 23, 2007
 *
 *  Purpose:    The MovementControlsPanel class contains all of the components
 *              related to movement control
 ****************************************************************************/


@SuppressWarnings("serial")
public class MovementControlsPanel extends JPanel
{
  private static MovementControlsPanel instance = null;

  //Private constructor to implement singleton design
  private MovementControlsPanel()
  {
    setLayout(new BorderLayout());
    add(SliderGroup.getDirectionSlider(), BorderLayout.NORTH);
    add(SliderGroup.getSpeedSlider(), BorderLayout.EAST);
    add(MovementGridPanel.getInstance(), BorderLayout.CENTER);
  }

  /**
   * Gets the instance of the MovementControlsPanel
   * @return The instance of the MovementControlsPanel
   */
  public static MovementControlsPanel getInstance()
  {
    if (instance == null)
      instance = new MovementControlsPanel();
    return instance;
  }
}
