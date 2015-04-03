package com.joss.voodootvdb.api.models.Movie;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.joss.voodootvdb.api.models.Show.Ids;
import com.joss.voodootvdb.api.models.Show.Images;
import com.joss.voodootvdb.interfaces.VoodooItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jossay
 * Date: 3/8/2015
 * Time: 3:20 PM
 */
public class Movie implements VoodooItem {

    @Expose
    private String title;
    @Expose
    private Integer year;
    @Expose
    private Ids ids;
    @Expose
    private String tagline;
    @Expose
    private String overview;
    @Expose
    private String released;
    @Expose
    private Integer runtime;
    @Expose
    private String trailer;
    @Expose
    private String homepage;
    @Expose
    private Double rating;
    @Expose
    private Integer votes;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @Expose
    private String language;
    @SerializedName("available_translations")
    @Expose
    private List<String> availableTranslations = new ArrayList<String>();
    @Expose
    private List<String> genres = new ArrayList<String>();
    @Expose
    private String certification;
    @Expose
    private Images images;

    private int type;
    private String sectionTitle;

    @Override
    public int getType() {
        return type;
    }

    @Override
    public int getId() {
        return getIds().getTrakt();
    }

    @Override
    public String getSectionTitle() {
        return sectionTitle == null ? "" : sectionTitle;
    }

    public void setType(int type){
        this.type = type;
    }

    public void setSectionTitle(String title){
        this.sectionTitle = title;
    }


    public String getTitle(){
        return title == null ? "" : title;
    }

    public int getYear(){
        return year == null ? 0 : year;
    }

    public Ids getIds(){
        return ids == null ? new Ids() : ids;
    }

    public Movie setTraktId(int traktId){
        this.ids = getIds().setTrakt(traktId);
        return this;
    }

    public String getTagline(){
        return tagline == null ? "" : tagline;
    }

    public String getOverview(){
        return overview == null ? "" : overview;
    }

    public String getReleased(){
        return released == null ? "" : released;
    }

    public int getRuntime(){
        return runtime == null ? 0 : runtime;
    }

    public String getTrailer(){
        return trailer == null ? "" : trailer;
    }

    public String getHomepage(){
        return homepage == null ? "" : homepage;
    }

    public double getRating(){
        return rating == null ? 0 : rating;
    }

    public int getVotes(){
        return votes == null ? 0 : votes;
    }

    public String getUpdatedAt(){
        return updatedAt == null ? "" : updatedAt;
    }

    public String getLanguage(){
        return language == null ? "" : language;
    }

    public List<String> getAvailableTranslations(){
        return availableTranslations == null ? new ArrayList<String>() : availableTranslations;
    }

    public List<String> getGenres(){
        return genres == null ? new ArrayList<String>() : genres;
    }

    public String getCertification(){
        return certification == null ? "" : certification;
    }

    public Images getImages(){
        return images == null ? new Images() : images;
    }

}
