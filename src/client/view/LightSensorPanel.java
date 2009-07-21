package client.view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;
import util.*;

import client.controller.*;

/*****************************************************************************
 *  File:       LightSensorPanel.java
 *  Authors:    Sean Jagat and Nick Knowlson
 *  Assignment: 4
 *  Class:      CPS235
 *  Instructor: Frank Niscak
 *  Due Date:   November 23, 2007
 *
 *  Purpose:    The LightSensorPanel class is the panel that the Battery
 *              Sensor is drawn on
 ****************************************************************************/

@SuppressWarnings("serial")
public class LightSensorPanel extends JPanel
{
  private JLabel lightDisplay = new JLabel((" brightness"), JLabel.CENTER);
  private static LightSensorPanel instance = null;

  //Private constructor to implement singleton design
  private LightSensorPanel()
  {
    setLayout(new BorderLayout());
    setBorder(BorderFactory
        .createTitledBorder(BorderFactory.createLineBorder(Color.black, 1),
                            "Light", TitledBorder.DEFAULT_JUSTIFICATION,
                            TitledBorder.DEFAULT_POSITION,
                            Constants.DEFAULT_BORDER_FONT,
                            Constants.DEFAULT_BORDER_COLOR));
    add(lightDisplay, BorderLayout.SOUTH);
  }

  /**
   * Gets the instance of the LightSensorPanel class
   * @return the instance of the LightSensorPanel class
   */
  public static LightSensorPanel getInstance()
  {
    if (instance == null)
      instance = new LightSensorPanel();
    return instance;
  }

  /**
   * Draws the LightSensorPanel
   */
  public void paintComponent(Graphics g)
  {

    Graphics2D g2 = (Graphics2D) g;
    g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                        RenderingHints.VALUE_RENDER_QUALITY);
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
    super.paintComponent(g2);
    //x,y,w,h
    int iBegin = 5;
    int iLimit = this.getWidth() - 5;
    int iSize = iLimit - iBegin;
    int iBottom = 120;
    int iTop = 20;

    Integer[] iLightValues = SensorInfo.getInstance().getLight();
    
    //  black background
    g2.setColor(new Color(0, 0, 0));
    g2.fill(new Rectangle(iBegin,iTop,iLimit-iBegin,iBottom-iTop));
    g2.setColor(new Color(0,160,0));
    //draw horizontal grid lines
    for (int iCount = iTop; iCount <= 120; iCount += 20)
    {
      g2.drawLine(iBegin, iCount, iLimit, iCount);
    }
    //draw vertical lines
    for (int iCount = 0; iCount <= Constants.SIN_CURVE_HISTORY; iCount += 5)
    {
      g2.drawLine((int) 5 + (iSize * (iCount) / Constants.SIN_CURVE_HISTORY),
                  iTop, (int) 5
                      + (iSize * (iCount) / Constants.SIN_CURVE_HISTORY), iBottom);
    }

    g2.setColor(new Color(0,240,0));

    //draw graph line
    for (int iCount = 0; iCount < Constants.SIN_CURVE_HISTORY - 1; iCount++)
    {
      try
      {
          //Controller.getInstance().print("" + iLightValues[iCount]);
          g2
              .drawLine(
                        (int) iBegin
                            + (iSize * (iCount) / Constants.SIN_CURVE_HISTORY),
                        iBottom - iLightValues[iCount],
                        (int) iBegin
                            + (iSize * (iCount + 1) / Constants.SIN_CURVE_HISTORY),
                        iBottom - iLightValues[iCount + 1]);
          lightDisplay.setText(iLightValues[iCount + 1] + " brightness");
      }
      catch(NullPointerException e )
      {
        //this is expected behavior for the linkedqueue
      }

    }

  }
}
