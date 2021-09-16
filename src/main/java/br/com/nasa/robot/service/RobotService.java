package br.com.nasa.robot.service;

import br.com.nasa.robot.entity.Robot;
import br.com.nasa.robot.interfaces.RobotMoviment;
import br.com.nasa.robot.utils.InvalidMovimentException;
import br.com.nasa.robot.utils.RobotHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RobotService {

    @Autowired
    private RobotMoviment robotMovimentNorth;
    @Autowired
    private RobotMoviment robotMovimentSouth;
    @Autowired
    private RobotMoviment robotMovimentEast;
    @Autowired
    private RobotMoviment robotMovimentWest;

    private RobotMoviment robotMoviment;

    public ResponseEntity<String> doMoviment(String moviment) {
        Robot robot = new Robot();
        ResponseEntity<String> responseEntity;
        changeRobotMoviment(robot);
        try {
            moviment.chars().mapToObj(c -> (char) c)
                    .forEach(character -> {
                        switch (character) {
                            case RobotHandler.MOVIMENT:
                                robotMoviment.incrementAxis(robot);
                                robot.validatePosition();
                                break;
                            case RobotHandler.TURN_LEFT:
                            case RobotHandler.TURN_RIGHT:
                                robotMoviment.turnDirection(character, robot);
                                changeRobotMoviment(robot);
                                break;
                            default:
                                throw new InvalidMovimentException();
                        }
                    });
            responseEntity = new ResponseEntity<>(retrievePosition(robot), HttpStatus.OK);
        } catch (InvalidMovimentException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    private void changeRobotMoviment(Robot robot) {
        switch (robot.getDirection()) {
            case RobotHandler.DIRECTION_EAST:
                robotMoviment = robotMovimentEast;
                break;
            case RobotHandler.DIRECTION_WEST:
                robotMoviment = robotMovimentWest;
                break;
            case RobotHandler.DIRECTION_SOUTH:
                robotMoviment = robotMovimentSouth;
                break;
            default:
                robotMoviment = robotMovimentNorth;
                break;
        }
    }

    private String retrievePosition(Robot robot) {
        return String.valueOf(RobotHandler.FORMATTER.format("(%d, %d, %s)",
                robot.getPositionX(), robot.getPositionY(), robot.getDirection()));
    }
}
