package com.joss.voodootvdb.api.models.Progress;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: jossayjacobo
 * Date: 3/13/15
 * Time: 1:01 PM
 */
public class Season {

    @Expose
    private Integer number;
    @Expose
    private Integer aired;
    @Expose
    private Integer completed;
    @Expose
    private List<Episode> episodes = new ArrayList<>();

    public Integer getNumber() {
        return number == null ? 0 : number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

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

    public List<Episode> getEpisodes() {
        return episodes == null ? new ArrayList<Episode>() : episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }
}
