package com.joss.voodootvdb.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joss.voodootvdb.interfaces.ToolbarListener;

import butterknife.ButterKnife;

/**
 * Created by: jossayjacobo
 * Date: 3/2/15
 * Time: 5:57 PM
 */
public abstract class BaseFragment extends Fragment {

    abstract int getLayoutId();

    ToolbarListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ToolbarListener){
            listener = (ToolbarListener) activity;
        }
    }

    public void setToolbarTitle(String title){
        setToolbarTitle(title, null);
    }

    public void setToolbarTitle(String title, String subtitle){
        if(listener != null){
            listener.onSetToolbarTitles(title, subtitle);
        }
    }
}
