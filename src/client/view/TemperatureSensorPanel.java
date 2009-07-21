package client.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import client.controller.*;
import util.*;

/*****************************************************************************
 *  File:       TemperatureSensorPanel.java
 *  Authors:    Sean Jagat and Nick Knowlson
 *  Assignment: 4
 *  Class:      CPS235
 *  Instructor: Frank Niscak
 *  Due Date:   November 23, 2007
 *
 *  Purpose:    The TemperatureSensorPanel class is the panel that the Battery
 *              Sensor is drawn on
 ****************************************************************************/

@SuppressWarnings("serial")
public class TemperatureSensorPanel extends JPanel
{
  private JLabel temperatureDisplay = new JLabel((" degrees"), JLabel.CENTER);
  private static TemperatureSensorPanel instance = null;

  //Private constructor to implement singleton design
  private TemperatureSensorPanel()
  {
    setLayout(new BorderLayout());
    setBorder(BorderFactory
        .createTitledBorder(BorderFactory.createLineBorder(Color.black, 1),
                            "Temperature", TitledBorder.DEFAULT_JUSTIFICATION,
                            TitledBorder.DEFAULT_POSITION,
                            Constants.DEFAULT_BORDER_FONT,
                            Constants.DEFAULT_BORDER_COLOR));
    add(temperatureDisplay, BorderLayout.SOUTH);
  }

  /**
   * Gets the instance of the TemperatureSensorPanel class
   * @return the instance of the TemperatureSensorPanel class
   */
  public static TemperatureSensorPanel getInstance()
  {
    if (instance == null)
      instance = new TemperatureSensorPanel();
    return instance;
  }

  /**
   * Draws the TemperatureSensorPanel
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
    int iLineSpacing = 20;


    Integer[] iTemperatureValues = SensorInfo.getInstance().getTemperature();
    //  black background
    g2.setColor(new Color(0, 0, 0));
    g2.fill(new Rectangle(iBegin, iTop, iLimit - iBegin, iBottom - iTop));
    g2.setColor(new Color(0, 160, 0));
    //draw horizontal grid lines
    for (int iCount = iTop; iCount <= iBottom; iCount += iLineSpacing)
    {
      g2.drawLine(iBegin, iCount, iLimit, iCount);
    }
    //draw vertical lines
    for (int iCount = 0; iCount <= Constants.SIN_CURVE_HISTORY; iCount += 5)
    {
      g2.drawLine((int) iBegin + (iSize * (iCount) / Constants.SIN_CURVE_HISTORY),
                  iTop, (int) iBegin
                        + (iSize * (iCount) / Constants.SIN_CURVE_HISTORY),
                  iBottom);
    }

    g2.setColor(new Color(0, 240, 0));

    for (int iCount = 0; iCount < Constants.SIN_CURVE_HISTORY - 1; iCount++)
    {
      try
      {
          g2
              .drawLine(
                        (int) 5
                            + (iSize * (iCount) / Constants.SIN_CURVE_HISTORY),
                        iBottom - iLineSpacing - iTemperatureValues[iCount],
                        (int) 5
                            + (iSize * (iCount + 1) / Constants.SIN_CURVE_HISTORY),
                        iBottom - iLineSpacing - iTemperatureValues[iCount + 1]);
          temperatureDisplay.setText(iTemperatureValues[iCount + 1]
                                     + " degrees");
      }
      catch(NullPointerException e )
      {
        //this is expected behavior for the linkedqueue
      }
    }
  }
}
