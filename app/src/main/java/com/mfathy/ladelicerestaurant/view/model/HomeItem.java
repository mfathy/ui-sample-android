package com.mfathy.ladelicerestaurant.view.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Mohammed Fathy on 13/07/2018.
 * dev.mfathy@gmail.com
 *
 * {@link HomeItem} parcelable.
 */

public class HomeItem implements Parcelable {
    public static final Creator<HomeItem> CREATOR = new Creator<HomeItem>() {
        @Override
        public HomeItem createFromParcel(Parcel in) {
            return new HomeItem(in);
        }

        @Override
        public HomeItem[] newArray(int size) {
            return new HomeItem[size];
        }
    };
    private String itemName;
    private String itemDescription;
    private int[] imageResources = new int[2];

    public HomeItem(String itemName, String itemDescription, int[] imageResources) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.imageResources = imageResources;
    }

    protected HomeItem(Parcel in) {
        itemName = in.readString();
        itemDescription = in.readString();
        imageResources = in.createIntArray();
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public int[] getImageResources() {
        return imageResources;
    }

    public void setImageResources(int[] imageResources) {
        this.imageResources = imageResources;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(itemName);
        dest.writeString(itemDescription);
        dest.writeIntArray(imageResources);
    }
}
