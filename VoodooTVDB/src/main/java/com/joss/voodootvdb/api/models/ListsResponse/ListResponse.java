package com.joss.voodootvdb.api.models.ListsResponse;

import com.google.gson.annotations.SerializedName;

/**
 * Created by: jossayjacobo
 * Date: 4/3/15
 * Time: 1:58 PM
 */
public class ListResponse {

    public Added added = new Added();

    public Existing existing = new Existing();

    public Deleted deleted = new Deleted();

    @SerializedName("not_found")
    public NotFound notFound = new NotFound();

    public int addedSize() {
        return added.size();
    }

    public int removedSize(){
        return deleted.size();
    }
}
