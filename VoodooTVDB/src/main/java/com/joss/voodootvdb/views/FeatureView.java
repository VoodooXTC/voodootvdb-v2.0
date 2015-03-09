package com.joss.voodootvdb.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joss.voodootvdb.R;
import com.joss.voodootvdb.api.models.Show.Show;
import com.joss.voodootvdb.interfaces.VoodooClickListener;
import com.melnykov.fab.FloatingActionButton;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by: jossayjacobo
 * Date: 3/5/15
 * Time: 6:19 PM
 */
public class FeatureView extends LinearLayout implements View.OnClickListener {

    @InjectView(R.id.feature_image)
    ImageView image;
    @InjectView(R.id.feature_play_button)
    FloatingActionButton floatingActionButton;
    @InjectView(R.id.feature_title)
    TextView title;

    Context context;
    Show show;
    VoodooClickListener listener;

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

        image.setOnClickListener(this);
        floatingActionButton.setOnClickListener(this);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_feature_card_view, this, true);
        ButterKnife.inject(this);
    }

    public void setContent(Show show, VoodooClickListener listener){
        this.listener = listener;
        this.show = show;
        this.title.setText(show.getTitle());
        Picasso.with(context)
                .load(show.getImages().getFanart().getFull())
                .fit()
                .centerCrop()
                .into(image);

        title.setVisibility(INVISIBLE);
        floatingActionButton.setVisibility(INVISIBLE);
    }

    public void animateIn(){
        title.setVisibility(VISIBLE);
        title.setAlpha(0);
        title.animate().alpha(1).setDuration(800).start();

        if(!show.getTrailer().isEmpty()){
            floatingActionButton.setVisibility(VISIBLE);
            floatingActionButton.animate().scaleX(0).scaleY(0).setDuration(0).start();
            floatingActionButton.animate().scaleX(1).scaleY(1).setDuration(800).start();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.feature_image:
                if(listener != null)
                    listener.onShowClicked(show);
                break;

            case R.id.feature_play_button:
                if(listener != null)
                    listener.onTrailerClicked(show.getTrailer());
                break;
        }
    }
}
