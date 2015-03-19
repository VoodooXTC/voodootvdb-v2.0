package com.joss.voodootvdb.activities;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.joss.voodootvdb.DataStore;
import com.joss.voodootvdb.R;
import com.joss.voodootvdb.api.models.Login.AccessTokenRequest;
import com.joss.voodootvdb.utils.Utils;
import com.joss.voodootvdb.views.ErrorView;
import com.joss.voodootvdb.views.LoadingView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by: jossayjacobo
 * Date: 3/10/15
 * Time: 5:34 PM
 */
public class LoginWebViewActivity extends BaseActivity implements View.OnClickListener {

    public static final String TAG = LoginWebViewActivity.class.getSimpleName();
    private static final String USERNAME = "username";

    String CLIENT_ID = AccessTokenRequest.CLIENT_ID;
    String REDIRECT_URI = AccessTokenRequest.REDIRECT_URI;
    String OAUTH_URL = "https://trakt.tv/oauth/authorize?response_type=code&client_id=" + CLIENT_ID + "&redirect_uri=" + REDIRECT_URI;

    @InjectView(R.id.login_card_view)
    CardView cardView;
    @InjectView(R.id.login_webview)
    WebView webView;
    @InjectView(R.id.login_error)
    ErrorView errorView;
    @InjectView(R.id.login_loading)
    LoadingView loadingView;

    boolean animatedIn = false;

    public static Intent getIntent(Context context, String username){
        Intent oauthIntent = new Intent(context, LoginWebViewActivity.class);
        oauthIntent.putExtra(USERNAME, username);
        return oauthIntent;
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.inject(this);

        String username = getIntent().getStringExtra(USERNAME);
        if(username != null)
            OAUTH_URL += "&username=" + username;

        errorView.setOnClickListener(this);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(OAUTH_URL);
        webView.setWebViewClient(new VoodooWebViewClient());
        showContent();

        cardView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                cardView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                animateViewsIn();
            }
        });
        setResult(RESULT_OK);
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_error:
                webView.loadUrl(OAUTH_URL);
                showContent();
                break;
        }
    }

    public class VoodooWebViewClient extends WebViewClient{
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (url.contains("?code=")) {
                    showLoading();
                    Uri uri = Uri.parse(url);
                    String authCode = uri.getQueryParameter("code");

                    AccessTokenRequest accessTokenRequest = new AccessTokenRequest(authCode);
                    DataStore.persistAccessTokenRequest(LoginWebViewActivity.this, accessTokenRequest);

                    setResult(RESULT_OK);
                    Utils.hideSoftKeyboard(LoginWebViewActivity.this, webView);
                    finish();
                }
            }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if(url.contains(REDIRECT_URI) && url.contains("?code=")){
                showLoading();
                return true;
            } else if(url.contains(REDIRECT_URI) && url.contains("error=access_denied")) {
                setResult(RESULT_CANCELED);
                finish();
                return  true;
            }
            return false;
        }

        @Override
        public void onReceivedError(WebView webView, int errorCode, String description, String failingUrl){
            super.onReceivedError(webView, errorCode, description, failingUrl);
            showError();
            setResult(RESULT_CANCELED);
        }
    }

    private void animateViewsIn() {
        if(!animatedIn){
            cardView.animate().translationY(cardView.getMeasuredHeight() * 2).setDuration(0).start();
            cardView.animate()
                    .translationY(0)
                    .setDuration(500)
                    .setInterpolator(new AccelerateDecelerateInterpolator())
                    .setListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
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

    private void showContent(){
        loadingView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
    }

    private void showLoading(){
        loadingView.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
    }

    private void showError(){
        loadingView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
    }

}
