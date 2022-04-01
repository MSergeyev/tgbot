package ru.demoprojects.tgbot.llogger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;

public class StepsLog implements ApplicationRunner {

    private static final Logger LOGGER = LogManager.getLogger(StepsLog.class);

    public static void main(String[] args) {
        SpringApplication.run(StepsLog.class, args);
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        LOGGER.debug("Debugging log");
        LOGGER.info("Info log");
        LOGGER.warn("Hey, This is a warning!");
        LOGGER.error("Oops! We have an Error. OK");
        LOGGER.fatal("Damn! Fatal error. Please fix me.");
        LOGGER.info("start logger");
    }

}


