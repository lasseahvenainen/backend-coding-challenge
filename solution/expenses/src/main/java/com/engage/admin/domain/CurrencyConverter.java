package com.engage.admin.domain;

import tech.sirwellington.alchemy.http.*;

import java.net.MalformedURLException;

public class CurrencyConverter {

    /** Do we need to use https ?? */
    private static final String URL = "http://api.fixer.io/latest";

    public static Float convertCurrency(Float theAmount, String currencyFrom, String currencyTo) throws Exception {

        AlchemyHttp http = AlchemyHttp.newDefaultInstance();
        String finalUrl = URL+"?base="+currencyFrom;

        CurrencyRates  currencyRates  = http.go()
                .get()
                .expecting(CurrencyRates.class)
                .at(finalUrl);

        return theAmount * currencyRates.getRate(Currencies.valueOf(currencyTo));
    }
}
