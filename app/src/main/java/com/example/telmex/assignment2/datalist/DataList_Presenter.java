package com.example.telmex.assignment2.datalist;

import android.util.Log;

import com.example.telmex.assignment2.model.DataModel;
import com.example.telmex.assignment2.network.Data_Interactor;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DataList_Presenter implements DataList_Contract.Presenter_DataList{

    private DataList_Contract.IView_DataList iView_dataList;
    private Data_Interactor  Data_interactor;

    private static final String TAG = "Result";

    public DataList_Presenter(Data_Interactor Data_interactor){
        this.Data_interactor = Data_interactor;
    }

    @Override
    public void onBind(DataList_Contract.IView_DataList iView_dataList) {
        this.iView_dataList = iView_dataList;
    }



    @Override
    public void getDataFromAPI() {
        Data_interactor.getDataList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DataModel dataModels) {
                        iView_dataList._dataList(dataModels);
                        Log.e(TAG, "Aquí");
                        Log.e(TAG, dataModels.getArtistName());
//Recycler View
                    }
                });



    }

    @Override
    public void unBind() {
        this.iView_dataList =null;

    }
}
