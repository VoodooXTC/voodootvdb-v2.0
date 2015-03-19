package com.joss.voodootvdb.views;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joss.voodootvdb.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by: jossayjacobo
 * Date: 3/19/15
 * Time: 10:17 AM
 */
public class EmptyView extends LinearLayout {

    @InjectView(R.id.empty_text)
    TextView emptyText;

    public EmptyView(Context context) {
        super(context);
        init();
    }

    private void init() {
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setLayoutParams(params);

        inflate(getContext(), R.layout.view_empty, this);
        ButterKnife.inject(this);
    }

    public EmptyView setText(String text){
        emptyText.setText(text);
        return this;
    }

}
