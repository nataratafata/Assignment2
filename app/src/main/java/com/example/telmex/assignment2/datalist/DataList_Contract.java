package com.example.telmex.assignment2.datalist;

import com.example.telmex.assignment2.model.DataModel;

public interface DataList_Contract {

    interface IView_DataList{

        void _dataList(DataModel dataModels);
        void _dimissProgressDialog();

    }

    interface  Presenter_DataList{

        //Methods called in the view by the presenter instance
        void onBind(IView_DataList iView_dataList);
        void getDataFromAPI();
        void unBind();
    }
}
