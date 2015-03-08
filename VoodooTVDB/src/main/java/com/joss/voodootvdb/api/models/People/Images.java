
package com.joss.voodootvdb.api.models.People;

import com.google.gson.annotations.Expose;

public class Images {

    @Expose
    private Headshot headshot;

    /**
     * 
     * @return
     *     The headshot
     */
    public Headshot getHeadshot() {
        return headshot == null ? new Headshot() : headshot;
    }

    /**
     * 
     * @param headshot
     *     The headshot
     */
    public void setHeadshot(Headshot headshot) {
        this.headshot = headshot;
    }

}
