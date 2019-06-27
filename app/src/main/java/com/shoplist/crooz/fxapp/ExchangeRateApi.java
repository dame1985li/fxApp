package com.shoplist.crooz.fxapp;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ExchangeRateApi {

    String URL = "/latest";

    @GET(URL)
    Observable<ExchangeRateResponse> getExchangeRate(
            @Query("access_key") String accessKey,
            @Query("base") String base,
            @Query("symbols") String symbols);
}
