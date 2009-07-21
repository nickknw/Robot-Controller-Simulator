package client.view;

import javax.swing.*;

/*****************************************************************************
 *  File:       LogTextArea.java
 *  Authors:    Sean Jagat and Nick Knowlson
 *  Assignment: 4
 *  Class:      CPS235
 *  Instructor: Frank Niscak
 *  Due Date:   November 23, 2007
 *
 *  Purpose:    The LogTextArea class acts as a visual representation for text
 *              messages to be sent to the user
 ****************************************************************************/

@SuppressWarnings("serial")
public class LogTextArea extends JTextArea
{
  private static LogTextArea logTextArea = null;

  //Private constructor to implement singleton design
  private LogTextArea()
  {
    setWrapStyleWord(true);
    setEditable(false);
  }

  /**
   * Gets the instance of the LogTextArea
   * @return The instance of the LogTextArea
   */
  public static LogTextArea getInstance()
  {
    if (logTextArea == null)
      logTextArea = new LogTextArea();
    return logTextArea;
  }
  
}
