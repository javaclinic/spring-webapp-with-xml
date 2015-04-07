package example.services;

import java.util.Date;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

/**
 * TimeService implementation. It returns the current timestamp, as Date and as String.
 * 
 * @author nevenc
 *
 */
@Service
public class TimeServiceImpl implements TimeService {

    private static final Logger LOGGER = Logger.getLogger(TimeServiceImpl.class.getCanonicalName());

    public TimeServiceImpl() {
        LOGGER.info("Inside " + this.getClass().getSimpleName() + "() constructor method. Instantiating service.");
    }

    @Override
    public Date getDate() {
        LOGGER.info("Inside " + this.getClass().getSimpleName() + ".getDate() business method. Processing business call.");
        return new Date();
    }

    @Override
    public String getTimestamp() {
        LOGGER.info("Inside " + this.getClass().getSimpleName() + ".getTimestamp() business method. Processing business call.");
        return (new Date()).toString();
    }

}
