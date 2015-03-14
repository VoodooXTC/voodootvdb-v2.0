package com.joss.voodootvdb.api.models.Episode;

import com.google.gson.annotations.Expose;

/**
 * Created by Jossay
 * Date: 3/14/2015
 * Time: 3:20 PM
 */
public class Screenshot {
    @Expose
    private String full;
    @Expose
    private String medium;
    @Expose
    private String thumb;

    /**
     *
     * @return
     *     The full
     */
    public String getFull() {
        return full == null ? "" : full;
    }

    /**
     *
     * @param full
     *     The full
     */
    public void setFull(String full) {
        this.full = full;
    }

    /**
     *
     * @return
     *     The medium
     */
    public String getMedium() {
        return medium == null ? "" : medium;
    }

    /**
     *
     * @param medium
     *     The medium
     */
    public void setMedium(String medium) {
        this.medium = medium;
    }

    /**
     *
     * @return
     *     The thumb
     */
    public String getThumb() {
        return thumb == null ? "" : thumb;
    }

    /**
     *
     * @param thumb
     *     The thumb
     */
    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}
