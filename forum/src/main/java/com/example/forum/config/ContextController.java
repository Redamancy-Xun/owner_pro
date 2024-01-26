package com.example.forum.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class ContextController {

    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping("/context")
    public String context(){
        String[] beans = applicationContext.getBeanDefinitionNames();
        Arrays.sort(beans);
        String s = "";
        for (String bean : beans)
        {
            //在.contains()中可以添加字符串以过滤我们想要查找的bean
            //最终从IOC容器中找到的bean的全限定名一定包含.contains()里的字符串
            //什么叫"全限定名"？自行百度哈
            if(applicationContext.getBean(bean).getClass().toString().contains("")) {
                s += bean + " of Type :: " + applicationContext.getBean(bean).getClass();
                s += "<br />";
            }
        }
        return s;
    }
}