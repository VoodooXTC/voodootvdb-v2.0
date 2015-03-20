package com.joss.voodootvdb.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joss.voodootvdb.R;
import com.joss.voodootvdb.api.models.CustomLists.CustomListItem;
import com.joss.voodootvdb.interfaces.ListListener;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by: jossayjacobo
 * Date: 3/20/15
 * Time: 4:11 PM
 */
public class CustomListItemView extends LinearLayout implements View.OnClickListener {

    @InjectView(R.id.list_image)
    ImageView image;
    @InjectView(R.id.list_title)
    TextView title;
    @InjectView(R.id.list_subtitle)
    TextView subtitle;

    CustomListItem customListItem;
    ListListener listener;

    public CustomListItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomListItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public CustomListItemView(Context context, ListListener listener) {
        super(context);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.li_list_item_view, this);
        ButterKnife.inject(this);
        setOnClickListener(this);
    }

    public void setContent(CustomListItem customListItem){
        this.customListItem = customListItem;
        this.title.setText(customListItem.getTitle());
        this.subtitle.setText(customListItem.getSubtitle());
        if(!customListItem.getImage().isEmpty()){
            Picasso.with(getContext())
                    .load(customListItem.getImage())
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

    }
}
