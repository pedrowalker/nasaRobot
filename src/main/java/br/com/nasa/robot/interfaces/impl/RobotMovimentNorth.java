package br.com.nasa.robot.interfaces.impl;

import br.com.nasa.robot.entity.Robot;
import br.com.nasa.robot.interfaces.RobotMoviment;
import br.com.nasa.robot.utils.RobotHandler;
import org.springframework.stereotype.Service;

@Service
public class RobotMovimentNorth implements RobotMoviment {
    @Override
    public void incrementAxis(Robot robot) {
        robot.setPositionY(robot.getPositionY() + 1);
    }

    @Override
    public void turnRight(Robot robot) {
        robot.setDirection(RobotHandler.DIRECTION_EAST);
    }

    @Override
    public void turnLeft(Robot robot) {
        robot.setDirection(RobotHandler.DIRECTION_WEST);
    }
}
