package client.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import client.controller.*;
import util.*;

/*****************************************************************************
 *  File:       SoundSensorPanel.java
 *  Authors:    Sean Jagat and Nick Knowlson
 *  Assignment: 4
 *  Class:      CPS235
 *  Instructor: Frank Niscak
 *  Due Date:   November 23, 2007
 *
 *  Purpose:    The SoundSensorPanel class is the panel that the Battery
 *              Sensor is drawn on
 ****************************************************************************/

@SuppressWarnings("serial")
public class SoundSensorPanel extends JPanel
{
  private static SoundSensorPanel instance = null;
  private JLabel soundDisplay = new JLabel((" decibels"), JLabel.CENTER);

  //Private constructor to implement singleton design
  private SoundSensorPanel()
  {
    setLayout(new BorderLayout());
    setBorder(BorderFactory
        .createTitledBorder(BorderFactory.createLineBorder(Color.black, 1),
                            "Sound", TitledBorder.DEFAULT_JUSTIFICATION,
                            TitledBorder.DEFAULT_POSITION,
                            Constants.DEFAULT_BORDER_FONT,
                            Constants.DEFAULT_BORDER_COLOR));
    add(soundDisplay, BorderLayout.SOUTH);
  }

  /**
   * Gets the instance of the SoundSensorPanel class
   * @return the instance of the SoundSensorPanel class
   */
  public static SoundSensorPanel getInstance()
  {
    if (instance == null)
      instance = new SoundSensorPanel();
    return instance;
  }

  /**
   * Draws the SoundSensorPanel
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
    
    Integer[] iSoundValues = SensorInfo.getInstance().getSound();
    
    //black background
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
        if (!iSoundValues[iCount].equals(null))
        {
          g2
              .drawLine(
                        (int) 5
                            + (iSize * (iCount) / Constants.SIN_CURVE_HISTORY),
                        iBottom - iSoundValues[iCount],
                        (int) 5
                            + (iSize * (iCount + 1) / Constants.SIN_CURVE_HISTORY),
                        iBottom - iSoundValues[iCount + 1]);
          soundDisplay.setText(iSoundValues[iCount+1] + " decibels");
        }
      }
      catch(NullPointerException e )
      {
        //this is expected behavior for the linkedqueue
      }
    }
  }

}
