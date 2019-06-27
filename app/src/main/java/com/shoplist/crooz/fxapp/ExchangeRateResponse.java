package com.shoplist.crooz.fxapp;

class ExchangeRateResponse {
    private String base;
    private String date;
    private CountryCode rates;

    public String getBase() {
        return base;
    }

    public String getDate() {
        return date;
    }

    public CountryCode getRates() {
        return rates;
    }
}
