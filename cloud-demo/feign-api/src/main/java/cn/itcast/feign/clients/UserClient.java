package cn.itcast.feign.clients;

import cn.itcast.feign.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description: TODO
 * @Author: ChenYun
 * @CreateDate: 2023/1/14 15:23
 * @Version 1.0
 */
// 添加服务名说明去哪里调用微服务，configuration是Feign自定义配置，
// 这里因为已经使用了全局配置，则将局部配置注释
//@FeignClient(value = "userservice",configuration = DefaultFeignConfiguration.class)
@FeignClient(value = "userservice")
public interface UserClient {

    @GetMapping("/user/{id}")
    User findById(@PathVariable("id") Long id);
}
