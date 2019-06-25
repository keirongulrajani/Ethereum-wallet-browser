package com.keiron.eth.smartcontracttest.drawable;

import android.content.Context;
import android.graphics.ColorFilter;

import androidx.core.content.ContextCompat;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieCompositionFactory;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.LottieTask;
import com.airbnb.lottie.SimpleColorFilter;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieValueCallback;
import com.keiron.eth.smartcontracttest.R;

public class LoadingDrawable extends LottieDrawable {

    public LoadingDrawable(Context context) {
        super();
        init(context);
    }

    private void init(Context context) {
        int accentColour = ContextCompat.getColor(context, R.color.colorAccent);
        ColorFilter filter = new SimpleColorFilter(accentColour);
        KeyPath keyPath = new KeyPath("**");
        LottieValueCallback<ColorFilter> callback = new LottieValueCallback<>(filter);
        addValueCallback(keyPath, LottieProperty.COLOR_FILTER, callback);
        LottieTask lottieTask = LottieCompositionFactory.fromRawRes(context, R.raw.loader_anim);
        lottieTask.addListener(lottieComposition -> {
            setComposition((LottieComposition) lottieComposition);
            setRepeatCount(LottieDrawable.INFINITE);
            playAnimation();
        });
    }
}