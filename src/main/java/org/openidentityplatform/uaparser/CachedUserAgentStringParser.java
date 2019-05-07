/*
 * The contents of this file are subject to the terms of the Common Development and
 * Distribution License (the License). You may not use this file except in compliance with the
 * License.
 *
 * You can obtain a copy of the License at legal/CDDLv1.0.txt. See the License for the
 * specific language governing permission and limitations under the License.
 *
 * When distributing Covered Software, include this CDDL Header Notice in each file and include
 * the License file at legal/CDDLv1.0.txt. If applicable, add the following below the CDDL
 * Header, with the fields enclosed by brackets [] replaced by your own identifying
 * information: "Portions copyright [year] [name of copyright owner]".
 *
 * Copyright 2019 3A-Systems LLC
 */

package org.openidentityplatform.uaparser;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.service.UADetectorServiceFactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class CachedUserAgentStringParser {

    final static Logger logger = LoggerFactory.getLogger(CachedUserAgentStringParser.class.getName());

    private static final UserAgentStringParser parser = UADetectorServiceFactory.getResourceModuleParser();

    private static final Cache<String, ReadableUserAgent> cache = CacheBuilder.newBuilder()
            .maximumSize(8192)
            .expireAfterAccess(20, TimeUnit.MINUTES)
            .build();

    private static final Cache<String, Boolean> cacheNotFound = CacheBuilder.newBuilder()
            .maximumSize(8192)
            .expireAfterAccess(20, TimeUnit.MINUTES)
            .build();

    private static final ScheduledExecutorService cleanup = Executors.newScheduledThreadPool(1);

    static {
        cleanup.schedule(() -> {
            cache.cleanUp();
            cacheNotFound.cleanUp();
        }, 20, TimeUnit.MINUTES);
    }

    public ReadableUserAgent parse(final String userAgentString) {
        ReadableUserAgent result = null;
        if (StringUtils.isEmpty(userAgentString)||cacheNotFound.getIfPresent(userAgentString)!=null)
            return null;

        result=cache.getIfPresent(userAgentString);
        if (result == null) {
            result = parser.parse(userAgentString);
            if (result!=null)
                cache.put(userAgentString, result);
            else
                cacheNotFound.put(userAgentString, true);
        }
        if (logger.isDebugEnabled())
            logger.debug("{}: {}",toString(result),result);
        return result;
    }

    public String toString(ReadableUserAgent ua){
        return MessageFormat.format("{0};{1};{2};{3};{4}",
                ua.getDeviceCategory().getCategory().name(),
                ua.getOperatingSystem().getFamily().name(),
                ua.getOperatingSystem().getName(),
                ua.getName(),ua.getVersionNumber().toVersionString());
    }
}