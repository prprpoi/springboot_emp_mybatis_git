package com.bluebird.config;

import org.dom4j.io.SAXReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfig {

    @Bean//声明第三方Bean,将当前方法的返回值对象交给IOC容器管理,称为IOC容器的Bean对象
    public SAXReader test1() {
        return new SAXReader();
    }

}
