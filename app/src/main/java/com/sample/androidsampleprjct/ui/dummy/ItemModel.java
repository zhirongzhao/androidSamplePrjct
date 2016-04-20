package com.sample.androidsampleprjct.ui.dummy;

import android.animation.TimeInterpolator;

public class ItemModel {
    public final String description;
    public final int colorId1;
    public final int colorId2;
    public final String descriptionDetail;
    public final TimeInterpolator interpolator;

    public ItemModel(String description, int colorId1, int colorId2, TimeInterpolator interpolator,String descriptionDetail) {
        this.description = description;
        this.colorId1 = colorId1;
        this.colorId2 = colorId2;
        this.interpolator = interpolator;
        this.descriptionDetail = descriptionDetail;
    }
}