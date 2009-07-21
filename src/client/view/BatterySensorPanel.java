package client.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import util.*;
import client.controller.*;

/*****************************************************************************
 *  File:       BatterySensorPanel.java
 *  Authors:    Sean Jagat and Nick Knowlson
 *  Assignment: 4
 *  Class:      CPS235
 *  Instructor: Frank Niscak
 *  Due Date:   November 23, 2007
 *
 *  Purpose:    The BatterySensorPanel class is the panel that the Battery
 *              Sensor is drawn on
 ****************************************************************************/

@SuppressWarnings("serial")
public class BatterySensorPanel extends JPanel
{
  private JLabel chargeDisplay = new JLabel((" mV"), JLabel.CENTER);
  private static BatterySensorPanel instance = null;

  //Private constructor to implement singleton design
  private BatterySensorPanel()
  {
    setLayout(new BorderLayout());
    setBorder(BorderFactory
        .createTitledBorder(BorderFactory.createLineBorder(Color.black, 1),
                            "Battery", TitledBorder.DEFAULT_JUSTIFICATION,
                            TitledBorder.DEFAULT_POSITION,
                            Constants.DEFAULT_BORDER_FONT,
                            Constants.DEFAULT_BORDER_COLOR));
    add(chargeDisplay, BorderLayout.SOUTH);

  }

  /**
   * Gets the instance of the BatterySensorPanel class
   * @return the instance of the BatterySensorPanel class
   */
  public static BatterySensorPanel getInstance()
  {
    if (instance == null)
      instance = new BatterySensorPanel();
    return instance;
  }

  /**
   * Draws the BatterySensorPanel
   */
  public void paintComponent(Graphics g)
  {
    Graphics2D g2 = (Graphics2D) g;
    g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                        RenderingHints.VALUE_RENDER_QUALITY);
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
    super.paintComponent(g2);

    //set drawing variables
    int iLimit = this.getWidth() - 10;
    int iBegin = 10;

    int iCharge = SensorInfo.getInstance().getCharge();
    int iWidth = 40;
    int iTop = 45;
    int iHeight = 60;

    chargeDisplay.setText(iCharge + " mV");

    int iCurrentCharge = (int) (((double) iCharge / 9000) * (double) (iLimit
                                                                      - (iBegin + iWidth / 2) - iWidth / 2));
    //charged battery
    g2.setColor(new Color(0, 0, 0));

    //fix artifacts at 0
    if (iCurrentCharge != 0)
      g2.fillOval(iBegin, iTop, iWidth, iHeight);

    g2.fillRect(iBegin + iWidth / 2, iTop, iCurrentCharge, iHeight);

    //uncharged battery
    //fill


    g2.setColor(new Color(0, 0, 0));
    g2.drawOval(iBegin + iCurrentCharge - 1, iTop, iWidth, iHeight);
    g2.setColor(new Color(200, 200, 200));
    g2.fillRect(iBegin + iCurrentCharge + (iWidth / 2), iTop,
                iLimit - (iBegin + iWidth / 2) - iWidth / 2 - iCurrentCharge,
                iHeight);
    //outline
    g2.setColor(new Color(0, 0, 0));

    g2.drawRect(iBegin + iCurrentCharge + (iWidth / 2), iTop,
                iLimit - (iBegin + iWidth / 2) - iWidth / 2 - iCurrentCharge,
                iHeight);
    g2.setColor(new Color(200, 200, 200));
    g2.fillOval(iBegin + iCurrentCharge, iTop + 1, iWidth, iHeight - 1);

    //top
    g2.setColor(new Color(255, 255, 255));
    g2.fillOval(iLimit - iWidth, iTop, iWidth, iHeight);
    g2.setColor(new Color(0, 0, 0));
    g2.drawOval(iLimit - iWidth, iTop, iWidth, iHeight);

  }

}
