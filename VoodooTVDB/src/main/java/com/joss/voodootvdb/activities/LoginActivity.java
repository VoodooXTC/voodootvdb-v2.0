package com.joss.voodootvdb.activities;

import android.app.LoaderManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.joss.voodootvdb.DataStore;
import com.joss.voodootvdb.R;
import com.joss.voodootvdb.api.Api;
import com.joss.voodootvdb.api.ApiService;
import com.joss.voodootvdb.api.models.Show.Show;
import com.joss.voodootvdb.fragments.LoginFragment;
import com.joss.voodootvdb.interfaces.LoginListener;
import com.joss.voodootvdb.provider.shows.ShowsProvider;
import com.joss.voodootvdb.provider.shows_popular.ShowsPopularColumns;
import com.joss.voodootvdb.provider.shows_popular.ShowsPopularCursor;
import com.joss.voodootvdb.provider.shows_popular.ShowsPopularProvider;
import com.joss.voodootvdb.utils.BlurTransformation;
import com.joss.voodootvdb.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by: jossayjacobo
 * Date: 3/4/15
 * Time: 10:01 AM
 */
public class LoginActivity extends BaseActivity implements LoaderManager.LoaderCallbacks<Cursor>,
        LoginListener {

    public static final String TAG = LoginActivity.class.getSimpleName();
    private static final int POPULAR_SHOWS_CALLBACK = 234;
    private static final int LOGIN_CODE = 567;

    @InjectView(R.id.login_bg_image)
    ImageView backgroundImage;
    @InjectView(R.id.login_content)
    FrameLayout frameLayout;

    LoginFragment loginFragment;

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        super.onCreate(savedInstanceState);

        if(DataStore.getAccessToken(this) == null){
            setContentView(R.layout.activity_login);
            ButterKnife.inject(this);

            loginFragment = savedInstanceState == null
                    ? LoginFragment.getInstance(false)
                    : (LoginFragment) getSupportFragmentManager().findFragmentByTag(LoginFragment.TAG);

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(frameLayout.getId(), loginFragment, LoginFragment.TAG);
            ft.commit();

            getLoaderManager().initLoader(POPULAR_SHOWS_CALLBACK, null, this);
            Api.getPopularShows(this);
        }else{
            launchMainActivity();
        }
    }

    private void launchMainActivity() {
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 500);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        getSupportFragmentManager().putFragment(savedInstanceState, LoginFragment.TAG, loginFragment);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this,
                ShowsPopularColumns.CONTENT_URI,
                ShowsPopularColumns.FULL_PROJECTION,
                null,
                null,
                ShowsPopularColumns.DEFAULT_ORDER);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if(data != null){
            ShowsPopularCursor cursor = new ShowsPopularCursor(data);
            List<Show> shows = ShowsProvider.get(this, cursor);
            for(Show show : shows){
                String image = show.getImages().getPoster().getFull();
                if(!image.isEmpty()){
                    BlurTransformation blurTransformation = new BlurTransformation(this, 25);
                    Picasso.with(this)
                            .load(image)
                            .fit()
                            .centerCrop()
                            .transform(blurTransformation)
                            .into(backgroundImage);
                    break;
                }
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onOAuthRequest(String username) {
        startActivityForResult(LoginWebViewActivity.getIntent(this, username), LOGIN_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == LOGIN_CODE) {
            if (resultCode == RESULT_OK) {
                loginFragment.attempToLogin();
            } else if(resultCode == RESULT_CANCELED){
                Utils.toast(this, "An error occurred authenticating with Trakt.tv please try again");
            }
        }
    }

    @Override
    public void onLoginSuccess() {
        loginFragment.animateViewOut();
    }

    @Override
    public void onCancel() {
    }

    @Override
    public void onAnimateOutFinished() {
        launchMainActivity();
    }
}
