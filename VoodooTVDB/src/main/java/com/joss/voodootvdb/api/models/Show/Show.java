
package com.joss.voodootvdb.api.models.Show;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.joss.voodootvdb.interfaces.VoodooItem;

public class Show implements VoodooItem {

    @Expose
    private String title;
    @Expose
    private Integer year;
    @Expose
    private Ids ids;
    @Expose
    private String overview;
    @SerializedName("first_aired")
    @Expose
    private String firstAired;
    @Expose
    private Airs airs;
    @Expose
    private Integer runtime;
    @Expose
    private String certification;
    @Expose
    private String network;
    @Expose
    private String country;
    @Expose
    private String trailer;
    @Expose
    private String homepage;
    @Expose
    private String status;
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
    @SerializedName("aired_episodes")
    @Expose
    private Integer airedEpisodes;
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

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title == null ? "" : title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The year
     */
    public Integer getYear() {
        return year == null ? 0 : year;
    }

    /**
     * 
     * @param year
     *     The year
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * 
     * @return
     *     The ids
     */
    public Ids getIds() {
        return ids == null ? new Ids() : ids;
    }

    /**
     * 
     * @param ids
     *     The ids
     */
    public void setIds(Ids ids) {
        this.ids = ids;
    }

    /**
     * 
     * @return
     *     The overview
     */
    public String getOverview() {
        return overview == null ? "" : overview;
    }

    /**
     * 
     * @param overview
     *     The overview
     */
    public void setOverview(String overview) {
        this.overview = overview;
    }

    /**
     * 
     * @return
     *     The firstAired
     */
    public String getFirstAired() {
        return firstAired == null ? "" : firstAired;
    }

    /**
     * 
     * @param firstAired
     *     The first_aired
     */
    public void setFirstAired(String firstAired) {
        this.firstAired = firstAired;
    }

    /**
     * 
     * @return
     *     The airs
     */
    public Airs getAirs() {
        return airs == null ? new Airs() : airs;
    }

    /**
     * 
     * @param airs
     *     The airs
     */
    public void setAirs(Airs airs) {
        this.airs = airs;
    }

    /**
     * 
     * @return
     *     The runtime
     */
    public Integer getRuntime() {
        return runtime == null ? 0 : runtime;
    }

    /**
     * 
     * @param runtime
     *     The runtime
     */
    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    /**
     * 
     * @return
     *     The certification
     */
    public String getCertification() {
        return certification == null ? "" : certification;
    }

    /**
     * 
     * @param certification
     *     The certification
     */
    public void setCertification(String certification) {
        this.certification = certification;
    }

    /**
     * 
     * @return
     *     The network
     */
    public String getNetwork() {
        return network == null ? "" : network;
    }

    /**
     * 
     * @param network
     *     The network
     */
    public void setNetwork(String network) {
        this.network = network;
    }

    /**
     * 
     * @return
     *     The country
     */
    public String getCountry() {
        return country == null ? "" : country;
    }

    /**
     * 
     * @param country
     *     The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 
     * @return
     *     The trailer
     */
    public String getTrailer() {
        return trailer == null ? "" : trailer;
    }

    /**
     * 
     * @param trailer
     *     The trailer
     */
    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    /**
     * 
     * @return
     *     The homepage
     */
    public String getHomepage() {
        return homepage == null ? "" : homepage;
    }

    /**
     * 
     * @param homepage
     *     The homepage
     */
    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    /**
     * 
     * @return
     *     The status
     */
    public String getStatus() {
        return status == null ? "" : status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The rating
     */
    public Double getRating() {
        return rating == null ? 0 : rating;
    }

    /**
     * 
     * @param rating
     *     The rating
     */
    public void setRating(Double rating) {
        this.rating = rating;
    }

    /**
     * 
     * @return
     *     The votes
     */
    public Integer getVotes() {
        return votes == null ? 0 : votes;
    }

    /**
     * 
     * @param votes
     *     The votes
     */
    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    /**
     * 
     * @return
     *     The updatedAt
     */
    public String getUpdatedAt() {
        return updatedAt == null ? "" : updatedAt;
    }

    /**
     * 
     * @param updatedAt
     *     The updated_at
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * 
     * @return
     *     The language
     */
    public String getLanguage() {
        return language == null ? "" : language;
    }

    /**
     * 
     * @param language
     *     The language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * 
     * @return
     *     The availableTranslations
     */
    public List<String> getAvailableTranslations() {
        return availableTranslations == null ? new ArrayList<String>() : availableTranslations;
    }

    /**
     * 
     * @param availableTranslations
     *     The available_translations
     */
    public void setAvailableTranslations(List<String> availableTranslations) {
        this.availableTranslations = availableTranslations;
    }

    /**
     * 
     * @return
     *     The genres
     */
    public List<String> getGenres() {
        return genres== null ? new ArrayList<String>() : genres;
    }

    /**
     * 
     * @param genres
     *     The genres
     */
    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    /**
     * 
     * @return
     *     The airedEpisodes
     */
    public Integer getAiredEpisodes() {
        return airedEpisodes == null ? 0 : airedEpisodes;
    }

    /**
     * 
     * @param airedEpisodes
     *     The aired_episodes
     */
    public void setAiredEpisodes(Integer airedEpisodes) {
        this.airedEpisodes = airedEpisodes;
    }

    /**
     * 
     * @return
     *     The images
     */
    public Images getImages() {
        return images == null ? new Images() : images;
    }

    /**
     * 
     * @param images
     *     The images
     */
    public void setImages(Images images) {
        this.images = images;
    }

}
