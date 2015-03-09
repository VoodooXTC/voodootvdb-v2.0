package com.joss.voodootvdb.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.joss.voodootvdb.interfaces.VoodooClickListener;
import com.joss.voodootvdb.interfaces.VoodooItem;
import com.joss.voodootvdb.views.VoodooCardView;
import com.joss.voodootvdb.views.VoodooHorizontalScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jossay
 * Date: 3/8/2015
 * Time: 5:20 PM
 */
public class PersonAdapter extends BaseAdapter {

    Context context;
    List<List<VoodooItem>> items;
    VoodooClickListener voodooClickListener;

    // TODO add types
    int type;

    public PersonAdapter(Context context, VoodooClickListener listener){
        this.context = context;
        this.items = new ArrayList<>();
        this.voodooClickListener = listener;
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

    @Override
    public int getItemViewType(int position){
        return items.get(position).get(0).getType();
    }

    @Override
    public int getViewTypeCount(){
        return VoodooCardView.TYPE_CAST_MOVIE + 1;
    }

    public void setContent(List<List<VoodooItem>> items){
        this.items = items;
        notifyDataSetChanged();
    }

    public int add(List<VoodooItem> item){
        if(items == null)
            items = new ArrayList<>();

        items.add(item);
        notifyDataSetChanged();
        return item.size() - 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VoodooHorizontalScrollView hScrollView = convertView == null
                ? new VoodooHorizontalScrollView(context,
                    VoodooHorizontalScrollView.TYPE_NORMAL,
                    voodooClickListener)
                : (VoodooHorizontalScrollView) convertView;

        hScrollView.setItems(items.get(position).get(0).getSectionTitle(), items.get(position));
        return hScrollView;
    }

    public void remove(int position) {
        items.remove(position);
        notifyDataSetChanged();
    }
}
