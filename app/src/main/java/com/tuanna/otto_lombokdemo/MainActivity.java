/******************************************************************************
 * Copyright (c) 2015. By tuanna (Jackson Nguyen)                             *
 ******************************************************************************/

package com.tuanna.otto_lombokdemo;

import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.squareup.otto.Produce;
import com.tuanna.otto_lombokdemo.adapter.ListWordAdapter;
import com.tuanna.otto_lombokdemo.bus.BusArrayListWord;
import com.tuanna.otto_lombokdemo.bus.BusProvider;
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
            mWord = Word.builder()
                    .id(i)
                    .name("Wor " + i)
                    .phonetic("This is Word " + i)
                    .meaning("")
                    .build();
            mArrayList.add(mWord);
        }
        mAdapter.notifyDataSetChanged();
    }

    @ItemClick
    void lvWordItemClicked() {
        mBusProvider.post(produceArrayListWord());
    }

    @Produce
    public BusArrayListWord produceArrayListWord() {
        if (mArrayList.size() > 0) {
            return new BusArrayListWord(mArrayList);
        }
        return null;
    }
}
