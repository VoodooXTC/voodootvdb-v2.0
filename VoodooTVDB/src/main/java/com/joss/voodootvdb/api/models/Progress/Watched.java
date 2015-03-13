package com.joss.voodootvdb.api.models.Progress;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: jossayjacobo
 * Date: 3/13/15
 * Time: 11:37 AM
 */
public class Watched {

    @Expose
    private Integer aired;
    @Expose
    private Integer completed;
    @Expose
    private List<Season> seasons = new ArrayList<>();

    public Integer getAired() {
        return aired == null ? 0 : aired;
    }

    public void setAired(Integer aired) {
        this.aired = aired;
    }

    public Integer getCompleted() {
        return completed == null ? 0 : completed;
    }

    public void setCompleted(Integer completed) {
        this.completed = completed;
    }

    public List<Season> getSeasons() {
        return seasons == null ? new ArrayList<Season>() : seasons;
    }

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }
}
