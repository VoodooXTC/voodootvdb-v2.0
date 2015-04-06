package com.joss.voodootvdb.api.models.ListsResponse;

/**
 * Created by: jossayjacobo
 * Date: 4/3/15
 * Time: 1:58 PM
 */
public class Deleted {

    public int movies;

    public int shows;

    public int seasons;

    public int episodes;

    public int people;

    public int size() {
        return movies + shows + seasons + episodes + people;
    }
}
