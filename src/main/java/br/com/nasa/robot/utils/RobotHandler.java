package br.com.nasa.robot.utils;

import java.util.Formatter;

public class RobotHandler {

    private RobotHandler() {
    }

    public static final Integer SIZE = 5;
    public static final char MOVIMENT = 'M';
    public static final char TURN_LEFT = 'L';
    public static final char TURN_RIGHT = 'R';

    public static final String ORIENTATION_WEST = "W";
    public static final String ORIENTATION_NORTH = "N";
    public static final String ORIENTATION_SOUTH = "S";
    public static final String ORIENTATION_EAST = "E";

    public static final Formatter FORMATTER = new Formatter();
}
