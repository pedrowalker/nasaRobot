package br.com.nasa.robot.service;

import br.com.nasa.robot.entity.Robot;
import br.com.nasa.robot.utils.InvalidMovimentException;
import br.com.nasa.robot.utils.RobotHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RobotService {

    public ResponseEntity<String> doMoviment(String moviment) {
        Robot robot = new Robot();
        ResponseEntity<String> responseEntity;
        try {
            moviment.chars().mapToObj(c -> (char) c)
                    .forEach(character -> {
                        switch (character) {
                            case RobotHandler.MOVIMENT:
                                incrementPosition(robot);
                                break;
                            case RobotHandler.TURN_LEFT:
                            case RobotHandler.TURN_RIGHT:
                                turnOrientation(robot, character);
                                break;
                            default:
                                throw new InvalidMovimentException();
                        }
                    });
            if (isValidPosition(robot))
                responseEntity = new ResponseEntity<>(retrievePosition(robot), HttpStatus.OK);
            else
                responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (InvalidMovimentException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    private void incrementPosition(Robot robot) {
        switch (robot.getOrientation()) {
            case RobotHandler.ORIENTATION_NORTH:
                robot.setPositionY(robot.getPositionY() + 1);
                break;
            case RobotHandler.ORIENTATION_SOUTH:
                robot.setPositionY(robot.getPositionY() - 1);
                break;
            case RobotHandler.ORIENTATION_EAST:
                robot.setPositionX(robot.getPositionX() + 1);
                break;
            case RobotHandler.ORIENTATION_WEST:
                robot.setPositionX(robot.getPositionX() - 1);
                break;
        }
    }

    private void turnOrientation(Robot robot, Character character) {
        switch (robot.getOrientation()) {
            case RobotHandler.ORIENTATION_NORTH:
                if (character == RobotHandler.TURN_LEFT)
                    robot.setOrientation(RobotHandler.ORIENTATION_WEST);
                else
                    robot.setOrientation(RobotHandler.ORIENTATION_EAST);
                break;
            case RobotHandler.ORIENTATION_SOUTH:
                if (character == RobotHandler.TURN_LEFT)
                    robot.setOrientation(RobotHandler.ORIENTATION_EAST);
                else
                    robot.setOrientation(RobotHandler.ORIENTATION_WEST);
                break;
            case RobotHandler.ORIENTATION_WEST:
                if (character == RobotHandler.TURN_LEFT)
                    robot.setOrientation(RobotHandler.ORIENTATION_SOUTH);
                else
                    robot.setOrientation(RobotHandler.ORIENTATION_NORTH);
                break;
            case RobotHandler.ORIENTATION_EAST:
                if (character == RobotHandler.TURN_LEFT)
                    robot.setOrientation(RobotHandler.ORIENTATION_NORTH);
                else
                    robot.setOrientation(RobotHandler.ORIENTATION_SOUTH);
                break;
        }
    }

    private boolean isValidPosition(Robot robot) {
        return robot.getPositionX() <= RobotHandler.SIZE && robot.getPositionY() <= RobotHandler.SIZE;
    }

    private String retrievePosition(Robot robot) {
        return String.valueOf(RobotHandler.FORMATTER.format("(%d, %d, %s)",
                robot.getPositionX(), robot.getPositionY(), robot.getOrientation()));
    }
}
