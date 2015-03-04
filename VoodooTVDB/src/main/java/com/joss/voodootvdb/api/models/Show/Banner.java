
package com.joss.voodootvdb.api.models.Show;

import com.google.gson.annotations.Expose;

public class Banner {

    @Expose
    private String full;

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

}
