package com.hyphenate.easeui.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hyphenate.easeui.R;

/**
 * Created by MerXu on 2017/9/29.
 */

public class SearchActivity extends Activity {
    private static final String TAG = "SearchActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchpeople);
    }
}
