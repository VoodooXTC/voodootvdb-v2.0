
package com.joss.voodootvdb.api.models.People;

import com.google.gson.annotations.Expose;

public class Person {

    @Expose
    private String name;
    @Expose
    private Ids ids;
    @Expose
    private Images images;
    @Expose
    private String biography;
    @Expose
    private String birthday;
    @Expose
    private String death;
    @Expose
    private String birthplace;
    @Expose
    private String homepage;

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name == null ? "" : name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
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
     *     The images
     */
    public Images getImages() {
        return images  == null ? new Images() : images;
    }

    /**
     * 
     * @param images
     *     The images
     */
    public void setImages(Images images) {
        this.images = images;
    }

    /**
     * 
     * @return
     *     The biography
     */
    public String getBiography() {
        return biography == null ? "" : biography;
    }

    /**
     * 
     * @param biography
     *     The biography
     */
    public void setBiography(String biography) {
        this.biography = biography;
    }

    /**
     * 
     * @return
     *     The birthday
     */
    public String getBirthday() {
        return birthday == null ? "" : birthday;
    }

    /**
     * 
     * @param birthday
     *     The birthday
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * 
     * @return
     *     The death
     */
    public String getDeath() {
        return death  == null ? "" : death;
    }

    /**
     * 
     * @param death
     *     The death
     */
    public void setDeath(String death) {
        this.death = death;
    }

    /**
     * 
     * @return
     *     The birthplace
     */
    public String getBirthplace() {
        return birthplace  == null ? "" : birthplace;
    }

    /**
     * 
     * @param birthplace
     *     The birthplace
     */
    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
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

}
