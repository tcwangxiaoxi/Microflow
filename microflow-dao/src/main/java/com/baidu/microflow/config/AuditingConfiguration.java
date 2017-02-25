package com.baidu.microflow.config;

import com.baidu.microflow.entity.base.UserAuditorAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Created by wangxiaoxi03 on 2017/2/21.
 */
@Configuration
@EnableJpaAuditing
public class AuditingConfiguration {

    @Bean
    UserAuditorAware auditorAware() {
        return new UserAuditorAware();
    }
}

