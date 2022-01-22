package br.com.nasa.robot;

import br.com.nasa.robot.service.RobotService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class RobotApplicationTests {

    @Autowired
    private RobotService robotService;

    @Test
    void perfectCase() {
        String response = robotService.doMoviment("MMRMMRMM");
        assertEquals("(2, 0, S)", response);
    }

    @Test
    void invalidCommand() {
        String response = robotService.doMoviment("AAA");
        assertNull(response);
    }

    @Test
    void invalidPosition() {
        String response = robotService.doMoviment("MMMMMMMMMMMMMMMMMMMMMMMM");
        assertNull(response);
    }

}
