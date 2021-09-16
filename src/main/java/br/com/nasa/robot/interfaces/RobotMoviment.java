package br.com.nasa.robot.interfaces;

import br.com.nasa.robot.entity.Robot;
import br.com.nasa.robot.utils.RobotHandler;

public interface RobotMoviment {
    void incrementAxis(Robot robot);

    void turnRight(Robot robot);

    void turnLeft(Robot robot);

    default void turnDirection(char direction, Robot robot) {
        if (direction == RobotHandler.TURN_LEFT)
            turnLeft(robot);
        else
            turnRight(robot);
    }
}
