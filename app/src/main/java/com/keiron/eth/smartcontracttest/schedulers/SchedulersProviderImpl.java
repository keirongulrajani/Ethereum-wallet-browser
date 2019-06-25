package com.keiron.eth.smartcontracttest.schedulers;

import com.keiron.eth.library.common.schedulers.SchedulersProvider;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import javax.inject.Inject;
import java.util.concurrent.Executor;

public class SchedulersProviderImpl implements SchedulersProvider {
    private Scheduler current = Schedulers.from(new CurrentThreadExecutor());

    @Inject
    public SchedulersProviderImpl() {
    }

    @Override
    public Scheduler mainThread() {
        return AndroidSchedulers.mainThread();
    }

    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

    @Override
    public Scheduler computation() {
        return Schedulers.computation();
    }

    @Override
    public Scheduler current() {
        return current;
    }

    private static class CurrentThreadExecutor implements Executor {

        @Override
        public void execute(Runnable command) {
            command.run();
        }
    }
}
