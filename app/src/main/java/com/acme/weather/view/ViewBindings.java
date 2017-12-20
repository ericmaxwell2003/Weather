package com.acme.weather.view;


import android.databinding.BindingAdapter;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.ImageView;

public class ViewBindings {

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
