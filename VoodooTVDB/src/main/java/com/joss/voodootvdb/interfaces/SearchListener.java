package com.joss.voodootvdb.interfaces;

import com.joss.voodootvdb.api.models.Search.Search;

/**
 * Created by Jossay
 * Date: 3/15/2015
 * Time: 12:41 PM
 */
public interface SearchListener {

    public void onSearchTypeSelected(String type);

    public void onSearchItemClicked(Search search);

    public void onMoreSelected(String searchType);

}
