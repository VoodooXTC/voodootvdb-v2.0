
package com.joss.voodootvdb.api.models.Settings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @Expose
    private String username;
    @SerializedName("private")
    @Expose
    private Boolean isPrivate;
    @Expose
    private String name;
    @Expose
    private Boolean vip;
    @SerializedName("joined_at")
    @Expose
    private String joinedAt;
    @Expose
    private String location;
    @Expose
    private String about;
    @Expose
    private String gender;
    @Expose
    private Long age;
    @Expose
    private Images images;

    /**
     * 
     * @return
     *     The username
     */
    public String getUsername() {
        return username == null ? "" : username;
    }

    /**
     * 
     * @param username
     *     The username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 
     * @return
     *     The _private
     */
    public Boolean getPrivate() {
        return isPrivate == null ? false : isPrivate;
    }

    /**
     * 
     * @param _private
     *     The private
     */
    public void setPrivate(Boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

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
     *     The vip
     */
    public Boolean getVip() {
        return vip == null ? false : vip;
    }

    /**
     * 
     * @param vip
     *     The vip
     */
    public void setVip(Boolean vip) {
        this.vip = vip;
    }

    /**
     * 
     * @return
     *     The joinedAt
     */
    public String getJoinedAt() {
        return joinedAt == null ? "" : joinedAt;
    }

    /**
     * 
     * @param joinedAt
     *     The joined_at
     */
    public void setJoinedAt(String joinedAt) {
        this.joinedAt = joinedAt;
    }

    /**
     * 
     * @return
     *     The location
     */
    public String getLocation() {
        return location == null ? "" : location;
    }

    /**
     * 
     * @param location
     *     The location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * 
     * @return
     *     The about
     */
    public String getAbout() {
        return about == null ? "" : about;
    }

    /**
     * 
     * @param about
     *     The about
     */
    public void setAbout(String about) {
        this.about = about;
    }

    /**
     * 
     * @return
     *     The gender
     */
    public String getGender() {
        return gender == null ? "" : gender;
    }

    /**
     * 
     * @param gender
     *     The gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * 
     * @return
     *     The age
     */
    public Long getAge() {
        return age == null ? 0 : age;
    }

    /**
     * 
     * @param age
     *     The age
     */
    public void setAge(Long age) {
        this.age = age;
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
