package com.joss.voodootvdb.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.joss.voodootvdb.api.models.CustomLists.CustomList;
import com.joss.voodootvdb.fragments.ListFragment;

import java.util.List;

/**
 * Created by Jossay
 * Date: 3/14/2015
 * Time: 2:10 PM
 */
public class ListsPagerAdapter extends FragmentStatePagerAdapter{

    public List<CustomList> lists;

    public ListsPagerAdapter(FragmentManager fm, List<CustomList> lists){
        super(fm);
        this.lists = lists;
    }

    @Override
    public Fragment getItem(int position) {
        return ListFragment.getInstance(lists.get(position));
    }

    @Override
    public int getItemPosition(Object object){
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return lists == null ? 0 : lists.size();
    }

    @Override
    public String getPageTitle(int position){
        return lists.get(position).getName();
    }

    public void setItems(List<CustomList> lists){
        this.lists = lists;
        notifyDataSetChanged();
    }

    public boolean equals(List<CustomList> newList){
        if(lists == null || newList == null)
            return false;

        if(lists.size() != newList.size())
            return false;

        for(int i = 0; i < lists.size(); i++){
            if(!lists.get(i).getIds().getTrakt().equals(newList.get(i).getIds().getTrakt()))
                return false;
        }

        return true;
    }
}
