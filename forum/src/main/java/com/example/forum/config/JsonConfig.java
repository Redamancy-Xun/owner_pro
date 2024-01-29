package com.example.forum.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 用于配置JSON处理的Spring配置类
 */
//此配置可允许在其他Spring组件中注入或使用名为"json"的Gson bean，以进行JSON序列化和反序列化操作。
@Configuration
public class JsonConfig {

    @Bean("json")
    public Gson json(){
        return new GsonBuilder().serializeNulls().create();
    }

}
