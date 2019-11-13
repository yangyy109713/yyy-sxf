package com.yyy.rutu.sxfy;

import com.yyy.rutu.sxfy.entity.FUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMQTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin; //创建和删除Queue、Exchange、Binding

    //创建交换器
    @Test
    public void amqpAdminCreateExchange(){
        //amqpAdmin.declareXXX 表示创建
        amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));//创建一个单播交换器
        System.out.println("create exchange success！");
    }


    // 创建队列
    @Test
    public void amqpAdminCreateQueue(){
        //amqpAdmin.declareXXX 表示创建
        amqpAdmin.declareQueue(new Queue("amqpadmin.queue", true));//创建一个队列
        System.out.println("create queue success！");
    }

    // 创建绑定规则
    @Test
    public void amqpAdminCreateBinding(){
        //amqpAdmin.declareXXX 表示创建
        Binding binding = new Binding("amqpadmin.queue", Binding.DestinationType.QUEUE,
                "amqpadmin.exchange", "amqpadmin.routingKey", null);
        amqpAdmin.declareBinding(binding);//创建一个队列
        System.out.println("create binding success！");

        // 对应的，还有删除操作，以.delete开头
        // amqpAdmin.deleteXXX
    }



    /**
     * 消息单播（Direct：点对点）
     * 使用已创建的交换器 exchange.direct
     */
    @Test
    public void sendMsgDirect(){
        // 需要传入一个Message：自定义消息体内容和消息头
        // 使用API方式：rabbitTemplate.send(exchange, routeKey, message);

        // rabbitTemplate.convertAndSend(exchange, routeKey, object);
        // object默认当成消息体，只需要传入要发送的对象，自动序列化后发给rabbitmq
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "This is a message");
        map.put("data", Arrays.asList("hello world", 95, true));
        //rabbitTemplate.convertAndSend("exchange.direct", "yangyy.news", map);

        // 对象被默认序列化后发送出去
        rabbitTemplate.convertAndSend("exchange.direct", "yangyy.news",
                new FUser("rabbitUser1", "123456", "rabbitMq1"));
    }

    // 接收数据：如何将数据自动转为JSON发出去？
    // 自定义消息转换器
    @Test
    public void receive(){
        // 根据队列名称，获取消息：接收消息成功，会清空队列中的消息（等价于Ack Message requeue false）
        Object o = rabbitTemplate.receive("yangyy.news");
        System.out.println(o.getClass());
        System.out.println(o);
    }


    /**
     * 消息多播（Fanout：一对多）
     * 使用已创建的交换器 exchange.fanout
     */
    @Test
    public void sendMsgFanout(){
        //rabbitTemplate.convertAndSend("sxfy.news", "指定路由键fanout模式");
        rabbitTemplate.convertAndSend("exchange.fanout", "", "this is a fanout message1.");
    }
}
