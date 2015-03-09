package com.joss.voodootvdb.views;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joss.voodootvdb.R;
import com.joss.voodootvdb.api.models.People.Cast;
import com.joss.voodootvdb.api.models.Show.Show;
import com.joss.voodootvdb.interfaces.VoodooClickListener;
import com.joss.voodootvdb.interfaces.VoodooItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by: jossayjacobo
 * Date: 3/5/15
 * Time: 5:53 PM
 */
@SuppressLint("ViewConstructor")
public class VoodooHorizontalScrollView extends LinearLayout {

    public static final String TAG = VoodooHorizontalScrollView.class.getSimpleName();
    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_FEATURE = 1;

    @InjectView(R.id.home_hsv_section_title)
    TextView sectionTitle;
    @InjectView(R.id.home_hsv_container)
    LinearLayout container;

    Context context;
    int type;
    List<VoodooItem> items;
    VoodooClickListener listener;

    public VoodooHorizontalScrollView(Context context){
        this(context, null);
    }

    public VoodooHorizontalScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VoodooHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, TYPE_NORMAL, null);
    }

    public VoodooHorizontalScrollView(Context context, int type, VoodooClickListener listener) {
        super(context);
        init(context, type, listener);
    }

    public void init(Context context, int type, VoodooClickListener listener){
        this.context = context;
        this.type = type;
        this.listener = listener;
        this.items = new ArrayList<>();

        AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(params);
        setOrientation(VERTICAL);

        LayoutInflater.from(context).inflate(R.layout.view_home_horizontal_scroll_view, this, true);
        ButterKnife.inject(this);
    }

    public void setListener(VoodooClickListener voodooClickListener){
        this.listener = voodooClickListener;
    }

    public void setItems(String sectionTitle, List<VoodooItem> items){
        if(!isObjectsEqual(this.items, items)){

            this.sectionTitle.setVisibility(sectionTitle == null ? GONE : VISIBLE);
            this.sectionTitle.setText(sectionTitle == null ? "" : sectionTitle);

            this.container.removeAllViews();
            this.items = items;
            addViews();
            animateIn();
        }
    }

    private void addViews() {
        for(Object o : items){
            if(type == TYPE_FEATURE){
                FeatureView featureView = new FeatureView(context);
                if(o instanceof Show)
                    featureView.setContent((Show) o, listener);

                container.addView(featureView);

            }else if(type == TYPE_NORMAL){
                VoodooCardView cardView = new VoodooCardView(context);
                if(o instanceof Show) {
                    cardView.setContent((Show) o, listener);
                }else if(o instanceof Cast){
                    Cast c = (Cast) o;
                    cardView.setContent(c, listener);
                }

                container.addView(cardView);

            }

        }
    }

    private boolean isObjectsEqual(List<VoodooItem> items1, List<VoodooItem> items2) {
        // Check same size
        if(items1.size() != items2.size())
            return false;

        for(int i = 0; i < items1.size(); i++){

            // Check to see if they are the same class
            if(!items1.get(i).getClass().equals(items2.get(i).getClass()))
                return false;

            if(items1.get(i) instanceof Show){
                Show s1 = (Show) items1.get(i);
                Show s2 = (Show) items2.get(i);

                if(!s1.getIds().getTrakt().equals(s2.getIds().getTrakt()))
                    return false;
            }else if(items1.get(i) instanceof Cast){
                Cast c1 = (Cast) items1.get(i);
                Cast c2 = (Cast) items2.get(i);

                if(c1.getId() != c2.getId())
                    return false;
            }
            // TODO else if(o instaceof OTHER_STUFF){
        }
        return true;
    }

    private void animateIn() {
        // Starting position
        sectionTitle.animate().alpha(0).setDuration(0).start();
        container.animate().translationX(container.getMeasuredWidth()).setDuration(0).start();
        // Animation
        sectionTitle.animate().alpha(1).setDuration(500).start();
        container.animate().translationX(0).setDuration(500)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if(type == TYPE_NORMAL){
                            for(int i = 0; i < container.getChildCount(); i++){
                                ((VoodooCardView) container.getChildAt(i)).animateIn(i);
                            }
                        }else if(type == TYPE_FEATURE){
                            for(int i = 0; i < container.getChildCount(); i++){
                                ((FeatureView) container.getChildAt(i)).animateIn();
                            }
                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }).start();
    }

}
