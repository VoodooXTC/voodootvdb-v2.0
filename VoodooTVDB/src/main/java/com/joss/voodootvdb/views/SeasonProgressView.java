package com.joss.voodootvdb.views;

import android.content.Context;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
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
public class SeasonProgressView extends LinearLayout implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {

    public static final String TAG = SeasonProgressView.class.getSimpleName();

    @InjectView(R.id.season_container)
    RippleView seasonContainer;
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

        seasonContainer.setOnClickListener(this);
        menu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getContext(), menu);
                popupMenu.getMenuInflater().inflate(R.menu.menu_season, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(SeasonProgressView.this);
                popupMenu.show();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.season_container:
                if(listener != null)
                    listener.onSeasonClicked(season);
                break;
            case R.id.season_menu:

                break;
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_season_watched_all:
                Log.e(TAG, "Watched All episodes on Season " + season.getNumber());
                return true;
        }
        return false;
    }
}
