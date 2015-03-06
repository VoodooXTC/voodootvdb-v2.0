package com.joss.voodootvdb.adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.joss.voodootvdb.views.HomeHorizontalScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: jossayjacobo
 * Date: 3/5/15
 * Time: 5:53 PM
 */
public class HomeAdapter extends BaseAdapter {

    public static final String TAG = HomeAdapter.class.getSimpleName();

    Context context;
    List<List<Object>> items;
    List<Integer> types;

    // TODO types getType()

    public HomeAdapter(Context context){
        this.context = context;
        this.items = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return items == null ? 0 : items.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setContent(List<List<Object>> items, List<Integer> types){
        this.items = items;
        this.types = types;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HomeHorizontalScrollView homeHorizontalScrollView =
                convertView == null
                    ? new HomeHorizontalScrollView(context, types.get(position))
                    : (HomeHorizontalScrollView) convertView;

        if(convertView == null){
            Log.e(TAG, position + "convertView == null");
        }else{
            Log.e(TAG, position + "convertView != null");
        }

        homeHorizontalScrollView.setItems(items.get(position));

        return homeHorizontalScrollView;
    }
}
