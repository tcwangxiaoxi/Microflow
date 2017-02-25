package com.baidu.microflow.generator.bo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

/**
 * Created by wangxiaoxi03 on 2017/2/22.
 */
@Data
public class Script {

    /**
     * 脚本资源标识
     */
    private String name;

    /**
     * 脚本原始代码
     * ByCode的话就是脚本，ByResource的话就是资源，需要读库获取脚本
     */
    private String originalScript;
    /**
     * 任务类型（区分是 ByCode 直接执行，还是 ByResource 引用脚本文件执行）
     */
    private ScriptType scriptType;

    /**
     * 任务类型（区分是 ByCode 直接执行，还是 ByResource 引用脚本文件执行）
     */
    public static enum ScriptType {
        Code, Resource
    }

    public Script(ScriptType scriptType, String script) {
        switch (scriptType) {
            case Code: {
                this.name = UUID.randomUUID().toString();
                this.originalScript = script;
                this.scriptType = scriptType;
                break;
            }
            case Resource: {
                this.name = script;
                this.scriptType = scriptType;
                break;
            }
            default: {
                throw new IllegalArgumentException("目前不支持该类型脚本!");
            }
        }

    }

}
