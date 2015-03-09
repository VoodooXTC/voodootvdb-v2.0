
package com.joss.voodootvdb.api.models.People;

import com.google.gson.annotations.Expose;
import com.joss.voodootvdb.api.models.Movie.Movie;
import com.joss.voodootvdb.api.models.Show.Show;
import com.joss.voodootvdb.interfaces.VoodooItem;

public class Cast implements VoodooItem {

    @Expose
    private String character;
    @Expose
    private Person person;
    @Expose
    private Show show;
    @Expose
    private Movie movie;

    private int type;
    private String sectionTitle;

    @Override
    public int getType() {
        return type;
    }

    @Override
    public int getId() {
        return getPerson().getIds().getTrakt();
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
     *     The character
     */
    public String getCharacter() {
        return character  == null ? "" : character;
    }

    /**
     * 
     * @param character
     *     The character
     */
    public void setCharacter(String character) {
        this.character = character;
    }

    /**
     * 
     * @return
     *     The person
     */
    public Person getPerson() {
        return person == null ? new Person() : person;
    }

    /**
     * 
     * @param person
     *     The person
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    public Show getShow() {
        return show == null ? new Show() : show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public Movie getMovie() {
        return movie == null ? new Movie() : movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
