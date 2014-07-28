/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.api.xmpp;

/**
 * A setting to connect to an xmpp server only if some security is available.
 *
 * @author dstrauss
 * @version 0.3
 */
public enum RequireSecurity {

    no, optional, required;
}
