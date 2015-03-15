package com.joss.voodootvdb.views;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joss.voodootvdb.R;
import com.joss.voodootvdb.api.models.Search.Search;
import com.joss.voodootvdb.interfaces.SearchListener;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Jossay
 * Date: 3/15/2015
 * Time: 2:40 PM
 */
public class SearchSectionTitleView extends LinearLayout implements View.OnClickListener {

    @InjectView(R.id.search_section_title)
    TextView title;
    @InjectView(R.id.search_section_title_more)
    TextView more;

    Search search;
    SearchListener listener;

    public SearchSectionTitleView(Context context, SearchListener listener) {
        super(context);
        this.listener = listener;
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.li_search_section_header, this);
        ButterKnife.inject(this);
        more.setOnClickListener(this);
    }

    public void setContent(Search search){
        this.search = search;
        title.setText(search.getTitle());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_section_title_more:
                if(listener != null)
                    listener.onMoreSelected(search);
                break;
        }
    }
}
