package com.joss.voodootvdb.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.joss.voodootvdb.R;
import com.joss.voodootvdb.fragments.PersonFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Jossay
 * Date: 3/8/2015
 * Time: 3:39 PM
 */
public class PersonActivity extends BaseActivity {

    public final static String TRAKT_ID = "trakt_id";

    @InjectView(R.id.content_container)
    FrameLayout frameLayout;

    Fragment fragment;

    public static void startActivity(Context context, int traktId){
        Intent i = new Intent(context, PersonActivity.class);
        i.putExtra(TRAKT_ID, traktId);
        context.startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        ButterKnife.inject(this);

        setupToolbar(toolbar);
        setToolbarTitle(getString(R.string.person));
        setContent(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        getSupportFragmentManager().putFragment(savedInstanceState, PersonFragment.TAG, fragment);
    }

    private void setContent(Bundle savedInstance){
        if(savedInstance == null){
            fragment = PersonFragment.newInstance( getIntent().getIntExtra(TRAKT_ID, 0));
        }else{
            fragment = getSupportFragmentManager().findFragmentByTag(PersonFragment.TAG);
        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(frameLayout.getId(), fragment, PersonFragment.TAG);
        ft.commit();
    }
}
