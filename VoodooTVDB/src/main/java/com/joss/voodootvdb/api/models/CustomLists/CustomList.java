package com.joss.voodootvdb.api.models.CustomLists;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.joss.voodootvdb.api.models.Show.Ids;

/**
 * Created by: jossayjacobo
 * Date: 3/20/15
 * Time: 11:49 AM
 */
public class CustomList {

    public static final int WATCHLIST_ID = -1;

    @Expose
    private String name;
    @Expose
    private String description;
    @Expose
    private String privacy;
    @Expose
    @SerializedName("display_numbers")
    private Boolean displayNumbers;
    @Expose
    @SerializedName("allow_comments")
    private Boolean allowComments;
    @Expose
    @SerializedName("updated_at")
    private String updatedAt;
    @Expose
    @SerializedName("item_count")
    private Integer itemCount;
    @Expose
    private Integer likes;
    @Expose
    private Ids ids;

    public String getName() {
        return name == null ? "" : name;
    }

    public CustomList setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description == null ? "" : description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDisplayNumbers() {
        return displayNumbers == null ? false : displayNumbers;
    }

    public void setDisplayNumbers(Boolean displayNumbers) {
        this.displayNumbers = displayNumbers;
    }

    public Boolean getAllowComments() {
        return allowComments == null ? false : allowComments;
    }

    public void setAllowComments(Boolean allowComments) {
        this.allowComments = allowComments;
    }

    public String getUpdatedAt() {
        return updatedAt == null ? "" : updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getItemCount() {
        return itemCount == null ? 0 : itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public Integer getLikes() {
        return likes == null ? 0 : likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Ids getIds() {
        return ids == null ? new Ids() : ids;
    }

    public void setIds(Ids ids) {
        this.ids = ids;
    }

    public CustomList setTraktId(int traktId){
        this.ids = getIds().setTrakt(traktId);
        return this;
    }
}
