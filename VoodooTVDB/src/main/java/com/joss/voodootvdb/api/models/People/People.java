
package com.joss.voodootvdb.api.models.People;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;

public class People {

    @Expose
    private List<Cast> cast = new ArrayList<Cast>();
    @Expose
    private Crew crew;

    /**
     * 
     * @return
     *     The cast
     */
    public List<Cast> getCast() {
        return cast == null ? new ArrayList<Cast>() : cast;
    }

    /**
     * 
     * @param cast
     *     The cast
     */
    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }

    /**
     * 
     * @return
     *     The crew
     */
    public Crew getCrew() {
        return crew == null ? new Crew() : crew;
    }

    /**
     * 
     * @param crew
     *     The crew
     */
    public void setCrew(Crew crew) {
        this.crew = crew;
    }

}
