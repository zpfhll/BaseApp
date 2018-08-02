package com.example.its.baseapplication.gamen.base;

import java.util.Map;

public class EventBusEntity{

    private String id;
    private Map<String,Object> data;

    public EventBusEntity(String id, Map<String, Object> data) {
        this.id = id;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> datas) {
        this.data = datas;
    }

    @Override
    public String toString() {
        return "EventBusEntity{" +
                "id='" + id + '\'' +
                ", data=" + data.toString() +
                '}';
    }
}
