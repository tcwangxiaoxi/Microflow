package com.baidu.microflow.utils;

import java.util.UUID;

/**
 * Created by wangxiaoxi03 on 2017/2/22.
 */
public class IdUtils {

    public static String genUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
    }
}
