package com.joss.voodootvdb.models;

/**
 * Created by: jossayjacobo
 * Date: 3/2/15
 * Time: 12:52 PM
 */
public class DrawerModel {

    public int id;
    public String title;
    public int resourceId;

    public DrawerModel(int id, String title, int resourceId){
        this.id = id;
        this.title = title;
        this.resourceId = resourceId;
    }
}
