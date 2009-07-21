package client.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import util.*;

/*****************************************************************************
 *  File:       SimControlsPanel.java
 *  Authors:    Sean Jagat and Nick Knowlson
 *  Assignment: 4
 *  Class:      CPS235
 *  Instructor: Frank Niscak
 *  Due Date:   November 23, 2007
 *
 *  Purpose:    The SimControlsPanel contains all of the components related to
 *              simulation controls
 ****************************************************************************/


@SuppressWarnings("serial")
public class SimControlsPanel extends JPanel
{
  private static SimControlsPanel simControlsPanel = null;
  private static JTextField hostAddressField = null;

  //Private constructor to implement singleton design
  private SimControlsPanel()
  {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    setBorder(BorderFactory
        .createTitledBorder(BorderFactory.createLineBorder(Color.black, 1),
                            "Simulation Controls",
                            TitledBorder.DEFAULT_JUSTIFICATION,
                            TitledBorder.DEFAULT_POSITION,
                            Constants.DEFAULT_BORDER_FONT,
                            Constants.DEFAULT_BORDER_COLOR));
    setPreferredSize(new Dimension(150, 26));
    add(Box.createVerticalGlue());
    add(Box.createVerticalGlue());
    add(ButtonGroup.getStartButton(), null);
    add(Box.createVerticalGlue());
    add(Box.createVerticalGlue());
    add(ButtonGroup.getStopButton(), null);
    add(Box.createVerticalGlue());
    add(Box.createVerticalGlue());
    add(ButtonGroup.getResetButton(), null);
    add(Box.createVerticalGlue());
    add(Box.createVerticalGlue());
    add(getHostAddressField());
    add(Box.createVerticalGlue());
    add(ButtonGroup.getConnectButton());
    add(Box.createVerticalGlue());   
    add(Box.createVerticalGlue());
  }

  /**
   * Gets the instance of the SimControlsPanel
   * @return The instance of the SimControlsPanel
   */
  public static SimControlsPanel getInstance()
  {
    if (simControlsPanel == null)
      simControlsPanel = new SimControlsPanel();
    return simControlsPanel;
  }

  /**
   * Gets the instance of the HostAddressField
   * @return The instance of the HostAddressField
   */
  public static JTextField getHostAddressField()
  {
    if (hostAddressField == null)
    {
      hostAddressField = new JTextField("localhost");
      hostAddressField.setMaximumSize(new Dimension(100, 10));
      hostAddressField.setHorizontalAlignment(JLabel.CENTER);
    }
    return hostAddressField;
  }
}
