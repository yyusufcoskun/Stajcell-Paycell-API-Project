package com.StajcellProject.Stajcell.API.Project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingDemo {
    private static final Logger logger = LoggerFactory.getLogger(LoggingDemo.class);

    public void doSomething() {
        logger.info("This is an info log.");
        logger.error("This is an error log.");
        

    }
}
