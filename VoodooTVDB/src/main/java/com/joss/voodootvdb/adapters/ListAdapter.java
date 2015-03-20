package com.joss.voodootvdb.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.joss.voodootvdb.api.models.CustomLists.CustomListItem;
import com.joss.voodootvdb.interfaces.ListListener;
import com.joss.voodootvdb.views.CustomListItemView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: jossayjacobo
 * Date: 3/20/15
 * Time: 4:06 PM
 */
public class ListAdapter extends BaseAdapter {

    private Context context;
    public List<CustomListItem> items;
    private ListListener listener;

    public ListAdapter(Context context, ListListener listener){
        this.context = context;
        this.items = new ArrayList<>();
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return items == null ? 0 : items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setItems(List<CustomListItem> items){
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CustomListItemView customListItem = convertView == null
                ? new CustomListItemView(context, listener)
                : (CustomListItemView) convertView;
        customListItem.setContent(items.get(position));
        return customListItem;
    }
}
