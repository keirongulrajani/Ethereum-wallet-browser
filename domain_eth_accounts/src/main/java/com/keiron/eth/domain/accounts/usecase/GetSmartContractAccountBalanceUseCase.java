package com.keiron.eth.domain.accounts.usecase;

import com.keiron.eth.domain.accounts.repository.AccountRepository;
import com.keiron.eth.domain.common.usecase.UseCase;
import io.reactivex.Single;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.inject.Inject;
import java.math.BigDecimal;

public class GetSmartContractAccountBalanceUseCase extends UseCase<GetSmartContractAccountBalanceUseCase.Params, Single<BigDecimal>> {

    private AccountRepository accountRepository;

    @Inject
    public GetSmartContractAccountBalanceUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Single<BigDecimal> buildUseCase(Params params) {
        return accountRepository.getSmartContractAccountBalance(params.contractAddress, params.address);
    }

    public static class Params {
        private final String contractAddress;
        private final String address;

        public Params(String contractAddress, String address) {

            this.contractAddress = contractAddress;
            this.address = address;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Params params = (Params) o;
            return contractAddress.equals(params.contractAddress) &&
                    address.equals(params.address);
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder()
                    .append(contractAddress)
                    .append(address)
                    .hashCode();
        }
    }
}
