package org.openidentityplatform.uaparser.repository;

import net.sf.uadetector.ReadableUserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DummyRepository implements UserAgentRepository {
    private final static Logger logger = LoggerFactory.getLogger(DummyRepository.class);

    public void storeUserAgent(ReadableUserAgent readableUserAgent) {
        logger.info("stored: {}", readableUserAgent);
    }
}
