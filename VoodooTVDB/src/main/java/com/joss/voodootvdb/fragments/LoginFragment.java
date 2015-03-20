package com.joss.voodootvdb.fragments;

import android.animation.Animator;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.dd.CircularProgressButton;
import com.iangclifton.android.floatlabel.FloatLabel;
import com.joss.voodootvdb.DataStore;
import com.joss.voodootvdb.R;
import com.joss.voodootvdb.api.Api;
import com.joss.voodootvdb.api.ApiService;
import com.joss.voodootvdb.api.models.Login.AccessTokenRequest;
import com.joss.voodootvdb.interfaces.LoginListener;
import com.joss.voodootvdb.utils.Utils;

import butterknife.InjectView;

/**
 * Created by: jossayjacobo
 * Date: 3/5/15
 * Time: 10:09 AM
 */
public class LoginFragment extends BaseFragment implements TextWatcher, View.OnKeyListener, View.OnClickListener {

    public static final String TAG = LoginFragment.class.getSimpleName();
    public static final String ALLOW_CANCEL = "allow_cancel";

    @InjectView(R.id.login_container)
    RelativeLayout container;
    @InjectView(R.id.login_card_view)
    CardView cardView;
    @InjectView(R.id.login_username)
    FloatLabel username;
    @InjectView(R.id.login_button)
    CircularProgressButton loginButton;
    @InjectView(R.id.login_icon_launcher)
    ImageView iconLauncher;

    LocalBroadcastManager broadcastManager;
    ApiReceiver apiReceiver;
    LoginListener listener;
    boolean allowCancel;
    boolean animatedIn = false;

    public static LoginFragment getInstance(boolean allowCancel){
        Bundle b = new Bundle();
        b.putBoolean(ALLOW_CANCEL, allowCancel);

        LoginFragment loginFragment = new LoginFragment();
        loginFragment.setArguments(b);

        return loginFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        allowCancel = getArguments().getBoolean(ALLOW_CANCEL);

        broadcastManager = LocalBroadcastManager.getInstance(getActivity());
        apiReceiver = new ApiReceiver();

        username.getEditText().addTextChangedListener(this);
        username.getEditText().setOnKeyListener(this);
        
        container.setOnClickListener(this);
        loginButton.setOnClickListener(this);

        cardView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                cardView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                animateViewsIn();
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        resetLoginButtonState();
        broadcastManager.registerReceiver(apiReceiver, new IntentFilter(ApiService.BROADCAST));
    }

    @Override
    public void onPause(){
        super.onPause();
        broadcastManager.unregisterReceiver(apiReceiver);
    }

    private void attemptToGetTraktAccessToken() {
        setButtonInProgress();
        Utils.hideSoftKeyboard(getActivity(), loginButton);
        String u = username.getEditText().getText().toString();
        listener.onOAuthRequest(u);
    }

    public void attemptToLogin(){
        AccessTokenRequest accessTokenRequest = DataStore.getAccessTokenRequest(getActivity());
        Api.login(getActivity(), accessTokenRequest);
        setButtonInProgress();
    }

    private class ApiReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            int api_type = intent.getIntExtra(ApiService.REQUEST_TYPE, -1);
            int status = intent.getExtras().getInt(ApiService.RESULT_STATUS);

            switch (api_type){
                case ApiService.REQUEST_LOGIN_ACCESS_TOKEN:
                    switch (status){
                        case ApiService.RESULT_SUCCESS:
                            setButtonInProgress();
                            Api.getUserSettings(getActivity());
                            break;
                        case ApiService.RESULT_ERROR:
                            setButtonErrorState();
                            Utils.toast(getActivity(), intent.getStringExtra(ApiService.RESULT_MESSAGE));
                            break;
                    }
                    break;

                case ApiService.REQUEST_USER_SETTINGS:
                    switch (status){
                        case ApiService.RESULT_SUCCESS:
                            setButtonSuccessState();
                            Utils.toast(getActivity(), getString(R.string.login_success));
                            listener.onLoginSuccess();
                            break;

                        case ApiService.RESULT_ERROR:
                            setButtonErrorState();
                            Utils.toast(getActivity(), intent.getStringExtra(ApiService.RESULT_MESSAGE));
                            break;
                    }
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.login_button:
                attemptToGetTraktAccessToken();
                break;

            default:
                if(allowCancel)
                    listener.onCancel();
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
                attemptToGetTraktAccessToken();
                return true;
        }
        return false;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof LoginListener){
            listener = (LoginListener) activity;
        }else{
            throw new IllegalStateException(activity.getClass().getSimpleName()
                    + " must implement LoginListener");
        }
    }

    private void animateViewsIn() {
        if(!animatedIn){
            cardView.animate().translationY(cardView.getMeasuredHeight() * 2).setDuration(0).start();
            cardView.animate()
                    .translationY(0)
                    .setDuration(800)
                    .setInterpolator(new AccelerateDecelerateInterpolator())
                    .setListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            iconLauncher.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            iconLauncher.setVisibility(View.VISIBLE);
                            iconLauncher.animate().alpha(0.0f).setDuration(0).start();
                            iconLauncher.animate().alpha(1.0f).setDuration(300).start();
                            animatedIn = true;
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    })
                    .start();
        }
    }

    public void animateViewOut(){
        allowCancel = false;
        iconLauncher.animate().alpha(0.0f).setDuration(300).start();
        cardView.animate().translationY(cardView.getMeasuredHeight() * 2).alpha(0).setDuration(500)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        listener.onAnimateOutFinished();
                        allowCancel = true;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                })
                .start();
    }

    private void resetLoginButtonState() {
        loginButton.setProgress(CircularProgressButton.IDLE_STATE_PROGRESS);
    }

    private void setButtonInProgress(){
        loginButton.setIndeterminateProgressMode(true);
        loginButton.setProgress(CircularProgressButton.INDETERMINATE_STATE_PROGRESS);
    }

    private void setButtonErrorState(){
        loginButton.setIndeterminateProgressMode(true);
        loginButton.setProgress(CircularProgressButton.ERROR_STATE_PROGRESS);
    }

    private void setButtonSuccessState(){
        loginButton.setIndeterminateProgressMode(true);
        loginButton.setProgress(CircularProgressButton.SUCCESS_STATE_PROGRESS);
    }
}
