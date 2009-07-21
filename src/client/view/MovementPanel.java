package client.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import util.*;

/*****************************************************************************
 *  File:       MovementPanel.java
 *  Authors:    Sean Jagat and Nick Knowlson
 *  Assignment: 4
 *  Class:      CPS235
 *  Instructor: Frank Niscak
 *  Due Date:   November 23, 2007
 *
 *  Purpose:    The MovementPanel class is a subpanel that contains the
 *              MovementControls panel and MovementDataPanel
 ****************************************************************************/


@SuppressWarnings("serial")
public class MovementPanel extends JPanel
{
  private static MovementPanel instance = null;

  //Private constructor to implement singleton design
  private MovementPanel()
  {
    setLayout(new BorderLayout(30,0));
    setBorder(BorderFactory
        .createTitledBorder(BorderFactory.createLineBorder(Color.black, 1),
                            "Movement Controls",
                            TitledBorder.DEFAULT_JUSTIFICATION,
                            TitledBorder.DEFAULT_POSITION,
                            Constants.DEFAULT_BORDER_FONT,
                            Constants.DEFAULT_BORDER_COLOR));
    add(MovementControlsPanel.getInstance(), BorderLayout.CENTER);
    add(MovementDataPanel.getInstance(), BorderLayout.EAST);
    
  }

  /**
   * Gets the instance of the MovementPanel class
   * @return The instance of the MovementPanel class
   */
  public static MovementPanel getInstance()
  {
    if (instance == null)
      instance = new MovementPanel();
    return instance;
  }

}
