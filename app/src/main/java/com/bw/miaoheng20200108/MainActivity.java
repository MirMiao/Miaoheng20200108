package com.bw.miaoheng20200108;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.bw.miaoheng20200108.adapter.CartAdapter;
import com.bw.miaoheng20200108.api.Api;
import com.bw.miaoheng20200108.api.CartApiService;
import com.bw.miaoheng20200108.entity.CartEntity;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv);

        initView();
        initData();
    }
    private void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void initData() {
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        CartApiService cartApiService = retrofit.create(CartApiService.class);
        cartApiService.getData("13387","157837851751313387")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CartEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    /**
                     * 成功
                     * @param cartEntity
                     */
                    @Override
                    public void onNext(CartEntity cartEntity) {
                        List<CartEntity.ResultBean> result = cartEntity.getResult();
                        CartAdapter cartAdapter = new CartAdapter(MainActivity.this, result);
                        recyclerView.setAdapter(cartAdapter);
                    }

                    /**
                     * 失败
                     * @param e
                     */
                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
