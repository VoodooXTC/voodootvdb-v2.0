
package com.joss.voodootvdb.api.models.Show;

import com.google.gson.annotations.Expose;

public class Airs {

    @Expose
    private String day;
    @Expose
    private String time;
    @Expose
    private String timezone;

    /**
     * 
     * @return
     *     The day
     */
    public String getDay() {
        return day == null ? "" : day;
    }

    /**
     *
     * @param day
     *     The day
     */
    public void setDay(String day) {
        this.day = day;
    }

    /**
     * 
     * @return
     *     The time
     */
    public String getTime() {
        return time == null ? "" : time;
    }

    /**
     *
     * @param time
     *     The time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * 
     * @return
     *     The timezone
     */
    public String getTimezone() {
        return timezone == null ? "" : timezone;
    }

    /**
     *
     * @param timezone
     *     The timezone
     */
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

}
