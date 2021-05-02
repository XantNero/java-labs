package com.web.restservice;

import java.util.concurrent.Semaphore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
class CounterService{
    Semaphore semaphore = new Semaphore(1);
    private int counter = 0;
    private Logger logger = LoggerFactory.getLogger(getClass());

    public int getCounter() throws ResponseStatusException{
        try {
            semaphore.acquire();
            logger.info("Return counter");
            return counter;
        } catch (InterruptedException e) {
			logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        } finally {
            semaphore.release();
        }
    }

    public void incrementCounter() {
        try {
            semaphore.acquire();
            ++counter;
            logger.info("Increments counter to " + String.valueOf(counter));
        } catch (InterruptedException e) {
			logger.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        } finally {
            semaphore.release();
        }
    }
}