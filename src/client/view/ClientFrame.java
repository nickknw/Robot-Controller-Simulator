package client.view;


import javax.swing.*;
import java.awt.*;

/*****************************************************************************
 *  File:       ClientFrame.java
 *  Authors:    Sean Jagat and Nick Knowlson
 *  Assignment: 4
 *  Class:      CPS235
 *  Instructor: Frank Niscak
 *  Due Date:   November 23, 2007
 *
 *  Purpose:    The ClientFrame class acts as the frame for the gui and is
 *              the root component to which everything is added
 ****************************************************************************/


@SuppressWarnings("serial")
public class ClientFrame extends JFrame
{
  private JPanel jContentPane = null;
  private static ClientFrame instance = null;

  /*
   * This is the default constructor
   * Private to implement singleton design
   */
  private ClientFrame()
  {
    super();
    initialize();
  }

  /**
   * Gets the instance of the ClientFrame
   * @return The instance of the ClientFrame
   */
  public static ClientFrame getInstance()
  {
    if (instance == null)
      instance = new ClientFrame();
    return instance;
  }


  /**
   * This method initializes this
   *
   * @return void
   */
  private void initialize()
  {
    setContentPane(getJContentPane());
    setTitle("Lego Mindstorm Vehicle Controller");
    setBounds(new Rectangle(0, 0, 750, 625));
    setPreferredSize(new Dimension(750, 625));
    setMinimumSize(new Dimension(500,550));
    setLocation(new Point(
        (Toolkit.getDefaultToolkit().getScreenSize().width / 2) - 375, (Toolkit
            .getDefaultToolkit().getScreenSize().height / 2) - 313));

  }

  /**
   * This method initializes jContentPane
   *
   * @return javax.swing.JPanel
   */
  private JPanel getJContentPane()
  {
    if (jContentPane == null)
    {
      jContentPane = new JPanel();
      jContentPane.setLayout(new BorderLayout());
      jContentPane.add(SensorPanel.getInstance(), BorderLayout.NORTH);
      jContentPane.add(SimControlsPanel.getInstance(), BorderLayout.EAST);
      jContentPane.add(LogPanel.getInstance(), BorderLayout.SOUTH);
      jContentPane.add(MovementPanel.getInstance(), BorderLayout.CENTER);
    }
    return jContentPane;
  }


}
