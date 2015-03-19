package com.joss.voodootvdb.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.joss.voodootvdb.R;
import com.joss.voodootvdb.api.models.Search.Search;
import com.joss.voodootvdb.interfaces.SearchListener;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Jossay
 * Date: 3/15/2015
 * Time: 12:27 PM
 */
public class SearchHeader extends LinearLayout implements AdapterView.OnItemSelectedListener {

    @InjectView(R.id.search_type)
    Spinner spinner;

    String[] types;
    SearchListener listener;

    public SearchHeader(Context context, SearchListener listener) {
        super(context);
        this.listener = listener;
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.view_search_header, this);
        ButterKnife.inject(this);
        types = getContext().getResources().getStringArray(R.array.search_types);
        spinner.setAdapter(new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                types));
        spinner.setOnItemSelectedListener(this);
    }

    public void setType(String type){
        for(int i = 0; i < types.length; i++){
            if(types[i].toLowerCase().equals(type)){
                spinner.setOnItemSelectedListener(null);
                spinner.setSelection(i);
                spinner.setOnItemSelectedListener(this);
                break;
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(listener != null)
            listener.onSearchTypeSelected(types[position].toLowerCase());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
