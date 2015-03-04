package com.joss.voodootvdb.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.joss.voodootvdb.model.DrawerModel;
import com.joss.voodootvdb.views.DrawerView;

import java.util.List;

/**
 * Created by: jossayjacobo
 * Date: 2/27/15
 * Time: 4:33 PM
 */
public class MenuListAdapter extends BaseAdapter {

    Context context;
    List<DrawerModel> items;
    int selected = -1;

    public MenuListAdapter(Context context, List<DrawerModel> drawerItems) {
        this.context = context;
        this.items = drawerItems;
    }

    @Override
    public int getCount() {
        return items != null ? items.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DrawerView drawerView = convertView == null
                ? new DrawerView(context)
                : (DrawerView) convertView;
        drawerView.setContent(items.get(position));
        drawerView.setActivated(selected == position);

        return drawerView;
    }

    public int getPositionAndSelect(int currentId) {
        for(int i = 0; i < items.size(); i++){
            if(items.get(i).id == currentId){
                selected = i;
                return i;
            }
        }
        return -1;
    }
}
