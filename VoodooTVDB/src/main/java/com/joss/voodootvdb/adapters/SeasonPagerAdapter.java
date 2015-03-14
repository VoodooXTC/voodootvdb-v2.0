package com.joss.voodootvdb.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.joss.voodootvdb.api.models.Episode.Episode;
import com.joss.voodootvdb.fragments.EpisodeFragment;

import java.util.List;

/**
 * Created by Jossay
 * Date: 3/14/2015
 * Time: 2:10 PM
 */
public class SeasonPagerAdapter extends FragmentPagerAdapter{

    List<Episode> episodes;

    public SeasonPagerAdapter(FragmentManager fm, List<Episode> episodes){
        super(fm);
        this.episodes = episodes;
    }
    @Override
    public Fragment getItem(int position) {
        return EpisodeFragment.getInstance(episodes.get(position));
    }

    @Override
    public int getCount() {
        return episodes == null ? 0 : episodes.size();
    }

    @Override
    public String getPageTitle(int position){
        return "S" + String.format("%02d", episodes.get(position).getSeason()) + "E" + String.format("%02d", episodes.get(position).getNumber());
    }
}
