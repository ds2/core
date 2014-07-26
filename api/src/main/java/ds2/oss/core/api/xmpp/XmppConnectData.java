/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.api.xmpp;

/**
 *
 * @author dstrauss
 */
public class XmppConnectData implements IXmppConnectionData {

    private boolean rosterLoadedAtLogin = false;
    private boolean sendPresence = false;
    private String password;
    private String username;
    private int serverPort = 5223;
    private String serverHostname = "localhost";
    private boolean ignoreSslTrustErrors = false;

    public void setIgnoreSslTrustErrors(boolean ignoreSslTrustErrors) {
        this.ignoreSslTrustErrors = ignoreSslTrustErrors;
    }

    public void setRosterLoadedAtLogin(boolean rosterLoadedAtLogin) {
        this.rosterLoadedAtLogin = rosterLoadedAtLogin;
    }

    public void setSendPresence(boolean sendPresence) {
        this.sendPresence = sendPresence;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public void setServerHostname(String serverHostname) {
        this.serverHostname = serverHostname;
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
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isSendPresence() {
        return sendPresence;
    }

    @Override
    public boolean isRosterLoadedAtLogin() {
        return rosterLoadedAtLogin;
    }

    @Override
    public String toString() {
        return "XmppConnectData{" + "rosterLoadedAtLogin=" + rosterLoadedAtLogin + ", sendPresence=" + sendPresence + ", password=" + "***" + ", username=" + username + ", serverPort=" + serverPort + ", serverHostname=" + serverHostname + '}';
    }

    @Override
    public boolean isIgnoreSslTrustErrors() {
        return ignoreSslTrustErrors;
    }

}
