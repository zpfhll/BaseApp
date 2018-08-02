package com.example.its.baseapplication.common.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class SimpleEntity {
    @Id
    private Long id;

    private String name;

    @Generated(hash = 1665101525)
    public SimpleEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 1682830787)
    public SimpleEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SimpleEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
