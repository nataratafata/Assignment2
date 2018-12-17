package com.example.telmex.assignment2.network;

import com.example.telmex.assignment2.model.DataModel;

import retrofit2.http.GET;
import rx.Observable;

public interface Request_Interface_Rock {

    @GET(API_Request.API_ROCK_LIST)
    Observable<DataModel> getDataList();
}
