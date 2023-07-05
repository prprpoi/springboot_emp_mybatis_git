package com.bluebird;

import org.dom4j.io.SAXReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

@ServletComponentScan
@SpringBootApplication
public class SpringbootEmpMybatisApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringbootEmpMybatisApplication.class, args);
    }

}
