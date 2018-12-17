package com.example.telmex.assignment2.network;

import com.example.telmex.assignment2.model.DataModel;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class ConnectionService implements Data_Interactor {

    private static Retrofit retrofit;

    public ConnectionService(){
        getConnection();
    }

    public static RequestInterface getConnection(){
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(API_Request.BASE_URL)
                .build();

        return  retrofit.create(RequestInterface.class);
    }


    @Override
    public Observable<DataModel> getDataList() {
        return getConnection().getDataList();
    }
}
