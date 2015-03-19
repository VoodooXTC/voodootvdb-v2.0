package com.joss.voodootvdb.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.joss.voodootvdb.R;

/**
 * Created by: jossayjacobo
 * Date: 3/17/15
 * Time: 11:23 AM
 */
public class LoadingListItemView extends LinearLayout {

    public LoadingListItemView(Context context) {
        this(context, null);
    }

    public LoadingListItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingListItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.li_loading_view, this);
    }

}
