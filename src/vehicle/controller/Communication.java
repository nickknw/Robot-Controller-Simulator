package vehicle.controller;

/*****************************************************************************
 *  File:       ButtonListener.java
 *  Authors:    Sean Jagat and Nick Knowlson
 *  Assignment: 4
 *  Class:      CPS235
 *  Instructor: Frank Niscak
 *  Due Date:   November 23, 2007
 *
 *  Purpose:    The Controller class is a general purpose class used to
 *              manipulate the vehicle
 ****************************************************************************/

import java.io.*;
import java.net.*;
import util.*;
import vehicle.model.*;


public class Communication implements Runnable
{
  private ServerSocket server = null;
  private Socket client = null;
  private BufferedReader in = null;
  private PrintWriter out = null;
  private boolean bConnected;
  private long lTimeSincePong;
  private static long lTimeWait;

  /**
   * Constructor waits on a port for a connection and creates the necessary
   * reader and writer
   */
  public Communication()
  {
    try
    {
      bConnected = true;
      server = new ServerSocket(Constants.PORT);
      lTimeWait = System.currentTimeMillis();
      server.setSoTimeout(Constants.TIME_TO_DIE);
      client = server.accept();
      lTimeSincePong = System.currentTimeMillis();
      in = new BufferedReader(new InputStreamReader(client.getInputStream()));
      out = new PrintWriter(client.getOutputStream(), true);
    }
    catch(IOException e )
    {
      System.err.println("Could not listen on port " + Constants.PORT);
      Debug.printError(e.toString());
    }
  }

  /**
   * Writes a message over the socket
   * @param sMessage The message to be written
   */
  public synchronized void write(String sMessage)
  {
    if (bConnected)
      out.println(sMessage);
  }

  /**
   * Reads a message from the reader buffer
   * @return The message from the reader buffer
   */
  private synchronized String read()
  {
    if (bConnected)
    {
      try
      {
        return in.readLine();
      }
      catch(IOException e )
      {
        System.err.println("Could not read from socket");
        Debug.printError(e.toString());
      }
    }
    return null;
  }

  /**
   * Run maintains the connection and reads any messages.  It sends messages to
   * an instance of the MessageInterpreter class if necessary
   */
  public void run()
  {
    if (System.currentTimeMillis() - lTimeWait < Constants.TIME_TO_DIE)
    {
      while (bConnected)
      {
        try
        {
          if (in.ready())
            while (in.ready())
            {
              String sMessage = read();
              if (sMessage.equals(Constants.PONG))
                lTimeSincePong = System.currentTimeMillis();
              else
                new Thread(new MessageInterpreter(sMessage)).start();
            }
          write(Constants.PING);
          if (System.currentTimeMillis() - lTimeSincePong > Constants.TIMEOUT)
            bConnected = false;
          Thread.sleep(Constants.TIME_TO_SLEEP);
        }
        catch(IOException e )
        {
          System.err.println("Could not read from socket");
          Debug.printError(e.toString());
        }
        catch(InterruptedException e )
        {
          System.err.println("Communication interrupted");
          Debug.printError(e.toString());
          bConnected = false;
        }
      }
      finalize();
      Controller.getInstance().reconnect();
    }
    bConnected = false;
    Controller.getInstance().setRunning(true);
    Controller.getInstance().setAlive(false);
  }

  /**
   * Finalize is the destructor for the class and closes any open ports,
   * readers and writers
   */
  public void finalize()
  {
    Debug.print(server.toString());
    Debug.print(client.toString());

    if (server != null)
    {
      try
      {
        server.close();
      }
      catch(IOException e )
      {
        System.err.println("Could not close server socket.");
        Debug.printError(e.toString());
      }
    }

    if (client != null)
    {
      try
      {
        client.close();
      }
      catch(IOException e )
      {
        System.err.println("Could not close client socket.");
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
        System.err.println("Could not close input reader from client");
        Debug.printError(e.toString());
      }
    }

    if (out != null)
      out.close();
  }
}
