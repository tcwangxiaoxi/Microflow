package com.baidu.microflow.utils;

import com.google.common.io.Files;
import lombok.extern.java.Log;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by wangxiaoxi03 on 2017/2/22.
 */
@Log
public class IOUtils {

    /**
     * 向文件中写入字节流
     *
     * @param fileName 要写入文件的文件名
     * @param contents 要写入的文件内容
     */
    public static void write(final String fileName, final String contents) {
        checkNotNull(fileName, "Provided file name for writing must NOT be null.");
        checkNotNull(contents, "Unable to write null contents.");
        final File newFile = new File(fileName);
        try {
            Files.write(contents.getBytes("UTF-8"), newFile);
        } catch (IOException fileIoEx) {
            log.warning("ERROR trying to write to file '" + fileName + "' - "
                    + fileIoEx.toString());
        }
    }

    public static void main(String[] args) {

        String dir = "D:\\";//args[0];
        String file = "wang.txt";
        write(dir + file, "wangxiaoxi");
        write(dir + file, "wangxiaoxi2网22");
    }
}
