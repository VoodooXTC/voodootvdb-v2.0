package com.joss.voodootvdb.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.joss.voodootvdb.R;
import com.joss.voodootvdb.api.models.Episode.Episode;
import com.joss.voodootvdb.utils.DateFormatter;
import com.joss.voodootvdb.utils.GGson;
import com.squareup.picasso.Picasso;

import butterknife.InjectView;

/**
 * Created by Jossay
 * Date: 3/14/2015
 * Time: 2:14 PM
 */
public class EpisodeFragment extends BaseFragment {

    private static final String EPISODE = "episode";

    @InjectView(R.id.episode_image)
    ImageView image;
    @InjectView(R.id.episode_title)
    TextView title;
    @InjectView(R.id.episode_rating)
    RatingBar ratingBar;
    @InjectView(R.id.episode_info)
    TextView info;
    @InjectView(R.id.episode_guests)
    TextView guests;
    @InjectView(R.id.episode_description)
    TextView description;

    @Override
    int getLayoutId() {
        return R.layout.fragment_episode;
    }

    public static EpisodeFragment getInstance(Episode episode){
        Bundle args = new Bundle();
        args.putString(EPISODE, GGson.toJson(episode));

        EpisodeFragment episodeFragment = new EpisodeFragment();
        episodeFragment.setArguments(args);

        return episodeFragment;
    }

    Episode episode;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        episode = GGson.fromJson(getArguments().getString(EPISODE), Episode.class);
        setContent(episode);
    }

    private void setContent(Episode episode) {
        String url = episode.getImages().getScreenshot().getFull();
        if(!url.isEmpty()){
            Picasso.with(getActivity()).load(url).fit().centerCrop().into(image);
        }
        title.setText(episode.getTitle());
        ratingBar.setRating((float) (episode.getRating() / 2));
        info.setText(DateFormatter.formatIso(episode.getFirstAired(), getString(R.string.tba)));
        description.setText(episode.getOverview(getString(R.string.no_overview_available)));
    }

}
