package com.joss.voodootvdb.views;

import android.content.Context;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.joss.voodootvdb.R;
import com.joss.voodootvdb.api.models.Season.Season;
import com.joss.voodootvdb.interfaces.SeasonListener;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by: jossayjacobo
 * Date: 3/13/15
 * Time: 2:49 PM
 */
public class SeasonProgressView extends LinearLayout {

    public static final String TAG = SeasonProgressView.class.getSimpleName();

    @InjectView(R.id.season_ripple_view)
    RippleView rippleLayout;
    @InjectView(R.id.season_number)
    TextView seasonNumber;
    @InjectView(R.id.season_progress)
    TextView progress;
    @InjectView(R.id.season_progress_bar)
    ProgressBar progressBar;
    @InjectView(R.id.season_menu)
    RippleView menu;

    SeasonListener listener;
    Season season;

    public SeasonProgressView(Context context) {
        super(context);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.view_season_progress, this);
        ButterKnife.inject(this);
        season = new Season();

        rippleLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null)
                    listener.onSeasonClicked(season);
            }
        });

        menu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "Season " + season.getNumber() + " menu clicked");
            }
        });
    }

    public void setContent(Season s, SeasonListener l){
        listener = l;
        season = s;

        seasonNumber.setText(season.getNumber() == 0
                ? getContext().getString(R.string.season_extras)
                : getContext().getString(R.string.season) + " " + season.getNumber());

        progress.setText("0/" + season.getEpisodeCount());

        progressBar.setMax(season.getEpisodeCount());
        progressBar.setProgress(0);
    }

    public void setProgress(SparseIntArray p, SeasonListener l){
        listener = l;

        progress.setText(p.get(season.getNumber()) + "/" + season.getEpisodeCount());

        progressBar.setMax(season.getEpisodeCount());
        progressBar.setProgress(p.get(season.getNumber()));
    }
}
