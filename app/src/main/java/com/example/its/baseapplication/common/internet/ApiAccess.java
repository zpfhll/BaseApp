package com.example.its.baseapplication.common.internet;

import android.annotation.SuppressLint;

import com.example.its.baseapplication.BuildConfig;
import com.example.its.baseapplication.common.internet.request.SimpleRequest;
import com.example.its.baseapplication.common.utils.TCILog;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiAccess {
    public static Retrofit retrofit;
    public ApiAccess() {
        if (null == retrofit) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor(TCILog::e);
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(logging).connectTimeout(BuildConfig.REQUEST_TIMEOUT, TimeUnit.SECONDS);
            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.DOMAIN)
                    .client(builder.build())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
    }

    //TCI利用例
    public TCIObservable simpleRequest(SimpleRequest simpleInfo){
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<Response<SimpleRequest>> observable = apiService.simpleRequest(simpleInfo.getBankCode(),simpleInfo.getReceiveAccountFlg());
        return new TCIObservable("simple",observable);
    }




    @SuppressLint("CheckResult")
    public Observable sendSingleApi(TCIObservable tciObservable){
        SchedulerProvider schedulerProvider = SchedulerProvider.getInstance();
        return tciObservable.getObservable().compose(schedulerProvider.applySchedulers())
                .compose(new ResponseTransformer().handleResult(tciObservable.getTag()));
    }


    /**
     *List順でAPIを呼び出す
     * 全てAPIの結果が返却した後で、callBackを呼び出す
     * 何でもAPIにエラーを返却したら、callBackを呼び出し、以降のAPIを呼び出しない
     */
    @SuppressLint("CheckResult")
    public void sendApiArray(List<TCIObservable> ls, RetrofitCallBack callBack){
        SchedulerProvider schedulerProvider = SchedulerProvider.getInstance();
        List<Object> resultList = new ArrayList<>();
        Observable observable = Observable.fromIterable(ls)
                .concatMap(
                        request -> request.getObservable()
                                .compose(schedulerProvider.applySchedulers())
                                .compose(new ResponseTransformer().handleResult(request.getTag()))
                );
        observable.subscribe(
                result -> {
                    resultList.add(result);
                    TCILog.e("result:" + result.toString());
                    if(resultList.size() == ls.size()) {
                        callBack.result(resultList);
                    }
                }, throwable -> {
                    resultList.add(throwable);
                    callBack.result(resultList);
                    TCILog.e("throwable:" + throwable.toString());
                    observable.unsubscribeOn(AndroidSchedulers.mainThread());
                }
        );
    }

    /**
     *非同期でAPIを呼び出す
     * 全てAPIの結果が返却した後で、callBackを呼び出す
     * @param ls
     * @param callBack
     */
    @SuppressLint("CheckResult")
    public void sendApiAsyn(List<TCIObservable> ls,RetrofitCallBack callBack){
        List<Object> resultList = new ArrayList<>();
        for (TCIObservable observable:ls){
            sendSingleApi(observable)
                    .subscribe(result -> {
                        resultList.add(result);
                        TCILog.e("result:" + result.toString());
                        if(resultList.size() == ls.size()){
                            callBack.result(resultList);
                        }
                    }, throwable -> {
                        resultList.add(throwable);
                        TCILog.e("throwable:" + throwable.toString());
                        if(resultList.size() == ls.size()){
                            callBack.result(resultList);
                        }
                    });
        }
    }



}
