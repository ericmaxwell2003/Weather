package com.acme.weather.view;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.fragment.app.Fragment;

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

    class Foo extends Fragment {
        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            Bundle args = getArguments();
            ((TextView) view.findViewById(android.R.id.text1))
                    .setText(Integer.toString(args.getInt("")));
        }
    }

}
