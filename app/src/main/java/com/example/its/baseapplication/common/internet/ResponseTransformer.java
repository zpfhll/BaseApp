package com.example.its.baseapplication.common.internet;

import android.net.ParseException;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.io.Serializable;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;
import retrofit2.Response;

public class ResponseTransformer {

    /**
     * デフォルトエラー
     */
    public static final int UNKNOWN = 1000;

    /**
     * JSON解析エラー
     */
    public static final int PARSE_ERROR = 1001;

    /**
     * インタネットエラー
     */
    public static final int NETWORK_ERROR = 1002;
    /**
     * タイムアウトエラー
     */
    public static final int HTTP_TIMEOUT_ERROR = 1003;

    public  String mTag = "";

    public  <T> ObservableTransformer<? super Response<? extends Serializable>, ? extends Serializable> handleResult(String tag) {
        mTag = tag;
        return upstream -> upstream
                .onErrorResumeNext(new ErrorResumeFunction<T>())
                .flatMap(new ResponseFunction<T>());
    }

    public  <T> ObservableTransformer<? super Response<? extends Serializable>, ? extends Serializable> handleResult() {

        return upstream -> upstream
                .onErrorResumeNext(new ErrorResumeFunction<T>())
                .flatMap(new ResponseFunction<T>());
    }


    /**
     * リクエストにエラーがある
     *
     * @param <T>
     */
    private class ErrorResumeFunction<T> implements Function<Throwable, ObservableSource<? extends Response<? extends Serializable>>> {

        @Override
        public ObservableSource<? extends Response<? extends Serializable>> apply(Throwable e){
            ApiServerError ex;
            if (e instanceof JsonParseException
                    || e instanceof JSONException
                    || e instanceof ParseException) {
                //JSON解析エラー
                ex = new ApiServerError(mTag,PARSE_ERROR, e.getMessage(),null);
            } else if (e instanceof ConnectException) {
                //インタネットエラー
                ex = new ApiServerError(mTag,NETWORK_ERROR, e.getMessage(),null);
            } else if (e instanceof UnknownHostException || e instanceof SocketTimeoutException|| e instanceof TimeoutException) {
                //タイムアウトエラー
                ex = new ApiServerError(mTag,HTTP_TIMEOUT_ERROR, e.getMessage(),null);
            } else {
                //デフォルトエラー
                ex = new ApiServerError(mTag,UNKNOWN, e.getMessage(),null);
            }
            ex.setTag(mTag);
            return Observable.error(ex);
        }
    }

    /**
     *サーバから返却した結果を解析
     *
     * @param <T>
     */
    private class ResponseFunction<T> implements Function<Response<? extends Serializable>, ObservableSource<? extends Serializable>> {

        @Override
        public ObservableSource<? extends Serializable> apply(Response<? extends Serializable> tResponse){
            int code = tResponse.code();
            String message = tResponse.message();
            if (code == 200) {
                return Observable.just(tResponse.body());
            } else {
                return Observable.error(new ApiServerError(mTag,code,message,tResponse.errorBody()));
            }
        }
    }

}
