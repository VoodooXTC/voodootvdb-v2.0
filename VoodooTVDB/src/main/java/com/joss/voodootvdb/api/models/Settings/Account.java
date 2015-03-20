
package com.joss.voodootvdb.api.models.Settings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Account {

    @Expose
    private String timezone;
    @SerializedName("time_24hr")
    @Expose
    private Boolean time24hr;
    @SerializedName("cover_image")
    @Expose
    private String coverImage;
    @Expose
    private String token;

    /**
     * 
     * @return
     *     The timezone
     */
    public String getTimezone() {
        return timezone == null ? "" : timezone;
    }

    /**
     * 
     * @param timezone
     *     The timezone
     */
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    /**
     * 
     * @return
     *     The time24hr
     */
    public Boolean getTime24hr() {
        return time24hr == null ? false : time24hr;
    }

    /**
     * 
     * @param time24hr
     *     The time_24hr
     */
    public void setTime24hr(Boolean time24hr) {
        this.time24hr = time24hr;
    }

    /**
     * 
     * @return
     *     The coverImage
     */
    public String getCoverImage() {
        return coverImage == null ? "" : coverImage;
    }

    /**
     * 
     * @param coverImage
     *     The cover_image
     */
    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    /**
     * 
     * @return
     *     The token
     */
    public String getToken() {
        return token == null ? "" : token;
    }

    /**
     * 
     * @param token
     *     The token
     */
    public void setToken(String token) {
        this.token = token;
    }


}
