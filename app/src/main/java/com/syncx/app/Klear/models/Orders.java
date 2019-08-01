package com.syncx.app.Klear.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Orders implements Parcelable {

    private String waste;
    private String cost;
    private String date;
    private String time;

    public Orders(String waste, String cost, String date, String time) {
        this.waste = waste;
        this.cost = cost;
        this.date = date;
        this.time = time;
    }

    public Orders() {
    }

    protected Orders(Parcel in) {
        waste = in.readString();
        cost = in.readString();
        date = in.readString();
        time = in.readString();
    }

    public static final Creator<Orders> CREATOR = new Creator<Orders>() {
        @Override
        public Orders createFromParcel(Parcel in) {
            return new Orders(in);
        }

        @Override
        public Orders[] newArray(int size) {
            return new Orders[size];
        }
    };

    public String getWaste() {
        return waste;
    }

    public void setWaste(String waste) {
        this.waste = waste;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "waste='" + waste + '\'' +
                ", cost='" + cost + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(waste);
        parcel.writeString(cost);
        parcel.writeString(date);
        parcel.writeString(time);
    }
}
