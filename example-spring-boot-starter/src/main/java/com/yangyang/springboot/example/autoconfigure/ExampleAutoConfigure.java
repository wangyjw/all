package com.yangyang.springboot.example.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by chenshunyang on 2017/5/23.
 */
@Configuration
@ConditionalOnClass(ExampleService.class)//当classpath下发现该类的情况下进行自动配置
@EnableConfigurationProperties(ExampleServiceProperties.class)
public class ExampleAutoConfigure {
    @Autowired
    private ExampleServiceProperties properties;

    @Bean
    @ConditionalOnMissingBean // 当Spring Context中不存在该Bean时
    @ConditionalOnProperty(prefix = "example.service",value = "enabled",havingValue = "true")//当配置文件中example.service.enabled=true时
    ExampleService exampleService (){
        return  new ExampleService(properties.getPrefix(),properties.getSuffix());
    }

}
