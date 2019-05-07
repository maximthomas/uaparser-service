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

import net.sf.uadetector.ReadableUserAgent;
import org.junit.Test;

import static org.junit.Assert.*;

public class CachedUserAgentStringParserTest {

    @Test
    public void parse() {
        CachedUserAgentStringParser parser = new CachedUserAgentStringParser();
        ReadableUserAgent firefox = parser.parse("Mozilla/5.0 (Linux; U; Windows NT 5.1; en-US; rv:1.8.1.11) Gecko/20071127 Firefox/2.0.0.13");
        assertEquals("Firefox", firefox.getName());

        ReadableUserAgent unknown = parser.parse("Unknown");

        assertEquals("unknown", unknown.getName());



    }
}