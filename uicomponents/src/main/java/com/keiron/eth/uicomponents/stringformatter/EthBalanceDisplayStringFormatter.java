package com.keiron.eth.uicomponents.stringformatter;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class EthBalanceDisplayStringFormatter {
    @Inject
    public EthBalanceDisplayStringFormatter() {

    }

    public String formatForDisplay(BigDecimal bigDecimal) {
        return bigDecimal.setScale(8, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();
    }
}
