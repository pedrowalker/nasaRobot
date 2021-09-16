package br.com.nasa.robot;

import br.com.nasa.robot.service.RobotService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RobotApplicationTests {

    private RobotService robotService;

    @Test
    void perfectCase() {
        ResponseEntity<String> response = robotService.doMoviment("MMRMMRMM");
        assertEquals("(2, 0, S)", response.getBody());
        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
    }

    @Test
    void invalidCommand() {
        ResponseEntity<String> response = robotService.doMoviment("AAA");
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCodeValue());
    }

    @Test
    void invalidPosition() {
        ResponseEntity<String> response = robotService.doMoviment("MMMMMMMMMMMMMMMMMMMMMMMM");
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCodeValue());
    }

}
