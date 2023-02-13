package cn.itcast.user.web;

import cn.itcast.user.config.PatternProperties;
import cn.itcast.user.pojo.User;
import cn.itcast.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
@RequestMapping("/user")
//@RefreshScope
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PatternProperties patternProperties;

    @GetMapping("/prop")
    public PatternProperties properties() {
        return patternProperties;
    }

    // 注入nacos中的配置属性
//    @Value("${pattern.dateformat}")
//    private String dateformat;

    /**
     * 路径：/user/now
     * 测试user服务是否从Nacos上读取到日期时间格式配置信息
     * @return
     */
    @GetMapping("/now")
    public String now() {
//        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(dateformat));
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(patternProperties.getDateformat()));
    }

    /**
     * 路径： /user/110
     *
     * @param id 用户id
     * @return 用户
     */
    @GetMapping("/{id}")
    public User queryById(@PathVariable("id") Long id,
                          @RequestHeader(value = "Truth", required = false) String truth) {
        // 打印请求头中获取的truth
        System.out.println("truth: " + truth);
        return userService.queryById(id);
    }

}
