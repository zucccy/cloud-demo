package cn.itcast.order.service;

import cn.itcast.order.clients.UserClient;
import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import cn.itcast.order.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    // 注入UserClient
    @Autowired
    private UserClient userClient;


    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        // 2.使用Feign进行远程调用获取用户信息
        User user = userClient.findById(order.getUserId());
        order.setUser(user);
        // 4.返回
        return order;
    }

    // 先将RestTemplate注入
//    @Autowired
//    private RestTemplate restTemplate;

//    public Order queryOrderById(Long orderId) {
//        // 1.查询订单
//        Order order = orderMapper.findById(orderId);
//        // 2.使用RestTemplate进行远程调用获取用户信息
//        // 2.1.自定义url路径
//        String url = "http://userservice/user/" + order.getUserId();
////        String url = "http://localhost:8081/user/" + order.getUserId();
//        // 参数中有个responseType，是Class<T>类型，是为了将返回的json反序列化成需要的class对象，这里写User.class
//        User user = restTemplate.getForObject(url, User.class);
//        order.setUser(user);
//        // 4.返回
//        return order;
//    }
}
