package com.joss.voodootvdb.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joss.voodootvdb.R;
import com.joss.voodootvdb.api.models.Show.Show;
import com.melnykov.fab.FloatingActionButton;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by: jossayjacobo
 * Date: 3/5/15
 * Time: 6:19 PM
 */
public class FeatureView extends LinearLayout {

    @InjectView(R.id.feature_image)
    ImageView image;
    @InjectView(R.id.feature_play_button)
    FloatingActionButton floatingActionButton;
    @InjectView(R.id.feature_title)
    TextView title;

    Context context;
    Show show;

    public FeatureView(Context context) {
        this(context, null);
    }

    public FeatureView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FeatureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_feature_card_view, this, true);
        ButterKnife.inject(this);
    }

    public void setContent(Show show){
        this.show = show;
        title.setText(show.getTitle());
        Picasso.with(context)
                .load(show.getImages().getFanart().getFull())
                .fit()
                .centerCrop()
                .into(image);

        floatingActionButton.setVisibility(!show.getTrailer().isEmpty()
                ? VISIBLE
                : GONE);
    }

    public void showPlayButton(){
        floatingActionButton.show();
    }

    public void hidePlayButton(){
        floatingActionButton.hide();
    }
}
