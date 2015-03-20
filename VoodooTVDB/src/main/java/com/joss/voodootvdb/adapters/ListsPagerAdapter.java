package com.joss.voodootvdb.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.joss.voodootvdb.api.models.CustomLists.CustomList;
import com.joss.voodootvdb.fragments.ListFragment;

import java.util.List;

/**
 * Created by Jossay
 * Date: 3/14/2015
 * Time: 2:10 PM
 */
public class ListsPagerAdapter extends FragmentPagerAdapter{

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
    public int getCount() {
        return lists == null ? 0 : lists.size();
    }

    @Override
    public String getPageTitle(int position){
        return lists.get(position).getName();
    }
}
