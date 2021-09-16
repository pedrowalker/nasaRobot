package br.com.nasa.robot.interfaces.impl;

import br.com.nasa.robot.entity.Robot;
import br.com.nasa.robot.interfaces.RobotMoviment;
import br.com.nasa.robot.utils.RobotHandler;
import org.springframework.stereotype.Service;

@Service
public class RobotMovimentEast implements RobotMoviment {
    @Override
    public void incrementAxis(Robot robot) {
        robot.setPositionX(robot.getPositionX() + 1);
    }

    @Override
    public void turnRight(Robot robot) {
        robot.setDirection(RobotHandler.DIRECTION_SOUTH);
    }

    @Override
    public void turnLeft(Robot robot) {
        robot.setDirection(RobotHandler.DIRECTION_NORTH);
    }
}
