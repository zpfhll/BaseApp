package com.example.its.baseapplication.common.internet;

import io.reactivex.Observable;

public class TCIObservable {

    //APIのID
    private String tag = "";

    private Observable observable;

    public TCIObservable(String tag, Observable observable){
        this.tag = tag;
        this.observable = observable;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Observable getObservable() {
        return observable;
    }

    public void setObservable(Observable observable) {
        this.observable = observable;
    }
}
