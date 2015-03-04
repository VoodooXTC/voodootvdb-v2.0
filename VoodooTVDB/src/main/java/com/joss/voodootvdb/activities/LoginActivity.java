package com.joss.voodootvdb.activities;

import android.app.LoaderManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.dd.CircularProgressButton;
import com.iangclifton.android.floatlabel.FloatLabel;
import com.joss.voodootvdb.R;
import com.joss.voodootvdb.api.Api;
import com.joss.voodootvdb.api.ApiService;
import com.joss.voodootvdb.api.models.Login.UserModel;
import com.joss.voodootvdb.api.models.Show.Show;
import com.joss.voodootvdb.provider.shows_popular.ShowsPopularColumns;
import com.joss.voodootvdb.provider.shows_popular.ShowsPopularCursor;
import com.joss.voodootvdb.provider.shows_popular.ShowsPopularProvider;
import com.joss.voodootvdb.utils.BlurTransformation;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by: jossayjacobo
 * Date: 3/4/15
 * Time: 10:01 AM
 */
public class LoginActivity extends BaseActivity implements LoaderManager.LoaderCallbacks<Cursor>, View.OnClickListener, TextWatcher, View.OnKeyListener {

    public static final String TAG = LoginActivity.class.getSimpleName();

    private static final int POPULAR_SHOWS_CALLBACK = 234;

    @InjectView(R.id.login_bg_image)
    ImageView backgroundImage;
    @InjectView(R.id.login_username)
    FloatLabel username;
    @InjectView(R.id.login_password)
    FloatLabel password;
    @InjectView(R.id.login_button)
    CircularProgressButton loginButton;

    private LocalBroadcastManager broadcastManager;
    private ApiReceiver apiReceiver;
    boolean loginInProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        broadcastManager = LocalBroadcastManager.getInstance(this);
        apiReceiver = new ApiReceiver();

        getLoaderManager().initLoader(POPULAR_SHOWS_CALLBACK, null, this);
        Api.getPopularShows(this, ApiService.EXT_IMAGES, ApiService.EXT_FULL);

        username.getEditText().addTextChangedListener(this);
        password.getEditText().addTextChangedListener(this);
        password.getEditText().setOnKeyListener(this);
        loginButton.setOnClickListener(this);
    }

    @Override
    protected void onResume(){
        super.onResume();
        resetLoginButtonState();
        broadcastManager.registerReceiver(apiReceiver, new IntentFilter(ApiService.BROADCAST));
    }

    @Override
    protected void onPause(){
        super.onPause();
        resetLoginButtonState();
        broadcastManager.unregisterReceiver(apiReceiver);
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
            List<Show> shows = ShowsPopularProvider.get(cursor);
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

    private class ApiReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            int api_type = intent.getIntExtra(ApiService.REQUEST_TYPE, -1);
            int status = intent.getExtras().getInt(ApiService.RESULT_STATUS);

            switch (api_type){
                case ApiService.REQUEST_LOGIN:

                    switch (status){
                        case ApiService.RESULT_SUCCESS:
                            setButtonSuccessState();
                            toast(getString(R.string.login_success));
                            break;
                        case ApiService.RESULT_ERROR:
                            setButtonErrorState();
                            toast(intent.getStringExtra(ApiService.RESULT_MESSAGE));
                            break;
                        default:

                    }
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_button:
                attemptLogin();
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        resetLoginButtonState();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_ENTER:
                attemptLogin();
                return true;
        }
        return false;
    }

    private void attemptLogin() {
        if(!loginInProgress){
            String u = username.getEditText().getText().toString();
            String p = password.getEditText().getText().toString();
            Api.login(this, u, p);
            setButtonInProgress();
            hideSoftKeyboard(loginButton);
        }
    }

    private void resetLoginButtonState() {
        loginInProgress = false;
        loginButton.setProgress(CircularProgressButton.IDLE_STATE_PROGRESS);
    }

    private void setButtonInProgress(){
        loginInProgress = true;
        loginButton.setIndeterminateProgressMode(true);
        loginButton.setProgress(CircularProgressButton.INDETERMINATE_STATE_PROGRESS);
    }

    private void setButtonErrorState(){
        loginInProgress = false;
        loginButton.setIndeterminateProgressMode(true);
        loginButton.setProgress(CircularProgressButton.ERROR_STATE_PROGRESS);
    }

    private void setButtonSuccessState(){
        loginInProgress = false;
        loginButton.setIndeterminateProgressMode(true);
        loginButton.setProgress(CircularProgressButton.SUCCESS_STATE_PROGRESS);
    }
}
