/******************************************************************************
 * Copyright (c) 2015. By tuanna (Jackson Nguyen)                             *
 ******************************************************************************/

package com.tuanna.otto_lombokdemo;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;

import com.tuanna.otto_lombokdemo.adapter.ListWordAdapter;
import com.tuanna.otto_lombokdemo.bus.BusProvider;
import com.tuanna.otto_lombokdemo.common.Word;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Otto-LombokDemo.
 * Created by tuanna on 28/10/2015.
 */
@EFragment(R.layout.fragment_replace_value)
public class ReplaceValueFragment extends Fragment {
    @Bean
    BusProvider mBusProvider;
    @Bean
    ListWordAdapter mAdapter;
    @ViewById(R.id.lvWord)
    ListView lvWord;

    private ArrayList<Word> mArrayList;
    private String[] mStrings;

    @AfterViews
    void init() {
        mArrayList = new ArrayList<>();
        mAdapter.setArrayListWord(mArrayList);
        lvWord.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mBusProvider.register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        mBusProvider.unregister(this);
    }

    @Click({R.id.tvReplaceValue, R.id.tvBinToMain})
    void onClicked(View view) {
        switch (view.getId()) {
            case R.id.tvReplaceValue:
                replaceValue();
                break;
            case R.id.tvBinToMain:
                binToMain();
                break;
        }
    }

    private void binToMain() {

    }

    private void replaceValue() {

    }
}