
package com.joss.voodootvdb.api.models.Settings;

import com.google.gson.annotations.Expose;

public class SharingText {

    @Expose
    private String watching;
    @Expose
    private String watched;

    /**
     * 
     * @return
     *     The watching
     */
    public String getWatching() {
        return watching == null ? "" : watching;
    }

    /**
     * 
     * @param watching
     *     The watching
     */
    public void setWatching(String watching) {
        this.watching = watching;
    }

    /**
     * 
     * @return
     *     The watched
     */
    public String getWatched() {
        return watched == null ? "" : watched;
    }

    /**
     * 
     * @param watched
     *     The watched
     */
    public void setWatched(String watched) {
        this.watched = watched;
    }

}
