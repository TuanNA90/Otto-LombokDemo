/******************************************************************************
 * Copyright (c) 2015. By tuanna (Jackson Nguyen)                             *
 ******************************************************************************/

package com.tuanna.otto_lombokdemo.common;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Data;
import lombok.experimental.Builder;

/**
 * Otto-LombokDemo.
 * Created by tuanna on 28/10/2015.
 */
@Data
@Builder
public class Word implements Parcelable {

    public static final Creator<Word> CREATOR = new Creator<Word>() {
        @Override
        public Word createFromParcel(Parcel in) {
            return Word.builder()
                    .id(in.readInt())
                    .name(in.readString())
                    .phonetic(in.readString())
                    .meaning(in.readString())
                    .build();
        }

        @Override
        public Word[] newArray(int size) {
            return new Word[size];
        }
    };
    private int id;
    private String name;
    private String phonetic;
    private String meaning;

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phonetic='" + phonetic + '\'' +
                ", meaning='" + meaning + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(phonetic);
        parcel.writeString(meaning);
    }
}
