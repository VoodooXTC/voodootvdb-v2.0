package com.joss.voodootvdb.activities;

import android.os.Bundle;

import com.joss.voodootvdb.R;

import butterknife.ButterKnife;

/**
 * Created by jossayjacobo
 * Date: 3/4/15
 * Time: 10:01 AM
 */
public class ShowActivity extends BaseActivity{

    public static final String TAG = ShowActivity.class.getSimpleName();
    public static final String ID = "id";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null){
            setContentView(R.layout.activity_main);
            ButterKnife.inject(this);
        }else{

        }
    }

}
