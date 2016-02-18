/*
 * Copyright 2012-2015 Dirk Strauss
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ds2.oss.core.statics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Some direct help methods, ready to use.
 *
 * @author dstrauss
 * @version 0.3
 */
public final class Tools {

    /**
     * A logger.
     */
    private static final transient Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * To convert a given string into an inet address.
     *
     * @param s
     *            the host name or ip address
     * @return the inet address, or null if an error occurred
     */
    public static InetAddress toInetAddress(final String s) {
        InetAddress rc = null;
        try {
            rc = InetAddress.getByName(s);
        } catch (UnknownHostException e) {
            LOG.debug("Unknown host: " + s, e);
        }
        return rc;
    }



    /**
     * May create an instance of the class.
     */
    private Tools() {
        // nothing special to do
    }
}
