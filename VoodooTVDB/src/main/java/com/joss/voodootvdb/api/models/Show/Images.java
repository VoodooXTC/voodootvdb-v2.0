
package com.joss.voodootvdb.api.models.Show;

import com.google.gson.annotations.Expose;

public class Images {

    @Expose
    private Fanart fanart;
    @Expose
    private Poster poster;
    @Expose
    private Logo logo;
    @Expose
    private Clearart clearart;
    @Expose
    private Banner banner;
    @Expose
    private Thumb thumb;

    /**
     * 
     * @return
     *     The fanart
     */
    public Fanart getFanart() {
        return fanart == null ? new Fanart() : fanart;
    }

    /**
     * 
     * @param fanart
     *     The fanart
     */
    public void setFanart(Fanart fanart) {
        this.fanart = fanart;
    }

    /**
     * 
     * @return
     *     The poster
     */
    public Poster getPoster() {
        return poster == null ? new Poster() : poster;
    }

    /**
     * 
     * @param poster
     *     The poster
     */
    public void setPoster(Poster poster) {
        this.poster = poster;
    }

    /**
     * 
     * @return
     *     The logo
     */
    public Logo getLogo() {
        return logo == null ? new Logo() : logo;
    }

    /**
     * 
     * @param logo
     *     The logo
     */
    public void setLogo(Logo logo) {
        this.logo = logo;
    }

    /**
     * 
     * @return
     *     The clearart
     */
    public Clearart getClearart() {
        return clearart == null ? new Clearart() : clearart;
    }

    /**
     * 
     * @param clearart
     *     The clearart
     */
    public void setClearart(Clearart clearart) {
        this.clearart = clearart;
    }

    /**
     * 
     * @return
     *     The banner
     */
    public Banner getBanner() {
        return banner == null ? new Banner() : banner;
    }

    /**
     * 
     * @param banner
     *     The banner
     */
    public void setBanner(Banner banner) {
        this.banner = banner;
    }

    /**
     * 
     * @return
     *     The thumb
     */
    public Thumb getThumb() {
        return thumb == null ? new Thumb() : thumb;
    }

    /**
     * 
     * @param thumb
     *     The thumb
     */
    public void setThumb(Thumb thumb) {
        this.thumb = thumb;
    }

}
