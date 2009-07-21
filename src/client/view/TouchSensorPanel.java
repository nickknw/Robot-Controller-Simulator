package client.view;

import java.awt.*;
import client.controller.*;
import javax.swing.*;
import javax.swing.border.*;
import util.*;

/*****************************************************************************
 *  File:       TouchSensorPanel.java
 *  Authors:    Sean Jagat and Nick Knowlson
 *  Assignment: 4
 *  Class:      CPS235
 *  Instructor: Frank Niscak
 *  Due Date:   November 23, 2007
 *
 *  Purpose:    The TouchSensorPanel class is the panel that the Battery
 *              Sensor is drawn on
 ****************************************************************************/

@SuppressWarnings("serial")
public class TouchSensorPanel extends JPanel
{
  private static TouchSensorPanel instance = null;

  //Private constructor to implement singleton design
  private TouchSensorPanel()
  {
    setBorder(BorderFactory
        .createTitledBorder(BorderFactory.createLineBorder(Color.black, 1),
                            "Touch", TitledBorder.DEFAULT_JUSTIFICATION,
                            TitledBorder.DEFAULT_POSITION,
                            Constants.DEFAULT_BORDER_FONT,
                            Constants.DEFAULT_BORDER_COLOR));
  }

  /**
   * Gets the instance of the TouchSensorPanel class
   * @return the instance of the TouchSensorPanel class
   */
  public static TouchSensorPanel getInstance()
  {
    if (instance == null)
      instance = new TouchSensorPanel();
    return instance;
  }

  /**
   * Draws the TouchSensorPanel
   */
  public void paintComponent(Graphics g)
  {
    Graphics2D g2 = (Graphics2D) g;
    g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                        RenderingHints.VALUE_RENDER_QUALITY);
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
    super.paintComponent(g2);

    int iBegin = 10;
    int iLimit = this.getWidth() - 10;
    int iFontSize = (int) ((iLimit - iBegin) / 3);
    int iFontScale = (int) (iFontSize - 50) / 2;

    Polygon polyStar = drawStar(iBegin, iLimit);

    if (SensorInfo.getInstance().getTouch())
    {
      g2.setColor(new Color(220, 0, 0));
      g2.fill(polyStar);
      g2.setColor(new Color(0, 0, 0));
      g2.setFont(new Font("SansSerif", Font.BOLD, iFontSize));
      g2.drawString("BLAM", (int) ((iLimit - iBegin) * .1), 95 + iFontScale);
    }
  }

  /*
   * Helper method draws a star on the TouchSensorPanel
   */
  private Polygon drawStar(int iBegin, int iLimit)
  {
    Polygon polyStar = new Polygon();
    polyStar.addPoint(iBegin, 30);
    polyStar.addPoint((int) ((iLimit - iBegin) * .3), 40);
    polyStar.addPoint((int) ((iLimit - iBegin) * .4), 20);
    polyStar.addPoint((int) ((iLimit - iBegin) * .6), 50);
    polyStar.addPoint((int) ((iLimit - iBegin) * .65), 25);
    polyStar.addPoint((int) ((iLimit - iBegin) * .8), 45);
    polyStar.addPoint(iLimit, 20);
    polyStar.addPoint((int) ((iLimit - iBegin) * .9), 70);
    polyStar.addPoint(iLimit, 80);
    polyStar.addPoint((int) ((iLimit - iBegin) * .9), 90);
    polyStar.addPoint((int) ((iLimit - iBegin) * .95), 120);
    polyStar.addPoint((int) ((iLimit - iBegin) * .8), 100);
    polyStar.addPoint((int) ((iLimit - iBegin) * .7), 140);
    polyStar.addPoint((int) ((iLimit - iBegin) * .5), 110);
    polyStar.addPoint((int) ((iLimit - iBegin) * .4), 140);
    polyStar.addPoint((int) ((iLimit - iBegin) * .3), 100);
    polyStar.addPoint((int) ((iLimit - iBegin) * .1), 120);
    polyStar.addPoint((int) ((iLimit - iBegin) * .2), 80);
    return polyStar;
  }
}
