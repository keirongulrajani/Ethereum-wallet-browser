package com.keiron.eth.domain.common.usecase;

public abstract class UseCase<Params, Type> {
    public abstract Type buildUseCase(Params params);
}