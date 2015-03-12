package com.joss.voodootvdb.api.models.Episode;

import com.google.gson.annotations.Expose;
import com.joss.voodootvdb.api.models.Show.Ids;
import com.joss.voodootvdb.api.models.Show.Images;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jossay
 * Date: 3/12/2015
 * Time: 12:07 AM
 */
public class Episode {
    @Expose
    private Integer season;
    @Expose
    private Integer number;
    @Expose
    private String title;
    @Expose
    private Ids ids;
    @Expose
    private Integer number_abs;
    @Expose
    private String overview;
    @Expose
    private Double rating;
    @Expose
    private Integer votes;
    @Expose
    private String first_aired;
    @Expose
    private String updated_at;
    @Expose
    private List<String> available_translations = new ArrayList<>();
    @Expose
    private Images images;

    public Integer getSeason() {
        return season == null ? 0 : season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public Integer getNumber() {
        return number == null ? 0 : number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Ids getIds() {
        return ids == null ? new Ids() : ids;
    }

    public void setIds(Ids ids) {
        this.ids = ids;
    }

    public Integer getNumber_abs() {
        return number_abs == null ? 0 : number_abs;
    }

    public void setNumber_abs(Integer number_abs) {
        this.number_abs = number_abs;
    }

    public String getOverview() {
        return overview == null ? "" : overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
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

    public String getFirst_aired() {
        return first_aired == null ? "" : first_aired;
    }

    public void setFirst_aired(String first_aired) {
        this.first_aired = first_aired;
    }

    public String getUpdated_at() {
        return updated_at == null ? "" : updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public List<String> getAvailable_translations() {
        return available_translations == null ? new ArrayList<String>() : available_translations;
    }

    public void setAvailable_translations(List<String> available_translations) {
        this.available_translations = available_translations;
    }

    public Images getImages() {
        return images == null ? new Images() : images;
    }

    public void setImages(Images images) {
        this.images = images;
    }
}
