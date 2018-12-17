package com.example.telmex.assignment2;

import android.nfc.Tag;
import android.support.design.widget.TabLayout;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.util.Log;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


//import android.widget.Button;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.telmex.assignment2.datalist.DataList_Contract;
import com.example.telmex.assignment2.datalist.DataList_Presenter;
import com.example.telmex.assignment2.model.DataModel;
import com.example.telmex.assignment2.model.dataAdapter;
import com.example.telmex.assignment2.network.ConnectionService;
import com.example.telmex.assignment2.network.Data_Interactor;
import com.example.telmex.assignment2.network.RequestInterface;
import com.example.telmex.assignment2.network.Request_Interface_Classic;
import com.example.telmex.assignment2.network.Request_Interface_Pop;
import com.example.telmex.assignment2.network.Request_Interface_Rock;

import java.io.File;
import java.util.List;
import java.util.Queue;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.example.telmex.assignment2.network.API_Request.BASE_URL;
import static com.example.telmex.assignment2.network.ConnectionService.getConnection;


//import static com.example.telmex.assignment2.SimpleFragment.newInstance;

public class MainActivity extends AppCompatActivity implements DataList_Contract.IView_DataList {

    private DataList_Presenter list_presenter;
    private Data_Interactor interactor;
    private RecyclerView recyclerview;
    private dataAdapter adapter;
    private CompositeDisposable mCompositeDisposable;
    // private ConnectionService service;
    public static File cacheDir;
   // private error throwable;


    //@Override
    public void _List(List<DataModel> cakeModels) {

        //recyclerview(CakeModel)
        recyclerview = (RecyclerView) findViewById(R.id.toolbar);
        recyclerview.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerview.setLayoutManager(layoutManager);

        adapter = new dataAdapter(cakeModels);
        recyclerview.setAdapter(adapter);
    }

    class Demo implements Observer<DataModel> {
        @Override
        public void onSubscribe(Disposable d) {
            Log.d("Boo", "Subbed");
        }

        @Override
        public void onNext(DataModel o) {
            Log.d("Thing", o.getArtistName());
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
        }

        @Override
        public void onComplete() {
            Log.d("Boo", "Done");
        }
    }




        //unchange this code
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            //-----------
           //adapter = new dataAdapter(getConnection());
            mCompositeDisposable = new CompositeDisposable();
           // Observable<DataModel> DataList = getConnection().getDataList();
            //Demo demo = new Demo();
            //DataList.subscribe((rx.Observer<? super DataModel>) demo);

            //List<DataModel> listDataModel = DataList;
            //for (int i = 0; i <= 5; i++)
            //    Log.e("TAG", "index=" + getConnection().getDataList() + "DataList" + DataList);

            //for (int i = 0; i < DataList.size(); i++){ 	Log.e("Tag", DataList.get(i).name) }


            //getConnection().getDataList();
            TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
            tabLayout.addTab(tabLayout.newTab().setText("Rock"));
            tabLayout.addTab(tabLayout.newTab().setText("Classic"));
            tabLayout.addTab(tabLayout.newTab().setText("Pop"));
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

            final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
            final PagerAdapter adapter = new PagerAdapter
                    (getSupportFragmentManager(), tabLayout.getTabCount());
            //
            viewPager.setAdapter(adapter);
            //tabLayout.setupWithViewPager(viewPager);

            //


            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });

            initRecyclerView();
            loadJSON();
            cacheDir = getCacheDir();
        }

        private void initRecyclerView() {
            interactor = new ConnectionService();
            list_presenter = new DataList_Presenter(interactor);
            list_presenter.onBind(this);
            list_presenter.getDataFromAPI();


            //recyclerview = new ConnectionService();
            //recyclerview.setHasFixedSize(true);
            // RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            //recyclerview.setLayoutManager(layoutManager);
        }


        private void loadJSON() {
            Request_Interface_Rock RockInterface = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(Request_Interface_Rock.class);


            Request_Interface_Classic ClassicInterface  = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(Request_Interface_Classic.class);


            Request_Interface_Pop PopInterface = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(Request_Interface_Pop.class);

            //mCompositeDisposable.add(RockInterface.getDataList()
             //              .observeOn(AndroidSchedulers.mainThread())
             //            .subscribeOn(Schedulers.io())
              //         .subscribe(this::handleResponse, this::handleError));


           /* RequestInterface requestInterface = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(RequestInterface.class);*/


           //  mCompositeDisposable.add(requestInterface.getDataList()
           //       .observeOn(AndroidSchedulers.mainThread())
            //     .subscribeOn(Schedulers.io())
            //   .subscribe(this::handleResponse, this::handleError));
        }

   // private void handleError(Throwable throwable) {
   // }

    private void handleResponse(DataModel dataModel) {
    }



    private void handleError(Throwable error) {


        //Log.d(TAG, error.localizedMessage)

   //     Toast.makeText(this, "Error ${error.localizedMessage}", Toast.LENGTH_SHORT).show();
    }


    /*
        private void handleResponse( pojo) {

            mmyPojoObject = pojo;
            mAdapter = new DataAdapter(mmyPojoObject.getObjects());
            mRecyclerView.setAdapter(mAdapter);
        }

        private void handleError(Throwable error) {

            Toast.makeText(this, "Error "+error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }

    */
    @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            //if (id == R.id.action_settings) {
            // return true;
            //return id;
            //}

            return super.onOptionsItemSelected(item);
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            list_presenter.unBind();
        }


        @Override
        public void _dataList(DataModel dataModels) {

       //    recyclerview = findViewById(R.id.toolbar);
        //   recyclerview =.setHasFixedSized

          //  dataModels.getArtistName();
         //   dataModels.getCollectionName();
         //   dataModels.getArtworkUrl60();
          //  dataModels.getTrackPrice();
         //   recyclerview.setAdapter();

            recyclerview = (RecyclerView) findViewById(R.id.toolbar);
            recyclerview.setHasFixedSize(true);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerview.setLayoutManager(layoutManager);

            adapter = new dataAdapter((List<DataModel>) dataModels);
            recyclerview.setAdapter(adapter);

           // mRecycler = findViewById(R.id.recycler_view);
          //  mRecycler.setHasFixedSize(true);
          //  RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
          //  mRecycler.setLayoutManager(layoutManager);

           // mAdapter = new DataAdapter(classicModels.getResults());
           // mRecycler.setAdapter(mAdapter);

            //adapter.onCreateViewHolder(dataModels.getArtistName(),dataModels.getCollectionName(),dataModels.getArtworkUrl60(),dataModels.getTrackPrice());
        }

        @Override
        public void _dimissProgressDialog() {

        }
    }
