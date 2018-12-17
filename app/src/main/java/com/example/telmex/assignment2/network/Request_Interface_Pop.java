package com.example.telmex.assignment2.network;

import com.example.telmex.assignment2.model.DataModel;

import retrofit2.http.GET;
import rx.Observable;

public interface Request_Interface_Pop {

    @GET(API_Request.API_POP_LIST)
    Observable<DataModel> getDataList();
}
