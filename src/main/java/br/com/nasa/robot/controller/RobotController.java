package br.com.nasa.robot.controller;

import br.com.nasa.robot.service.RobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/rest", produces = MediaType.APPLICATION_JSON_VALUE)
public class RobotController {

    @Autowired
    private RobotService robotService;

    @PostMapping(path = "/mars/{movimento}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> moveRobot(@PathVariable("movimento") String moviment) {
        return new ResponseEntity<>(robotService.doMoviment(moviment), HttpStatus.OK);
    }
}
