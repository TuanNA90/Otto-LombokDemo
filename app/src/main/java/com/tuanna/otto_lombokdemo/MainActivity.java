/******************************************************************************
 * Copyright (c) 2015. By tuanna (Jackson Nguyen)                             *
 ******************************************************************************/

package com.tuanna.otto_lombokdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.squareup.otto.Produce;
import com.tuanna.otto_lombokdemo.adapter.ListWordAdapter;
import com.tuanna.otto_lombokdemo.bus.BusProvider;
import com.tuanna.otto_lombokdemo.bus.BusWord;
import com.tuanna.otto_lombokdemo.common.Word;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    @ViewById(R.id.lvWord)
    ListView lvWord;
    @Bean
    ListWordAdapter mAdapter;
    @Bean
    BusProvider mBusProvider;

    private ArrayList<Word> mArrayList;
    private Word mWord;

    @AfterViews
    void init() {
        mArrayList = new ArrayList<>();
        mAdapter.setArrayListWord(mArrayList);
        lvWord.setAdapter(mAdapter);
        lvWord.setDivider(null);
        setData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBusProvider.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mBusProvider.unregister(this);
    }

    private void setData() {
        for (int i = 0; i < 10; i++) {
            Word w = Word.builder()
                    .id(i)
                    .name("Word " + i)
                    .phonetic("This is Word " + i)
                    .meaning("")
                    .build();
            mArrayList.add(w);
        }
        mAdapter.notifyDataSetChanged();
    }

    @ItemClick
    void lvWordItemClicked(Word word) {
        this.mWord = word;
        Log.d("xxx", "Word: " + mWord.getId());
        mBusProvider.post(produceBusWord());
        addFragment(ReplaceValueFragment_.builder().build());
    }

    /**
     * Method add new Fragment
     *
     * @param fragment parameter of fragment insert into
     */
    public void addFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(
                    android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                    android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            fragmentTransaction.replace(R.id.container, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    @Produce
    public BusWord produceBusWord() {
        return new BusWord(mWord);
    }
}
