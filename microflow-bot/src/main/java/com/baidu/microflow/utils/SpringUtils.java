package com.baidu.microflow.utils;

import com.baidu.microflow.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

/**
 * Created by wangxiaoxi03 on 2017/2/26.
 */
public class SpringUtils {

    private static ApplicationContext ctx;

    public static void initSpringApplicationContext(Class<?> mainClass, String[] args) {
        ctx = SpringApplication.run(mainClass, args);
    }

    public static <T> T getBean(Class<T> tClass) {
        return ctx.getBean(tClass);
    }
}
