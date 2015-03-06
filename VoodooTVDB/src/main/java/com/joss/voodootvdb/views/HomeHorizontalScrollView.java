package com.joss.voodootvdb.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.joss.voodootvdb.api.models.Show.Show;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: jossayjacobo
 * Date: 3/5/15
 * Time: 5:53 PM
 */
@SuppressLint("ViewConstructor")
public class HomeHorizontalScrollView extends HorizontalScrollView {

    public static final String TAG = HomeHorizontalScrollView.class.getSimpleName();
    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_FEATURE = 1;

    Context context;
    int type;

    List<Object> items;
    LinearLayout container;

    public HomeHorizontalScrollView(Context context, int type) {
        super(context);
        this.type = type;
        this.context = context;
        this.items = new ArrayList<>();

        AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(params);
        setHorizontalScrollBarEnabled(false);

        container = new LinearLayout(context);
        container.setOrientation(LinearLayout.HORIZONTAL);

        FeatureView f = new FeatureView(context);
        f.setVisibility(View.INVISIBLE);
        container.addView(f);

        addView(container);
        requestLayout();
    }

    public void setItems(List<Object> items){
        this.container.removeAllViews();
        this.items = items;
        addViews();
    }

    private void addViews() {
        for(Object o : items){
            if(type == TYPE_FEATURE){
                FeatureView featureView = new FeatureView(context);
                if(o instanceof Show)
                    featureView.setContent((Show) o);

                container.addView(featureView);

            }else if(type == TYPE_NORMAL){
                VoodooCardView cardView = new VoodooCardView(context);
                if(o instanceof Show)
                    cardView.setContent((Show) o);

                container.addView(cardView);

            }

        }
    }

}
