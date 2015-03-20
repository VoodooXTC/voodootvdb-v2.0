
package com.joss.voodootvdb.api.models.Settings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Settings {

    @Expose
    private User user;
    @Expose
    private Account account;
    @Expose
    private Connections connections;
    @SerializedName("sharing_text")
    @Expose
    private SharingText sharingText;

    /**
     * 
     * @return
     *     The user
     */
    public User getUser() {
        return user == null ? new User() : user;
    }

    /**
     * 
     * @param user
     *     The user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * 
     * @return
     *     The account
     */
    public Account getAccount() {
        return account == null ? new Account() : account;
    }

    /**
     * 
     * @param account
     *     The account
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * 
     * @return
     *     The connections
     */
    public Connections getConnections() {
        return connections == null ? new Connections() : connections;
    }

    /**
     * 
     * @param connections
     *     The connections
     */
    public void setConnections(Connections connections) {
        this.connections = connections;
    }

    /**
     * 
     * @return
     *     The sharingText
     */
    public SharingText getSharingText() {
        return sharingText == null ? new SharingText() : sharingText;
    }

    /**
     * 
     * @param sharingText
     *     The sharing_text
     */
    public void setSharingText(SharingText sharingText) {
        this.sharingText = sharingText;
    }

}
