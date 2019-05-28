package com.thinvent.nj.identify;


import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;

/**
 * 应用程序入口
 * @author administrator
 */
@SpringBootApplication(exclude = {
        MybatisAutoConfiguration.class,
        RedisAutoConfiguration.class
})
@ComponentScan("com.thinvent.nj")
public class Application {
    public static void main(String[] args) throws IOException {
        SpringApplication application = new SpringApplication(Application.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }
}
