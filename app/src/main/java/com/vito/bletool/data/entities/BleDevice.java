package com.vito.bletool.data.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sunde_000 on 25/10/2017.
 */

public class BleDevice implements Parcelable {

    public final String macAddress;
    public final String name;
    public final int rssi;

    public BleDevice(String macAddress, String name, int rssi) {
        this.macAddress = macAddress;
        this.name = name;
        this.rssi = rssi;
    }

    private BleDevice(Parcel in) {
        macAddress = in.readString();
        name = in.readString();
        rssi = in.readInt();
    }

    public static final Creator<BleDevice> CREATOR = new Creator<BleDevice>() {
        @Override
        public BleDevice createFromParcel(Parcel in) {
            return new BleDevice(in);
        }

        @Override
        public BleDevice[] newArray(int size) {
            return new BleDevice[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(macAddress);
        parcel.writeString(name);
        parcel.writeInt(rssi);
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null
                && obj instanceof BleDevice
                && ((BleDevice) obj).macAddress.equalsIgnoreCase(macAddress);
    }

}