package com.example.listofusers.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Data implements Parcelable {

    private String name, age, address;

    public Data() {
        this.name = "";
        this.age = "";
        this.address = "";
    }

    public Data(String name, String age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    protected Data(Parcel in) {
        name = in.readString();
        age = in.readString();
        address = in.readString();
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(age);
        dest.writeString(address);
    }
}
