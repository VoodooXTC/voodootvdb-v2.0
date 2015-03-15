package com.joss.voodootvdb.views;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joss.voodootvdb.R;
import com.joss.voodootvdb.api.models.Search.Search;
import com.joss.voodootvdb.interfaces.SearchListener;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Jossay
 * Date: 3/15/2015
 * Time: 2:56 PM
 */
public class SearchItem extends LinearLayout implements View.OnClickListener {

    @InjectView(R.id.search_image)
    ImageView image;
    @InjectView(R.id.search_title)
    TextView title;
    @InjectView(R.id.search_subtitle)
    TextView subtitle;

    Search search;
    SearchListener listener;

    public SearchItem(Context context, SearchListener listener) {
        super(context);
        this.listener = listener;
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.li_search, this);
        ButterKnife.inject(this);
        setOnClickListener(this);
    }

    public void setContent(Search search){
        this.search = search;
        this.title.setText(search.getTitle());
        this.subtitle.setText(search.getSubtitle());
        if(!search.getImage().isEmpty()){
            Picasso.with(getContext())
                    .load(search.getImage())
                    .error(R.drawable.placeholder_vertical)
                    .placeholder(R.drawable.placeholder_vertical)
                    .fit()
                    .centerCrop()
                    .into(image);
        }else{
            image.setImageResource(R.drawable.placeholder_vertical);
        }
    }

    @Override
    public void onClick(View v) {
        if(listener != null)
            listener.onSearchItemClicked(search);
    }
}
