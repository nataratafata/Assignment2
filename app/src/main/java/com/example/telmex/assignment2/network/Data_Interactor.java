package com.example.telmex.assignment2.network;


import com.example.telmex.assignment2.model.DataModel;

import rx.Observable;

public interface Data_Interactor {

    Observable<DataModel> getDataList();
}
