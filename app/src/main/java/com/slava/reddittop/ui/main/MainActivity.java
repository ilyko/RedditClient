package com.slava.reddittop.ui.main;

import android.os.Bundle;

import com.slava.reddittop.R;
import com.slava.reddittop.ui.base.BaseActivity;
import com.slava.reddittop.ui.main.topfragment.TopFragment;

import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainMvp.View {

    public static int LAYOUT = R.layout.activity_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
        if (savedInstanceState == null) {
            addFragment(R.id.constraint_layout_main, TopFragment.newInstance());
        }
        setUnBinder(ButterKnife.bind(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
