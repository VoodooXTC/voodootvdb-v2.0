package com.joss.voodootvdb.api.models.Season;

import com.google.gson.annotations.Expose;
import com.joss.voodootvdb.api.models.Show.Ids;
import com.joss.voodootvdb.api.models.Show.Images;

/**
 * Created by Jossay
 * Date: 3/11/2015
 * Time: 10:56 PM
 */
public class Season {
    @Expose
    private Integer number;
    @Expose
    private Ids ids;
    @Expose
    private Double rating;
    @Expose
    private Integer votes;
    @Expose
    private Integer episode_count;
    @Expose
    private Integer aired_episodes;
    @Expose
    private String overview;
    @Expose
    private Images images;

    public Integer getNumber() {
        return number == null ? 0 : number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Ids getIds() {
        return ids == null ? new Ids() : ids;
    }

    public void setIds(Ids ids) {
        this.ids = ids;
    }

    public Double getRating() {
        return rating == null ? 0 : rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getVotes() {
        return votes == null ? 0 : votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public Integer getEpisode_count() {
        return episode_count == null ? 0 : episode_count;
    }

    public void setEpisode_count(Integer episode_count) {
        this.episode_count = episode_count;
    }

    public Integer getAired_episodes() {
        return aired_episodes == null ? 0 : aired_episodes;
    }

    public void setAired_episodes(Integer aired_episodes) {
        this.aired_episodes = aired_episodes;
    }

    public String getOverview() {
        return overview == null ? "" : overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Images getImages() {
        return images == null ? new Images() : images;
    }

    public void setImages(Images images) {
        this.images = images;
    }
}
