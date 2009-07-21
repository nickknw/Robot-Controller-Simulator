package client.view;

import javax.swing.*;
import java.awt.*;
import util.*;
import client.controller.*;

/*****************************************************************************
 *  File:       MovementGridPanel.java
 *  Authors:    Sean Jagat and Nick Knowlson
 *  Assignment: 4
 *  Class:      CPS235
 *  Instructor: Frank Niscak
 *  Due Date:   November 23, 2007
 *
 *  Purpose:    The MovementGridPanel draws the vehicle's movement
 ****************************************************************************/

@SuppressWarnings("serial")
public class MovementGridPanel extends JPanel
{
  private static MovementGridPanel instance = null;

  //Private constructor to implement singleton design
  private MovementGridPanel()
  {
    setBorder(BorderFactory.createLineBorder(Color.black, 1));
  }

  /**
   * Gets the instance of the MovementGridPanel
   * @return The instance of the MovementGridPanel
   */
  public static MovementGridPanel getInstance()
  {
    if (instance == null)
      instance = new MovementGridPanel();
    return instance;
  }

  /**
   * Draws the path of the vehicle
   */
  public void paintComponent(Graphics g)
  {
    Graphics2D g2 = (Graphics2D) g;
    g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                        RenderingHints.VALUE_RENDER_QUALITY);
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
    super.paintComponent(g2);

    int iLeftLimit = 5;
    int iRightLimit = this.getWidth() - 5;
    int iTopLimit = 5;
    int iBottomLimit = this.getHeight() - 5;
    int iHorizontalSize = iRightLimit - iLeftLimit;
    int iVerticalSize = iBottomLimit - iTopLimit;

    g2.setColor(new Color(0, 0, 0));

    Double[] xValues = SensorInfo.getInstance().getXPos();
    Double[] yValues = SensorInfo.getInstance().getYPos();

    for (int iCount = 0; iCount < Constants.SIN_CURVE_HISTORY - 1; iCount++)
    {
      try
      {
        int x1 = (int) (iHorizontalSize / 2 + xValues[iCount]);
        int y1 = (int) (iVerticalSize / 2 - yValues[iCount]);
        int x2 = (int) (iHorizontalSize / 2 + xValues[iCount + 1]);
        int y2 = (int) (iVerticalSize / 2 - yValues[iCount + 1]);
        g2.drawLine(x1, y1, x2, y2);
      }
      catch(NullPointerException e )
      {
        //this is expected behavior for the LinkedQueue
      }
    }
  }
}
