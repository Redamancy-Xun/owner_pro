package undestiny.onwer_pre.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBeans {

    @Bean("autowire")
    public Integer autowire_bean() {
        return 1;
    }

    @Bean("resource_name1")
    public String resource_bean1() {
        return "this is resource_bean1";
    }

    @Bean("resource_name2")
    public String resource_bean2() {
        return "this is resource_bean2";
    }
}
