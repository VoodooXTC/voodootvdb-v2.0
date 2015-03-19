package com.joss.voodootvdb.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.joss.voodootvdb.api.Api;
import com.joss.voodootvdb.api.models.Search.Search;
import com.joss.voodootvdb.interfaces.SearchListener;
import com.joss.voodootvdb.views.SearchItem;
import com.joss.voodootvdb.views.SearchSectionTitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jossay
 * Date: 3/15/2015
 * Time: 2:19 PM
 */
public class SearchAdapter extends BaseAdapter {

    Context context;
    public List<Search> items;
    SearchListener listener;

    public SearchAdapter(Context context, SearchListener listener){
        this.context = context;
        this.listener = listener;
        this.items = new ArrayList<>();
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

    @Override
    public int getItemViewType(int position){
        return items.get(position).getType();
    }

    @Override
    public int getViewTypeCount(){
        return Search.getMaxTypes();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        switch (getItemViewType(position)){
            case Search.TYPE_ITEM:
                SearchItem searchItem = convertView == null
                        ? new SearchItem(context, listener)
                        : (SearchItem) convertView;
                searchItem.setContent(items.get(position));
                return  searchItem;

            case Search.TYPE_SECTION_TITLE:
                SearchSectionTitleView titleView = convertView == null
                        ? new SearchSectionTitleView(context, listener)
                        : (SearchSectionTitleView) convertView;
                titleView.setContent(items.get(position));
                return titleView;
        }

        // Shouldn't reach this statement, if you get a weird adapter error now you know why.
        return null;
    }

    public void setItems(List<Search> setItems){
        this.items.clear();
        this.items = addSectionTitles(setItems);
        notifyDataSetChanged();
    }

    public void addItems(List<Search> addItems){
        this.items.addAll(addItems);
        notifyDataSetChanged();
    }

    public void clearItems(){
        this.items.clear();
        notifyDataSetChanged();
    }

    private List<Search> addSectionTitles(List<Search> setItems){
        List<Search> itemsPlusSections = new ArrayList<>();
        String sectionTitle = "";

        for(int i = 0; i < setItems.size(); i++){
            if(!sectionTitle.equals(setItems.get(i).type)){
                sectionTitle = setItems.get(i).type;
                Search search = Search.getSearchTitleHeader(sectionTitle);
                itemsPlusSections.add(search);
            }
            itemsPlusSections.add(setItems.get(i));
        }
        return itemsPlusSections;
    }
}
