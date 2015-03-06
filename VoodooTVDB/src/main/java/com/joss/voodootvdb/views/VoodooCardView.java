package com.joss.voodootvdb.views;

import android.animation.Animator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joss.voodootvdb.R;
import com.joss.voodootvdb.api.models.Show.Show;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by: jossayjacobo
 * Date: 3/5/15
 * Time: 6:53 PM
 */
public class VoodooCardView extends LinearLayout {

    @InjectView(R.id.card_image)
    ImageView image;
    @InjectView(R.id.card_menu)
    ImageView menu;
    @InjectView(R.id.card_title)
    TextView title;

    Context context;
    Show show;

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
    }

    public void setContent(Show show) {
        this.show = show;
        this.title.setText(show.getTitle());
        Picasso.with(context)
                .load(show.getImages().getPoster().getFull())
                .fit()
                .centerCrop()
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
                menu.setVisibility(VISIBLE);

                title.animate().alpha(1).setDuration(300).start();
                menu.animate().alpha(1).setDuration(300).start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        image.startAnimation(scaleAnimation);
    }
}
