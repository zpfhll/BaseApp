package com.example.its.baseapplication.common.internet;

import com.example.its.baseapplication.BuildConfig;
import com.example.its.baseapplication.common.internet.request.SimpleRequest;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HTTP;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ApiService {

    //TCI用例
    @HTTP(method = "PUT",path = BuildConfig.EXAMPLE_API_PATH,hasBody = true)
    @Headers("Content-Type: application/x-www-form-urlencoded;charset=UTF-8")
    @FormUrlEncoded
    Observable<Response<SimpleRequest>> simpleRequest(@Path("bank_code") String code, @Field("receiveAccountFlg") String flg);




}
