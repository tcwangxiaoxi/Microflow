package com.baidu.microflow.utils;

import lombok.Data;

/**
 * Created by wxx on 2017/2/22.
 */
@Data
public class CommandResult {

    public static final int EXIT_VALUE_TIMEOUT = -1;

    private String output;

    private int exitValue;

    private String error;
}
