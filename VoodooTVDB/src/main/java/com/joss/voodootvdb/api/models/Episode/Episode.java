package com.joss.voodootvdb.api.models.Episode;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
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
    @SerializedName("number_abs")
    private Integer numberAbs;
    @Expose
    private String overview;
    @Expose
    private Double rating;
    @Expose
    private Integer votes;
    @Expose
    @SerializedName("first_aired")
    private String firstAired;
    @Expose
    @SerializedName("updated_at")
    private String updatedAt;
    @Expose
    @SerializedName("available_translations")
    private List<String> availableTranslations = new ArrayList<>();
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

    public Episode setTraktId(int traktId){
        this.setIds(getIds().setTrakt(traktId));
        return this;
    }

    public void setIds(Ids ids) {
        this.ids = ids;
    }

    public Integer getNumberAbs() {
        return numberAbs == null ? 0 : numberAbs;
    }

    public void setNumberAbs(Integer numberAbs) {
        this.numberAbs = numberAbs;
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

    public String getFirstAired() {
        return firstAired == null ? "" : firstAired;
    }

    public void setFirstAired(String firstAired) {
        this.firstAired = firstAired;
    }

    public String getUpdatedAt() {
        return updatedAt == null ? "" : updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<String> getAvailableTranslations() {
        return availableTranslations == null ? new ArrayList<String>() : availableTranslations;
    }

    public void setAvailableTranslations(List<String> availableTranslations) {
        this.availableTranslations = availableTranslations;
    }

    public Images getImages() {
        return images == null ? new Images() : images;
    }

    public void setImages(Images images) {
        this.images = images;
    }
}
