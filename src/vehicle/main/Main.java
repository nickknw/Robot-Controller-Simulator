package vehicle.main;

import vehicle.controller.*;

/*****************************************************************************
 *  File:       Main.java
 *  Authors:    Sean Jagat and Nick Knowlson
 *  Assignment: 4
 *  Class:      CPS235
 *  Instructor: Frank Niscak
 *  Due Date:   November 23, 2007
 *
 *  Purpose:    The Main class initializes the program and starts it
 ****************************************************************************/

public class Main
{
  /**
   * Initializes the program and starts it
   * @param args Arguments
   */
  public static void main(String[] args)
  {
    Controller.getInstance();
  }
}
