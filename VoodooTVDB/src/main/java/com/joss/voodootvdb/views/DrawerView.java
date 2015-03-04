package com.joss.voodootvdb.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joss.voodootvdb.R;
import com.joss.voodootvdb.model.DrawerModel;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by: jossayjacobo
 * Date: 3/2/15
 * Time: 4:23 PM
 */
public class DrawerView extends LinearLayout {

    @InjectView(R.id.drawer_icon)
    ImageView icon;
    @InjectView(R.id.drawer_title)
    TextView title;

    public DrawerModel drawerItem;

    public DrawerView(Context context) {
        this(context, null);
    }

    public DrawerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.li_drawer, this, true);
        ButterKnife.inject(this);
    }

    public void setContent(DrawerModel drawerItem){
        this.drawerItem = drawerItem;

        icon.setImageResource(drawerItem.resourceId);
        title.setText(drawerItem.title);
    }
}
