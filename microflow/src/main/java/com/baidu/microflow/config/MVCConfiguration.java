package com.baidu.microflow.config;

import com.baidu.microflow.util.Constant;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.MultipartConfigElement;

/**
 * Created by wangxiaoxi03 on 2017/2/21.
 */
@Configuration
public class MVCConfiguration {

    @Bean
    public MultipartConfigElement getMultipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 设置文件大小限制 ,超了，页面会抛出异常信息，这时候就需要进行异常信息的处理了;
        factory.setMaxFileSize(Constant.FILE_MAX_IN_MEMORY_SIZE); //KB,MB
        // 设置总上传数据总大小
        //factory.setMaxRequestSize("256KB");
        // Sets the directory location where files will be stored.
        //factory.setLocation("路径地址");
        return factory.createMultipartConfig();
    }
}
