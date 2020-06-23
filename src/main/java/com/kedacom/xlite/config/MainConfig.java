package com.kedacom.xlite.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangshuxuan
 * @date 2020/5/12 10:55
 */
@Configuration
@ComponentScan("com.kedacom.xlite.model")
public class MainConfig {


    public static void main(String[] args) {
        System.out.println(11111111);
        System.out.println(22222222);
    }

}
