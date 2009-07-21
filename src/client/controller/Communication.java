package client.controller;

import java.io.*;
import java.net.*;
import client.model.*;
import util.*;


/*****************************************************************************
 *  File:       Communication.java
 *  Authors:    Sean Jagat and Nick Knowlson
 *  Assignment: 4
 *  Class:      CPS235
 *  Instructor: Frank Niscak
 *  Due Date:   November 23, 2007
 *
 *  Purpose:    The Communication class encapsulates all of the logic for
 *              reading and writing from sockets.
 ****************************************************************************/

public class Communication implements Runnable
{
  private Socket vehicle = null;
  private BufferedReader in = null;
  private PrintWriter out = null;
  private boolean bConnected;
  private long lTimeSincePing;

  /**
   * Connects to the host socket and creates readers and writer for writing on
   * the socket
   * @param sHost The hostname to connect to
   */
  public Communication(String sHost)
  {
    lTimeSincePing = System.currentTimeMillis();
    try
    {
      vehicle = new Socket(InetAddress.getByName(sHost), Constants.PORT);
      in = new BufferedReader(new InputStreamReader(vehicle.getInputStream()));
      out = new PrintWriter(vehicle.getOutputStream(), true);
      bConnected = true;
    }
    catch(UnknownHostException e )
    {
      Controller.getInstance().print("Could not find host");
      Debug.printError(e.toString());
      bConnected = false;
    }
    catch(IOException e )
    {
      Controller.getInstance().print("Could not connect to vehicle");
      Debug.printError(e.toString());
      bConnected = false;
    }
  }

  /**
   * Writes a message on the socket
   * @param sMessage The message to be written
   */
  public synchronized void write(String sMessage)
  {
    out.println(sMessage);
  }

  /**
   * Reads the next line in the reader's buffer
   * @return The next line in the reader's buffer
   */
  public synchronized String read()
  {
    try
    {
      return in.readLine();
    }
    catch(IOException e )
    {
      Debug.print("Could not read");
    }
    return null;
  }

  /**
   * Consistently reads on the socket and sends new messages to a new instance
   * of the MessageInterpreter class and starts the thread
   */
  public void run()
  {
    while (bConnected)
    {
      try
      {
        if (in.ready())
          while (in.ready())
          {
            String sMessage = read();
            if (sMessage.equals(Constants.PING))
            {
              write(Constants.PONG);
              lTimeSincePing = System.currentTimeMillis();
            }
            else
              new Thread(new MessageInterpreter(sMessage)).start();
          }
        if (System.currentTimeMillis() - lTimeSincePing > Constants.TIMEOUT)
          bConnected = false;
        Thread.sleep(Constants.TIME_TO_SLEEP);
      }
      catch(IOException e )
      {
        Debug.print("Could not read");
      }
      catch(InterruptedException e )
      {
        Debug.print("Communication dying");
        bConnected = false;
      }
    }
    Controller.getInstance().connect();
  }

  /**
   * Closes the connection, reader and writer to the server
   */
  public void finalize()
  {
    Debug.print(vehicle.toString());

    if (vehicle != null)
    {
      try
      {
        vehicle.close();
      }
      catch(IOException e )
      {
        Debug.print("Could not close socket");
        Debug.printError(e.toString());
      }
    }

    if (in != null)
    {
      try
      {
        in.close();
      }
      catch(IOException e )
      {
        Debug.print("Could not close input reader from socket");
        Debug.printError(e.toString());
      }
    }

    if (out != null)
      out.close();
  }
}
