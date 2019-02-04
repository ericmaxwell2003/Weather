package com.acme.weather.view;


import android.view.View;
import android.widget.ImageView;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.databinding.BindingAdapter;

public class BindingAdapters {

    @BindingAdapter("app:weatherIcon")
    public static void showWeatherIcon(ImageView imageView, @DrawableRes int id) {
        if(id != 0) {
            imageView.setImageResource(id);
        }
    }

    @BindingAdapter("app:weatherBackground")
    public static void setWeatherBackground(View view, @ColorRes int colorId) {
        if(colorId != 0) {
            view.setBackgroundResource(colorId);
        }
    }


}
