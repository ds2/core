/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.xmpp.impl;

import ds2.oss.core.api.xmpp.IPacketIdProvider;
import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

/**
 *
 * @author dstrauss
 * @version 0.3
 */
@ApplicationScoped
@Alternative
public class UuidNumberProvider implements IPacketIdProvider {

    @Override
    public String getNextId() {
        return UUID.randomUUID().toString();
    }

}
