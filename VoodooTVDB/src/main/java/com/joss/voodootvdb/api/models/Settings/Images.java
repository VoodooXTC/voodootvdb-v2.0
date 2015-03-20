
package com.joss.voodootvdb.api.models.Settings;

import com.google.gson.annotations.Expose;

public class Images {

    @Expose
    private Avatar avatar;

    /**
     * 
     * @return
     *     The avatar
     */
    public Avatar getAvatar() {
        return avatar == null ? new Avatar() : avatar;
    }

    /**
     * 
     * @param avatar
     *     The avatar
     */
    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

}
