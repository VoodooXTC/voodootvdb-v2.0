package com.joss.voodootvdb.interfaces;

import com.joss.voodootvdb.api.models.Show.Show;

/**
 * Created by: jossayjacobo
 * Date: 3/6/15
 * Time: 1:30 PM
 */
public interface HomeClickListener {

    public void onShowClicked(Show show);

    public void onShowMenuClicked(Show show);

    public void onTrailerClicked(String url);

}
