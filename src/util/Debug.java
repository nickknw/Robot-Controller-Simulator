package util;

/*****************************************************************************
 *  File:       Debug.java
 *  Authors:    Sean Jagat and Nick Knowlson
 *  Assignment: 4
 *  Class:      CPS235
 *  Instructor: Frank Niscak
 *  Due Date:   November 23, 2007
 *
 *  Purpose:    The Debug class contains print methods for debugging.
 *              Debug.print and Debug.printError may be accessed statically
 *              and their output can be turned on and off using the DEBUG
 *              variable.  There is no need for variable checking in classes
 *              using the Debug class.
 ****************************************************************************/

public class Debug
{
  public static final boolean DEBUG = false;

  /**
   * Prints a message
   * @param sPrint The message to print
   * @return Whether debugging is enabled
   */
  public static boolean print(String sPrint)
  {
    if (DEBUG)
      System.out.println(sPrint);
    return DEBUG;
  }

  /**
   * Prints an error
   * @param sError The error to print
   * @return Whether debugging is enabled
   */
  public static boolean printError(String sError)
  {
    if (DEBUG)
      System.err.println(sError);
    return DEBUG;
  }
}
