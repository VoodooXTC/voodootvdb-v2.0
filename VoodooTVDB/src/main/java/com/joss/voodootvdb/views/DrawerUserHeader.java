package com.joss.voodootvdb.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joss.voodootvdb.R;
import com.joss.voodootvdb.api.models.Settings.Settings;
import com.joss.voodootvdb.transformations.CircleTransform;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by: jossayjacobo
 * Date: 3/19/15
 * Time: 4:44 PM
 */
public class DrawerUserHeader extends LinearLayout {

    @InjectView(R.id.user_avatar)
    ImageView avatar;
    @InjectView(R.id.user_username)
    TextView username;
    @InjectView(R.id.user_real_name)
    TextView userRealName;

    public DrawerUserHeader(Context context) {
        this(context, null);
    }

    public DrawerUserHeader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawerUserHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.view_drawer_user_header, this);
        ButterKnife.inject(this);
    }

    public void setContent(Settings settings){
        String avatarUrl = settings.getUser().getImages().getAvatar().getFull();
        if(!avatarUrl.isEmpty()){
            Picasso.with(getContext())
                    .load(avatarUrl)
                    .error(R.drawable.ic_launcher)
                    .placeholder(R.drawable.ic_launcher)
                    .fit()
                    .centerCrop()
                    .transform(new CircleTransform())
                    .into(avatar);
        }else{
            Picasso.with(getContext())
                    .load(R.drawable.ic_launcher)
                    .fit()
                    .centerCrop()
                    .transform(new CircleTransform())
                    .into(avatar);
        }
        username.setText(settings.getUser().getUsername());
        userRealName.setText(settings.getUser().getName());
    }

}
