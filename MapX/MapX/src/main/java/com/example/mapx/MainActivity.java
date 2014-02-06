package com.example.mapx;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import map.AboutUsFragment;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new AboutUsFragment())
                    .commit();
        }
    }
}
