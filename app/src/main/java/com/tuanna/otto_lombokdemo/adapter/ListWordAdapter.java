/******************************************************************************
 * Copyright (c) 2015. By tuanna (Jackson Nguyen)                             *
 ******************************************************************************/

package com.tuanna.otto_lombokdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tuanna.otto_lombokdemo.R;
import com.tuanna.otto_lombokdemo.common.Word;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;

/**
 * Otto-LombokDemo.
 * Created by tuanna on 28/10/2015.
 */
@EBean
public class ListWordAdapter extends BaseAdapter {
    @RootContext
    Context mContext;

    private ArrayList<Word> mArrayList;
    private LayoutInflater mInflater;

    public void setArrayListWord(ArrayList<Word> arrayListWord) {
        this.mArrayList = arrayListWord;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mArrayList.size();
    }

    @Override
    public Word getItem(int position) {
        return mArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.custom_list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            viewHolder.tvPhonetic = (TextView) convertView.findViewById(R.id.tvPhonetic);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvName.setText(getItem(position).getName());
        viewHolder.tvPhonetic.setText(getItem(position).getPhonetic());
        return convertView;
    }

    class ViewHolder {
        TextView tvName, tvPhonetic;
    }
}
