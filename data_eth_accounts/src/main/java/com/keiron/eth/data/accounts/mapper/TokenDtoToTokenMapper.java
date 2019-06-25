package com.keiron.eth.data.accounts.mapper;

import com.keiron.eth.data.accounts.model.TokenDto;
import com.keiron.eth.domain.accounts.model.Token;
import com.keiron.eth.library.common.mapper.BaseMapperToDomain;

import javax.inject.Inject;

public class TokenDtoToTokenMapper extends BaseMapperToDomain<TokenDto, Token> {

    @Inject
    public TokenDtoToTokenMapper() {

    }

    @Override
    public Token mapToDomain(TokenDto toBeTransformed) {
        return new Token(toBeTransformed.getAddress(), toBeTransformed.getName(), toBeTransformed.getSymbol());
    }
}
