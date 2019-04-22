package com.bogdan.myspringtask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MySpringTaskApplicationTests {

    private static final Logger logger = LogManager.getLogger(MySpringTaskApplicationTests.class);

    @Test
    public void log4j2Test() {
        for (int i = 0; i < 200; i++ ){
            logger.debug("Debug Log Test");
            logger.info("Info Log Test");
            logger.warn("Warn Log Test");
            logger.error("Error Log Test");
            logger.fatal("Fatal Log Test");
        }

    }

}
