/*
 * Copyright 2019 DS/2 <dstrauss@ds-2.de>
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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.api.xmpp;

/**
 * The dto implementation from the config contract.
 *
 * @author dstrauss
 * @version 0.3
 */
public class XmppConnectData implements IXmppConnectionData {

    /**
     * The svuid.
     */
    private static final long serialVersionUID = 2352803105230195555L;
    /**
     * Whether to debug this connection.
     */
    private boolean debuggerEnabled;
    /**
     * whether to ignore strange ssl certificates, or crash.
     */
    private boolean ignoreSslTrustErrors;
    /**
     * The user password.
     */
    private String password;
    /**
     * load roster at login.
     */
    private boolean rosterLoadedAtLogin;
    /**
     * The expected security level.
     */
    private RequireSecurity securityLevel = RequireSecurity.optional;
    /**
     * Send presence.
     */
    private boolean sendPresence;
    /**
     * The server host name to connect to.
     */
    private String serverHostname = "localhost";
    /**
     * the server port to connect to.
     */
    private int serverPort = 5223;
    /**
     * the username, or user jid.
     */
    private String username;

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public RequireSecurity getSecurityLevel() {
        return securityLevel;
    }

    @Override
    public String getServerHostname() {
        return serverHostname;
    }

    @Override
    public int getServerPort() {
        return serverPort;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isDebuggerEnabled() {
        return debuggerEnabled;
    }

    @Override
    public boolean isIgnoreSslTrustErrors() {
        return ignoreSslTrustErrors;
    }

    @Override
    public boolean isRosterLoadedAtLogin() {
        return rosterLoadedAtLogin;
    }

    @Override
    public boolean isSendPresence() {
        return sendPresence;
    }

    public void setDebuggerEnabled(final boolean debuggerEnabled) {
        this.debuggerEnabled = debuggerEnabled;
    }

    public void setIgnoreSslTrustErrors(final boolean ignoreSslTrustErrors) {
        this.ignoreSslTrustErrors = ignoreSslTrustErrors;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setRosterLoadedAtLogin(final boolean rosterLoadedAtLogin) {
        this.rosterLoadedAtLogin = rosterLoadedAtLogin;
    }

    public void setSecurityLevel(final RequireSecurity securityLevel) {
        this.securityLevel = securityLevel;
    }

    public void setSendPresence(final boolean sendPresence) {
        this.sendPresence = sendPresence;
    }

    public void setServerHostname(final String serverHostname) {
        this.serverHostname = serverHostname;
    }

    public void setServerPort(final int serverPort) {
        this.serverPort = serverPort;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "XmppConnectData{" + "rosterLoadedAtLogin=" + rosterLoadedAtLogin + ", sendPresence=" + sendPresence
            + ", password=" + "***" + ", username=" + username + ", serverPort=" + serverPort + ", serverHostname="
            + serverHostname + '}';
    }

}
