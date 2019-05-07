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

package org.openidentityplatform.uaparser.controllers;

import net.sf.uadetector.ReadableUserAgent;
import org.apache.commons.lang3.StringUtils;
import org.openidentityplatform.uaparser.CachedUserAgentStringParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAgentParserRestController {

    private CachedUserAgentStringParser cachedUserAgentStringParser;

    public UserAgentParserRestController(CachedUserAgentStringParser cachedUserAgentStringParser) {
        this.cachedUserAgentStringParser = cachedUserAgentStringParser;
    }

    @GetMapping("/")
    public ReadableUserAgent parse(@RequestParam(name = "user-agent", required = false) String userAgentParameter, @RequestHeader(name = "User-Agent") String userAgentHeader) {
        return cachedUserAgentStringParser.parse(StringUtils.isNotBlank(userAgentParameter) ? userAgentParameter : userAgentHeader);
    }

}
