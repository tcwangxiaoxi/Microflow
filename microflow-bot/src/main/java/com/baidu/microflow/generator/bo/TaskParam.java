package com.baidu.microflow.generator.bo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wangxiaoxi03 on 2017/2/22.
 */
@Data
public class TaskParam {

    /**
     * 任务名称
     */
    private String name;

    /**
     * 执行环境上下文
     */
    private Map<String, String> envConext;
    /**
     * 原始脚本
     */
    private Script originalScript;
    /**
     * 失败的重试次数
     */
    private int retries;

    /**
     * 前置脚本列表
     */
    private List<Script> preScripts = new ArrayList<>();
    /**
     * 后置脚本列表
     */
    private List<Script> postScripts = new ArrayList<>();
}
