package br.com.nasa.robot.entity;

import br.com.nasa.robot.utils.InvalidMovimentException;
import br.com.nasa.robot.utils.RobotHandler;
import lombok.Data;

@Data
public class Robot {

    private int positionX;
    private int positionY;
    private char direction;

    public void validatePosition() {
        if (positionX > RobotHandler.SIZE || positionY > RobotHandler.SIZE) throw new InvalidMovimentException();
    }
}
