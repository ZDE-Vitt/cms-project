package com.gem.config;

import com.gem.dialect.DictDialect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: jzhang
 * @WX: 15250420158
 * @Date: 2020/2/27 09:20
 * @Description: 注册方言
 */
@Configuration
public class DialectConfig {
    @Bean
    @ConditionalOnMissingBean
    public DictDialect customDialect() {
        return new DictDialect("自定义的字典方言类");

    }
}
