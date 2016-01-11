package com.example.zhongyu.retrofitdemo.Parcelable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhongyu on 1/11/2016.
 */
public class Book implements Parcelable {

    private int mData;

    protected Book(Parcel in) {
        mData = in.readInt();
    }


    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mData);
    }
}
