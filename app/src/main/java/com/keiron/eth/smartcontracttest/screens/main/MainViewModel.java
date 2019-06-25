package com.keiron.eth.smartcontracttest.screens.main;

import androidx.lifecycle.MutableLiveData;
import com.keiron.eth.domain.accounts.usecase.GetEthereumAccountUseCase;
import com.keiron.eth.library.common.schedulers.SchedulersProvider;
import com.keiron.eth.smartcontracttest.screens.main.model.MainUiModel;
import com.keiron.eth.smartcontracttest.screens.main.model.MainViewState;
import com.keiron.eth.uicomponents.viewmodel.BaseViewModel;
import io.reactivex.disposables.Disposable;

import javax.inject.Inject;

public class MainViewModel extends BaseViewModel {

    private GetEthereumAccountUseCase getEthereumAccountUseCase;
    private SchedulersProvider schedulersProvider;
    private MutableLiveData<MainViewState> mainViewState = new MutableLiveData<>();

    @Inject
    public MainViewModel(GetEthereumAccountUseCase getEthereumAccountUseCase, SchedulersProvider schedulersProvider) {
        this.getEthereumAccountUseCase = getEthereumAccountUseCase;
        this.schedulersProvider = schedulersProvider;
    }

    void onEthAddressSubmitted(String ethAddress) {
        Disposable disposable = getEthereumAccountUseCase.buildUseCase(ethAddress)
                .subscribeOn(schedulersProvider.io())
                .observeOn(schedulersProvider.mainThread())
                .doOnSubscribe(disposable1 -> mainViewState.postValue(createLoadingState())).subscribe();

        compositeDisposable.add(disposable);
    }

    private MainViewState createLoadingState() {
        return new MainViewState(true, MainViewState.Error.NONE, null);
    }

    private MainViewState createErrorState(Throwable throwable) {
        return new MainViewState(false, new MainViewState.Error.NetworkIssue("Error fetching posts: $throwable"), null);
    }

    private MainViewState createDataState(MainUiModel uiModel) {
        return new MainViewState(false, MainViewState.Error.NONE, uiModel);

    }
}
