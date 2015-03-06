package com.joss.voodootvdb.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.joss.voodootvdb.R;
import com.joss.voodootvdb.api.ApiService;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by: jossayjacobo
 * Date: 3/5/15
 * Time: 5:11 PM
 */
public abstract class BaseListFragment extends BaseFragment {

    @InjectView(R.id.listView)
    public ListView listView;

    public View errorView;
    public View loadingView;

    abstract View getLoadingView();
    abstract View getErrorView();
    abstract List<Integer> getApiTypes();

    abstract void onErrorMessageReceived();

    protected LocalBroadcastManager broadcastManager;
    private ApiReceiver apiReceiver;

    @Override
    int getLayoutId() {
        return R.layout.fragment_base_list_view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if(getApiTypes().size() > 0){
            broadcastManager = LocalBroadcastManager.getInstance(getActivity());
            apiReceiver = new ApiReceiver();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        RelativeLayout view = (RelativeLayout) super.onCreateView(inflater, container, savedInstanceState);

        if(getErrorView() != null) {
            errorView = getErrorView();
            view.addView(errorView);
        }

        if(getLoadingView() != null){
            loadingView = getLoadingView();
            view.addView(loadingView);
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        broadcastManager.registerReceiver(apiReceiver, new IntentFilter(ApiService.BROADCAST));
    }

    @Override
    public void onPause() {
        super.onPause();
        broadcastManager.unregisterReceiver(apiReceiver);
    }

    public void showContent(){
        if(errorView != null)
            errorView.setVisibility(View.GONE);

        if(loadingView != null)
            loadingView.setVisibility(View.GONE);
    }

    public void showLoadingView(){
        if(errorView != null)
            errorView.setVisibility(View.GONE);

        if(loadingView != null)
            loadingView.setVisibility(View.VISIBLE);
    }

    public void showErrorView(){
        if(errorView != null)
            errorView.setVisibility(View.VISIBLE);

        if(loadingView != null)
            loadingView.setVisibility(View.GONE);
    }

    private class ApiReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int requestType = intent.getIntExtra(ApiService.REQUEST_TYPE, -1);
            for(int type : getApiTypes()){
                if(requestType == type){
                    int status = intent.getIntExtra(ApiService.RESULT_STATUS, ApiService.RESULT_ERROR);
                    if (status == ApiService.RESULT_ERROR) {
                        onErrorMessageReceived();
                    }
                }
            }
        }
    }

}
