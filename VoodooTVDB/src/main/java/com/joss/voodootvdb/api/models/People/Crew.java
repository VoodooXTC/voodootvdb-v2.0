
package com.joss.voodootvdb.api.models.People;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Crew {

    @Expose
    private List<Art> art = new ArrayList<Art>();
    @Expose
    private List<Sound> sound = new ArrayList<Sound>();
    @Expose
    private List<Writing> writing = new ArrayList<Writing>();
    @SerializedName("costume & make-up")
    @Expose
    private List<CostumeMakeUp> costumeMakeUp = new ArrayList<CostumeMakeUp>();
    @Expose
    private List<Production> production = new ArrayList<Production>();

    /**
     * 
     * @return
     *     The art
     */
    public List<Art> getArt() {
        return art == null ? new ArrayList<Art>() : art;
    }

    /**
     * 
     * @param art
     *     The art
     */
    public void setArt(List<Art> art) {
        this.art = art;
    }

    /**
     * 
     * @return
     *     The sound
     */
    public List<Sound> getSound() {
        return sound == null ? new ArrayList<Sound>() : sound;
    }

    /**
     * 
     * @param sound
     *     The sound
     */
    public void setSound(List<Sound> sound) {
        this.sound = sound;
    }

    /**
     * 
     * @return
     *     The writing
     */
    public List<Writing> getWriting() {
        return writing == null ? new ArrayList<Writing>() : writing;
    }

    /**
     * 
     * @param writing
     *     The writing
     */
    public void setWriting(List<Writing> writing) {
        this.writing = writing;
    }

    /**
     * 
     * @return
     *     The costumeMakeUp
     */
    public List<CostumeMakeUp> getCostumeMakeUp() {
        return costumeMakeUp == null ? new ArrayList<CostumeMakeUp>() : costumeMakeUp;
    }

    /**
     * 
     * @param costumeMakeUp
     *     The costume & make-up
     */
    public void setCostumeMakeUp(List<CostumeMakeUp> costumeMakeUp) {
        this.costumeMakeUp = costumeMakeUp;
    }

    /**
     * 
     * @return
     *     The production
     */
    public List<Production> getProduction() {
        return production == null ? new ArrayList<Production>() : production;
    }

    /**
     * 
     * @param production
     *     The production
     */
    public void setProduction(List<Production> production) {
        this.production = production;
    }

}
