package com.joss.voodootvdb.views;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joss.voodootvdb.R;
import com.joss.voodootvdb.api.models.Movie.Movie;
import com.joss.voodootvdb.api.models.People.Cast;
import com.joss.voodootvdb.api.models.Show.Show;
import com.joss.voodootvdb.interfaces.VoodooClickListener;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by: jossayjacobo
 * Date: 3/5/15
 * Time: 6:53 PM
 */
public class VoodooCardView extends LinearLayout implements View.OnClickListener {

    public static final int TYPE_SHOW = 0;
    public static final int TYPE_CAST = 1;
    public static final int TYPE_CAST_SHOW = 2;
    public static final int TYPE_CAST_MOVIE = 3;
    public static final int TYPE_MOVIE = 4;

    @InjectView(R.id.card_view)
    CardView cardView;
    @InjectView(R.id.card_image)
    ImageView image;
    @InjectView(R.id.card_menu)
    ImageView menu;
    @InjectView(R.id.card_title)
    TextView title;

    VoodooClickListener listener;
    Context context;

    Show show;
    Cast cast;
    Movie movie;

    int type;

    public VoodooCardView(Context context) {
        this(context, null);
    }

    public VoodooCardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VoodooCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        this.context = context;
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_card_view, this, true);
        ButterKnife.inject(this);
        image.setVisibility(INVISIBLE);
        title.setVisibility(INVISIBLE);
        menu.setVisibility(INVISIBLE);

        cardView.setOnClickListener(this);
        menu.setOnClickListener(this);
    }

    public void setContent(Show show, VoodooClickListener voodooClickListener) {
        this.type = TYPE_SHOW;
        this.listener = voodooClickListener;
        this.show = show;
        this.title.setText(show.getTitle());

        if(!show.getImages().getPoster().getFull().isEmpty())
            Picasso.with(context)
                    .load(show.getImages().getPoster().getFull())
                    .fit()
                    .centerCrop()
                    .placeholder(R.drawable.placeholder_vertical)
                    .error(R.drawable.placeholder_vertical)
                    .into(image);
    }

    public void setContent(Cast cast, VoodooClickListener voodooClickListener) {
        this.type = cast.getType();
        this.listener = voodooClickListener;
        this.cast = cast;
        this.title.setText(cast.getCharacter());

        String url = "";
        switch (this.type){
            case TYPE_CAST:
                url = cast.getPerson().getImages().getHeadshot().getFull();
                break;

            case TYPE_CAST_SHOW:
                url = cast.getShow().getImages().getPoster().getFull();
                break;

            case TYPE_CAST_MOVIE:
                url = cast.getMovie().getImages().getPoster().getFull();
                break;
        }
        if(!url.isEmpty())
            Picasso.with(context)
                    .load(url)
                    .fit()
                    .centerCrop()
                    .placeholder(R.drawable.placeholder_vertical)
                    .error(R.drawable.placeholder_vertical)
                    .into(image);
    }

    public void setContent(Movie m, VoodooClickListener listener) {
        this.type = m.getType();
        this.listener = listener;
        this.movie = m;
        this.title.setText(m.getTitle());

        String url = m.getImages().getPoster().getFull();
        if(!url.isEmpty())
            Picasso.with(context)
                    .load(url)
                    .fit()
                    .centerCrop()
                    .placeholder(R.drawable.placeholder_vertical)
                    .error(R.drawable.placeholder_vertical)
                    .into(image);
    }

    public void animateIn(int position){
        // Initial Position
        title.setAlpha(0);
        menu.animate().alpha(0).setDuration(0).start();

        // Animate
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1, 0, 0);
        scaleAnimation.setDuration(200);
        scaleAnimation.setStartOffset(position * 150);
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                image.setVisibility(VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                title.setVisibility(VISIBLE);
                title.animate().alpha(1).setDuration(300).start();

                if(type == TYPE_SHOW || type == TYPE_MOVIE){
                    menu.setVisibility(VISIBLE);
                    menu.animate().alpha(1).setDuration(300).start();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        image.startAnimation(scaleAnimation);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.card_view:
                if(listener != null){
                    switch (type){
                        case TYPE_SHOW:
                            listener.onShowClicked(show);
                            break;

                        case TYPE_CAST:
                            listener.onCastClicked(cast);
                            break;

                        case TYPE_CAST_SHOW:
                            listener.onShowClicked(cast.getShow());
                            break;

                        case TYPE_CAST_MOVIE:
                            listener.onMovieClicked(cast.getMovie());
                            break;
                    }
                }
                break;

            case R.id.card_menu:
                if(listener != null){
                    switch (type){
                        case TYPE_SHOW:
                            listener.onShowMenuClicked(show);
                            break;

                        case TYPE_MOVIE:
                            listener.onMovieMenuClicked(movie);
                            break;
                    }
                }
                break;
        }
    }
}
