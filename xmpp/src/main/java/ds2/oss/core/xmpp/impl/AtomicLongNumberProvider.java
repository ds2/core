/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.xmpp.impl;

import ds2.oss.core.api.xmpp.IPacketIdProvider;
import java.util.concurrent.atomic.AtomicLong;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

/**
 *
 * @author dstrauss
 */
@ApplicationScoped
@Alternative
public class AtomicLongNumberProvider implements IPacketIdProvider {

    private AtomicLong l;

    public AtomicLongNumberProvider() {
        l = new AtomicLong(0L);
    }

    @Override
    public String getNextId() {
        return "" + l.incrementAndGet();
    }

}
