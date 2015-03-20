
package com.joss.voodootvdb.api.models.Settings;

import com.google.gson.annotations.Expose;

public class Connections {

    @Expose
    private Boolean facebook;
    @Expose
    private Boolean twitter;
    @Expose
    private Boolean google;
    @Expose
    private Boolean tumblr;

    /**
     * 
     * @return
     *     The facebook
     */
    public Boolean getFacebook() {
        return facebook == null ? false : facebook;
    }

    /**
     * 
     * @param facebook
     *     The facebook
     */
    public void setFacebook(Boolean facebook) {
        this.facebook = facebook;
    }

    /**
     * 
     * @return
     *     The twitter
     */
    public Boolean getTwitter() {
        return twitter == null ? false : twitter;
    }

    /**
     * 
     * @param twitter
     *     The twitter
     */
    public void setTwitter(Boolean twitter) {
        this.twitter = twitter;
    }

    /**
     * 
     * @return
     *     The google
     */
    public Boolean getGoogle() {
        return google == null ? false : google;
    }

    /**
     * 
     * @param google
     *     The google
     */
    public void setGoogle(Boolean google) {
        this.google = google;
    }

    /**
     * 
     * @return
     *     The tumblr
     */
    public Boolean getTumblr() {
        return tumblr == null ? false : tumblr;
    }

    /**
     * 
     * @param tumblr
     *     The tumblr
     */
    public void setTumblr(Boolean tumblr) {
        this.tumblr = tumblr;
    }

}
