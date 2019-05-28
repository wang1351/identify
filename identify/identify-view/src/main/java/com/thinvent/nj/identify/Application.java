package com.thinvent.nj.identify;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * 应用程序入口
 * @author liupeijun
 */
@SpringBootApplication(exclude = {
        RedisAutoConfiguration.class,
})
@ComponentScan("com.thinvent.nj")
public class Application {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }
}
