package com.keiron.eth.data.accounts.model;

public enum TokenDto {

    GOLEM("0xa74476443119A942dE498590Fe1f2454d7D4aC0d", "Golem", "GNT"),
    AUGUR("0xff3daa886d63547c4e2d78449915fa9e01193f59", "Augur", "REP"),
    DAI("0x89d24a6b4ccb1b6faa2625fe562bdd9a23260359", "Dai Stablecoin", "DAI"),
    OMISEGO("0xd26114cd6EE289AccF82350c8d8487fedB8A0C07", "OmiseGo", "OMG");

    private final String address;
    private String name;
    private final String symbol;

    TokenDto(String address, String name, String symbol) {
        this.address = address;
        this.name = name;
        this.symbol = symbol;
    }

    public String getAddress() {
        return address;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }
}
