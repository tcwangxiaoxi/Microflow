package com.baidu.microflow.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wangxiaoxi03 on 2017/2/24.
 */
@Data
@Configuration
public class Constant {

    @Value("${com.baidu.microflow.generator.bashFileParentPath}")
    private String bashFileParentPath;
}
