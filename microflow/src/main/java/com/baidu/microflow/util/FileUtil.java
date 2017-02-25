package com.baidu.microflow.util;

import org.springframework.util.Assert;

/**
 * Created by wangxiaoxi03 on 2017/2/22.
 */
public class FileUtil {

    /**
     * 文件大小格式的转化
     *
     * @param size 10KB 或者 10MB
     * @return 字节大小
     */
    public static long parseSize(String size) {
        Assert.hasLength(size, "Size must not be empty");
        size = size.toUpperCase();
        return size.endsWith("KB") ? Long.valueOf(size.substring(0, size.length() - 2)).longValue() * 1024L : (size.endsWith("MB") ? Long.valueOf(size.substring(0, size.length() - 2)).longValue() * 1024L * 1024L : Long.valueOf(size).longValue());
    }
}
