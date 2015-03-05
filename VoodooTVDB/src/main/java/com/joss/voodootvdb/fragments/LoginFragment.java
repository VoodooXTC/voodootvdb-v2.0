package com.joss.voodootvdb.fragments;

import android.animation.Animator;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.dd.CircularProgressButton;
import com.iangclifton.android.floatlabel.FloatLabel;
import com.joss.voodootvdb.R;
import com.joss.voodootvdb.api.Api;
import com.joss.voodootvdb.api.ApiService;
import com.joss.voodootvdb.utils.Utils;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by: jossayjacobo
 * Date: 3/5/15
 * Time: 10:09 AM
 */
public class LoginFragment extends BaseFragment implements TextWatcher, View.OnKeyListener, View.OnClickListener {

    public static final String TAG = LoginFragment.class.getSimpleName();

    @InjectView(R.id.login_card_view)
    CardView cardView;
    @InjectView(R.id.login_username)
    FloatLabel username;
    @InjectView(R.id.login_password)
    FloatLabel password;
    @InjectView(R.id.login_button)
    CircularProgressButton loginButton;
    @InjectView(R.id.login_icon_launcher)
    ImageView iconLauncher;

    private LocalBroadcastManager broadcastManager;
    private ApiReceiver apiReceiver;
    boolean loginInProgress;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.inject(this, view);

        broadcastManager = LocalBroadcastManager.getInstance(getActivity());
        apiReceiver = new ApiReceiver();

        username.getEditText().addTextChangedListener(this);
        password.getEditText().addTextChangedListener(this);

        password.getEditText().setOnKeyListener(this);

        loginButton.setOnClickListener(this);

        cardView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                cardView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                animateViewsIn();
            }
        });

        return view;
    }

    private void animateViewsIn() {
        cardView.animate().translationY(cardView.getMeasuredHeight()).setDuration(0).start();
        cardView.animate()
                .translationY(0)
                .setDuration(1000)
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

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
        resetLoginButtonState();
        broadcastManager.unregisterReceiver(apiReceiver);
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
                            Utils.toast(getActivity(), getString(R.string.login_success));
                            break;
                        case ApiService.RESULT_ERROR:
                            setButtonErrorState();
                            Utils.toast(getActivity(), intent.getStringExtra(ApiService.RESULT_MESSAGE));
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
            Api.login(getActivity(), u, p);
            setButtonInProgress();
            Utils.hideSoftKeyboard(getActivity(), loginButton);
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
