package com.joss.voodootvdb.views;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andexert.library.RippleView;
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

    @InjectView(R.id.search_section_title_container)
    RippleView container;
    @InjectView(R.id.search_section_title)
    TextView title;

    Search search;
    SearchListener listener;

    public SearchSectionTitleView(Context context){
        this(context, null);
    }

    public SearchSectionTitleView(Context context, SearchListener listener) {
        super(context);
        this.listener = listener;
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.li_search_section_header, this);
        ButterKnife.inject(this);
        container.setOnClickListener(this);
    }

    public void setContent(Search search){
        this.search = search;
        title.setText(search.getTitle());
    }

    @Override
    public void onClick(View v) {
        if(listener != null)
            listener.onMoreSelected(search.getTitle());
    }
}
