package client.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import util.*;

/*****************************************************************************
 *  File:       LogScrollPane.java
 *  Authors:    Sean Jagat and Nick Knowlson
 *  Assignment: 4
 *  Class:      CPS235
 *  Instructor: Frank Niscak
 *  Due Date:   November 23, 2007
 *
 *  Purpose:    The LogScrollPane class acts as a scrollpane for the
 *              LogTextArea class
 ****************************************************************************/

@SuppressWarnings("serial")
public class LogScrollPane extends JScrollPane
{
  private static LogScrollPane logScrollPane = null;

  //Private constructor to implement singleton design
  private LogScrollPane()
  {
    TitledBorder titledBorder = BorderFactory
        .createTitledBorder(BorderFactory.createLineBorder(Color.black, 1),
                            "Log", TitledBorder.DEFAULT_JUSTIFICATION,
                            TitledBorder.DEFAULT_POSITION,
                            Constants.DEFAULT_BORDER_FONT,
                            Constants.DEFAULT_BORDER_COLOR);
    titledBorder.setBorder(BorderFactory.createLineBorder(Color.black, 1));
    titledBorder.setTitleColor(Color.black);
    setPreferredSize(new Dimension(3, 150));
    setBorder(titledBorder);
    setViewportView(LogTextArea.getInstance());
    setAutoscrolls(true);
  }

  /**
   * Gets the instance of the LogScrollPane class
   * @return The instance of the LogScrollPane class
   */
  public static LogScrollPane getInstance()
  {
    if (logScrollPane == null)
      logScrollPane = new LogScrollPane();
    return logScrollPane;
  }
  
  /**
   * Draws the LogScrollPane
   */
  public void paintComponent(Graphics g)
  {
    
  }

}
