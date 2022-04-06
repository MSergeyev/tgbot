package ru.demoprojects.tgbot.llogger;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.demoprojects.tgbot.controller.CurrencyController;


@Slf4j
public class StepsLog {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyController.class);


    public static void main(String[] args) {
        LOGGER.debug("Debug log message");
        LOGGER.info("Info log message");
        LOGGER.error("Error log message");


    }
}


