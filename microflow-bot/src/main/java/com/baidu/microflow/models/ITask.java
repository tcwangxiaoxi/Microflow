package com.baidu.microflow.models;

/**
 * Created by wangxiaoxi03 on 2017/2/22.
 */
public interface ITask {

    int run();

    int getRetries();

    String getName();

    boolean equalsTo(Object obj);

}
