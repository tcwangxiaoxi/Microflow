package com.baidu.microflow.config;

import com.baidu.microflow.util.Constant;
import com.baidu.microflow.util.FileUtil;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Created by wangxiaoxi03 on 2017/2/22.
 */
@Component
public class PropConfiguration {

    @Value("${script.file.max.in.memory.size:5KB}")
    private String limitScriptSize;

    public long getLimitScriptSize() {
        return FileUtil.parseSize(limitScriptSize);
    }

    public String getLimitScriptSizeStr() {
        return limitScriptSize;
    }
}
