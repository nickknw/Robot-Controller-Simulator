package util;

/*****************************************************************************
 *  File:       Constants.java
 *  Authors:    Sean Jagat and Nick Knowlson
 *  Assignment: 4
 *  Class:      CPS235
 *  Instructor: Frank Niscak
 *  Due Date:   November 23, 2007
 *
 *  Purpose:    The Constants class contains commonly used constants
 ****************************************************************************/


import java.awt.*;


public class Constants
{
  public static final int TIME_TO_SLEEP = 500, PORT = 4000,
      SIN_CURVE_HISTORY = 50, GRID_SIZE = 100, MAX_BATTERY = 9000,
      LOW_BATTERY = 1000, ZERO = 0, DEFAULT_BRIGHTNESS = 50,
      DEFAULT_SOUND = 50, DEFAULT_TEMPERATURE = 20, DEFAULT_STEERING = 50,
      TIMEOUT = 4000, DEFAULT_SPEED = 5, DEGREES_PER_CIRCLE = 360,
      TIME_TO_DIE = 15000;
  public static final double PERCENT = 100;
  public static final String VEHICLE = "LegoMindstorm ",
      BATTERY = "BatterySensor ", LIGHT = "LightSensor ",
      MOVEMENT_MANAGER = "MovementManagerSensor ", SOUND = "SoundSensor ",
      TEMPERATURE = "TemperatureSensor ", TOUCH = "TouchSensor ",
      COMMAND = "Command ", RESPONSE = "Response ", ERROR = "Error ",
      UPDATE = "Update ", SET_LEFT_MOTOR = "Set Left Motor ",
      SET_RIGHT_MOTOR = "Set Right Motor ", START = "Start", STOP = "Stop",
      SLOW = "Slow", CENTER = "Center", RESET = "Reset",
      DIRECTION = "Direction", SPEED = "Speed", PING = "Ping", PONG = "Pong",
      CONNECT = "Connect ", DISCONNECT = "Disconnect ";
  public static final Font DEFAULT_BORDER_FONT = new Font("Dialog", Font.BOLD,
      12), DEFAULT_BUTTON_FONT = new Font("Dialog", Font.BOLD, 14);
  public static final Color DEFAULT_BORDER_COLOR = new Color(51, 51, 51);
}
