package com.keiron.eth.smartcontracttest.screens.tokens;

import androidx.lifecycle.MutableLiveData;
import com.keiron.eth.domain.accounts.usecase.GetTokenAccountsForEthereumAccountUseCase;
import com.keiron.eth.library.common.schedulers.SchedulersProvider;
import com.keiron.eth.smartcontracttest.screens.tokens.mapper.TokenAccountToTokenAccountUiModelMapper;
import com.keiron.eth.smartcontracttest.screens.tokens.model.TokenAccountUiModel;
import com.keiron.eth.smartcontracttest.screens.tokens.model.TokenAccountViewState;
import com.keiron.eth.uicomponents.viewmodel.BaseViewModel;
import io.reactivex.disposables.Disposable;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

public class TokenAccountViewModel extends BaseViewModel {

    private GetTokenAccountsForEthereumAccountUseCase getTokenAccountsForEthereumAccountUseCase;
    private TokenAccountToTokenAccountUiModelMapper mapper;
    private SchedulersProvider schedulersProvider;

    MutableLiveData<TokenAccountViewState> tokenAccountsViewState = new MutableLiveData<>();

    @Inject
    public TokenAccountViewModel(GetTokenAccountsForEthereumAccountUseCase getTokenAccountsForEthereumAccountUseCase,
                                 TokenAccountToTokenAccountUiModelMapper mapper,
                                 SchedulersProvider schedulersProvider) {

        this.getTokenAccountsForEthereumAccountUseCase = getTokenAccountsForEthereumAccountUseCase;
        this.mapper = mapper;
        this.schedulersProvider = schedulersProvider;
    }

    void onNavigateToView(String ethAddress) {
        Disposable disposable = getTokenAccountsForEthereumAccountUseCase.buildUseCase(ethAddress)
                .subscribeOn(schedulersProvider.io())
                .observeOn(schedulersProvider.mainThread())
                .doOnSubscribe(disposable1 -> tokenAccountsViewState.postValue(createLoadingState()))
                .map(accounts -> mapper.mapToPresentation(accounts))
                .subscribe(uiModels -> tokenAccountsViewState.postValue(createDataState(uiModels)),
                        throwable -> tokenAccountsViewState.postValue(createErrorState(throwable)));
        compositeDisposable.add(disposable);
    }

    private TokenAccountViewState createLoadingState() {
        return new TokenAccountViewState(true, TokenAccountViewState.Error.NONE, Collections.emptyList());
    }

    private TokenAccountViewState createErrorState(Throwable throwable) {
        return new TokenAccountViewState(false, new TokenAccountViewState.Error.NetworkIssue("Error fetching posts: $throwable"), Collections.emptyList());
    }

    private TokenAccountViewState createDataState(List<TokenAccountUiModel> uiModels) {
        return new TokenAccountViewState(false, TokenAccountViewState.Error.NONE, uiModels);

    }
}
