package com.shoplist.crooz.fxapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                // エンドポイントの設定
                .baseUrl("http://data.fixer.io/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        retrofit.create(ExchangeRateApi.class)
                // USドルからJPYに絞り込むクエリ
                .getExchangeRate("6ae6707077aa02b215b0ae6e93fb7ca8","EUR", "CAD")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ( new Observer<ExchangeRateResponse>(){
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("通信 -> ", "失敗" + e.toString());
                    }

                    @Override
                    public void onNext(ExchangeRateResponse exchangeRateResponse) {
                        Log.d("通信 -> ", "成功");
                        TextView textView = findViewById(R.id.txt_jpy);
                        textView.setText("JPY: " + exchangeRateResponse.getRates().getJPY() );
                    }
                });
    }
}
