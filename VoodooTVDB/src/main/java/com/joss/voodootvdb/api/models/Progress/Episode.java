package com.joss.voodootvdb.api.models.Progress;

import com.google.gson.annotations.Expose;

/**
 * Created by: jossayjacobo
 * Date: 3/13/15
 * Time: 1:06 PM
 */
public class Episode {

    @Expose
    private Integer number;
    @Expose
    private Boolean completed;

    public Integer getNumber() {
        return number == null ? 0 : number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Boolean getCompleted() {
        return completed == null ? false : completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
