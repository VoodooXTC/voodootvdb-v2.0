
package com.joss.voodootvdb.api.models.Show;

import com.google.gson.annotations.Expose;

public class Poster {

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
