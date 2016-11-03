package com.saferich.core.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractScheduleHandler {

    protected final Logger logger = LoggerFactory.getLogger(AbstractScheduleHandler.class);

    public void handle() throws IllegalAccessException, Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("start schedule[" + getClass() + "].");
        }
        doHandle();
        if (logger.isDebugEnabled()) {
            logger.debug("end schedule[" + getClass() + "].");
        }
    }

    protected abstract void doHandle() throws IllegalAccessException, Exception;
}
