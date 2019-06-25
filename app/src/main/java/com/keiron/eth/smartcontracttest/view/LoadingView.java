package com.keiron.eth.smartcontracttest.view;

import android.content.Context;
import android.graphics.ColorFilter;
import android.util.AttributeSet;
import androidx.core.content.ContextCompat;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.SimpleColorFilter;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieValueCallback;
import com.keiron.eth.smartcontracttest.R;

public class LoadingView extends LottieAnimationView {
    public LoadingView(Context context) {
        super(context);
        init(context);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        int accentColour = ContextCompat.getColor(context, R.color.colorAccent);
        ColorFilter filter = new SimpleColorFilter(accentColour);
        KeyPath keyPath = new KeyPath("**");
        LottieValueCallback<ColorFilter> callback = new LottieValueCallback<>(filter);
        addValueCallback(keyPath, LottieProperty.COLOR_FILTER, callback);

        setAnimation(R.raw.loader_anim);
        playAnimation();
    }
}