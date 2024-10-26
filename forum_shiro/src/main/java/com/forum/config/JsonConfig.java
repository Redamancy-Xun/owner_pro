package com.forum.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonConfig {

    @Bean("json")
    public Gson json(){
        return new GsonBuilder().serializeNulls().create();
    }

}
