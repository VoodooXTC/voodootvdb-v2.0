package com.joss.voodootvdb.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
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
}
