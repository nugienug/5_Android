package com.example.TEM_PMSD.homeiot_lights.util;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;

import com.example.TEM_PMSD.homeiot_lights.R;

public class CommonUtil {

    private static Drawable[] drawables = new Drawable[3];

    public static Drawable makeLightsType(Resources resources, int index){

        if (drawables[index] == null){
            TypedArray images = resources.obtainTypedArray(R.array.lights_resources);
            Drawable drawable = images.getDrawable(index);
            drawables[index]=drawable;
        }

        return drawables[index];
    }
}
