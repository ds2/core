/*
 * Copyright 2020 DS/2 <dstrauss@ds-2.de>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package ds2.oss.core.base.impl;

import ds2.oss.core.api.InetAddressService;
import jakarta.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author dstrauss
 */
@ApplicationScoped
public class InetAddressServiceImpl implements InetAddressService {
    private static final Logger LOG = LoggerFactory.getLogger(InetAddressServiceImpl.class);

    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.InetAddressService#getByName(java.lang.String)
     */
    @Override
    public InetAddress getByName(String name) {
        try {
            return InetAddress.getByName(name);
        } catch (UnknownHostException e) {
            LOG.debug("Error when getting the ip address for " + name, e);
        }
        return null;
    }

}
