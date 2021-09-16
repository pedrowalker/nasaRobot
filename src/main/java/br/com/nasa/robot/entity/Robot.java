package br.com.nasa.robot.entity;

import br.com.nasa.robot.utils.RobotHandler;
import lombok.Data;

@Data
public class Robot {

    public Robot() {
        positionX = 0;
        positionY = 0;
        orientation = RobotHandler.ORIENTATION_NORTH;
    }

    private int positionX;
    private int positionY;
    private String orientation;
}
