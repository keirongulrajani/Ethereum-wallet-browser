package com.keiron.eth.uicomponents.viewmodel;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.concurrent.atomic.AtomicBoolean;

public class SingleLiveEvent<T> extends MutableLiveData<T> {

    private AtomicBoolean pending = new AtomicBoolean(false);

    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
        super.observe(owner, t -> {
            if (pending.compareAndSet(true, false)) {
                observer.onChanged(t);
            }
        });
    }

    @Override
    public void setValue(T value) {
        pending.set(true);
        super.setValue(value);
    }

    /**
     * Used for cases where T is Void, to make calls cleaner.
     */
    @MainThread
    public void call() {
        setValue(null);
    }

    /**
     * Used for cases where T is Void, to make calls cleaner.
     */
    public void post() {
        postValue(null);
    }

}
