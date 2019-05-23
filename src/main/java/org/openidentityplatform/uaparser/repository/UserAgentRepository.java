package org.openidentityplatform.uaparser.repository;

import net.sf.uadetector.ReadableUserAgent;

public interface UserAgentRepository {
    void storeUserAgent(ReadableUserAgent readableUserAgent);
}
