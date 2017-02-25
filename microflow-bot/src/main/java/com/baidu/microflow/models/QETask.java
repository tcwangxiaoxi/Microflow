package com.baidu.microflow.models;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;

@Log
@Getter
@ToString(callSuper = true)
public class QETask extends Task {

    private String cliparameters;
    private String hqlcmd;

    public QETask(String name) {
        super(name);
    }

    public QETask(String name, Integer retries) {
        super(name, retries);
    }

    public void hql(String sqlcmd) {
        this.hqlcmd = sqlcmd;
    }

    public void param(String cliparameters) {
        this.cliparameters = cliparameters;
    }

    public int run() {
        System.out.println("Run HQL: queryengine " + cliparameters + "\n" + hqlcmd);
        return 0;
    }
}
