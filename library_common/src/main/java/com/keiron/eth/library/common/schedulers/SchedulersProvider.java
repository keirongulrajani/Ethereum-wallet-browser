package com.keiron.eth.library.common.schedulers;

import io.reactivex.Scheduler;

public interface SchedulersProvider {

    Scheduler mainThread();

    Scheduler io();

    Scheduler computation();

    Scheduler current();
}
