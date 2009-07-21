package client.view;

import java.awt.*;
import javax.swing.*;
import client.controller.*;
import java.text.*;

/*****************************************************************************
 *  File:       MovementDataPanel.java
 *  Authors:    Sean Jagat and Nick Knowlson
 *  Assignment: 4
 *  Class:      CPS235
 *  Instructor: Frank Niscak
 *  Due Date:   November 23, 2007
 *
 *  Purpose:    The MovementDataPanel contains all of the components related
 *              to displaying data about the vehicle's movement
 ****************************************************************************/

@SuppressWarnings("serial")
public class MovementDataPanel extends JPanel
{
  private static MovementDataPanel instance = null;
  private static JLabel directionLabel = null, xPosLabel = null,
      yPosLabel = null, motorALabel = null, motorBLabel = null,
      distanceLabel = null;

  //Private constructor to implement singleton design
  private MovementDataPanel()
  {
    setAlignmentX(LEFT_ALIGNMENT);
    setPreferredSize(new Dimension(110,0));
    GridLayout gLayout = new GridLayout(8,1, 0,10);
    setLayout(gLayout);
    initLabels();
    add(ButtonGroup.getCenterButton());
    add(ButtonGroup.getSlowButton());
    add(directionLabel);
    add(xPosLabel);
    add(yPosLabel);
    add(motorALabel);
    add(motorBLabel);
    add(distanceLabel);
  }

  /**
   * Gets the instance of the MovementDataPanel
   * @return The instance of the MovementDataPanel
   */
  public static MovementDataPanel getInstance()
  {
    if (instance == null)
      instance = new MovementDataPanel();
    return instance;
  }

  /*
   * Helpter method to initialize the names of all fo the labels 
   */
  private void initLabels()
  {
    if (directionLabel == null || xPosLabel == null || yPosLabel == null
        || motorALabel == null || motorBLabel == null || distanceLabel == null)
    {
      directionLabel = new JLabel("Direction: ", SwingConstants.LEFT);
      xPosLabel = new JLabel("X Position: ");
      yPosLabel = new JLabel("Y Position: ");
      motorALabel = new JLabel("Left Motor: ");
      motorBLabel = new JLabel("Right Motor: ");
      distanceLabel = new JLabel("Distance: ");
    }
  }

  /**
   * Draws the labels along with their values
   */
  public void paintComponent(Graphics g)
  {
    try
    {
      super.paintComponent(g);
      NumberFormat formatter = new DecimalFormat("#.#");
      directionLabel.setText("Direction: "
                             + SensorInfo.getInstance().getDirection());
      xPosLabel.setText("X Position: "
                        + formatter.format(SensorInfo.getInstance()
                            .getCurrentXPos()));
      yPosLabel.setText("Y Position: "
                        + formatter.format(SensorInfo.getInstance()
                            .getCurrentYPos()));
      motorALabel.setText("Left Motor: "
                          + SensorInfo.getInstance().getLeftMotorLevel());
      motorBLabel.setText("Right Motor: "
                          + SensorInfo.getInstance().getRightMotorLevel());
      distanceLabel.setText("Distance: "
                            + formatter.format(SensorInfo.getInstance()
                                .getDistance()));
    }
    catch(NullPointerException e )
    {
      //Null values do not have to be dealt with, just ignored
    }
  }
}
