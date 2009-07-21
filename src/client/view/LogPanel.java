package client.view;

import java.awt.*;
import javax.swing.*;


/*****************************************************************************
 *  File:       LogPanel.java
 *  Authors:    Sean Jagat and Nick Knowlson
 *  Assignment: 4
 *  Class:      CPS235
 *  Instructor: Frank Niscak
 *  Due Date:   November 23, 2007
 *
 *  Purpose:    The LogPanel class contains the LogScrollPane class
 ****************************************************************************/

@SuppressWarnings("serial")
public class LogPanel extends JPanel
{
  private static LogPanel logPanel = null;

  //Private constructor to implement singleton design
  private LogPanel()
  {
    setLayout(new BorderLayout());
    add(LogScrollPane.getInstance(), BorderLayout.CENTER);
  }

  /**
   * Gets the instance of the LogPanel class
   * @return The instance of the LogPanel class
   */
  public static LogPanel getInstance()
  {
    if (logPanel == null)
      logPanel = new LogPanel();
    return logPanel;
  }
}
