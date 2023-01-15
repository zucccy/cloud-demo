package cn.itcast.order.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * @Description: Feign自定义配置类
 * @Author: ChenYun
 * @CreateDate: 2023/1/15 10:47
 * @Version 1.0
 */
public class DefaultFeignConfiguration {
    @Bean
    public Logger.Level logLevel() {
        return Logger.Level.BASIC;
    }
}
