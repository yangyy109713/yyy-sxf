package com.yyy.rutu.sxfy.service;

import com.yyy.rutu.sxfy.entity.FUser;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQFUserService {

    // 监听消息队列 yangyy.news 的内容：只要这个队列就消息进来，就会触发
    // 要使注解 RabbitListener生效，需要开启RabbitMQ注解：在Application类上使用 @EnableRabbit
    @RabbitListener(queues = "yangyy.news")
    public void receive(FUser fUser){
        System.out.println("yangyy.news 收到新消息了..."+ fUser);
    }

    // 监听消息队列 yangyy 的消息头、消息体
    // 当队列中有消息时，启动Application / 执行测试用例 就能监听到
    @RabbitListener(queues = "yangyy")
    public void receiveMsg(Message message){
        System.out.println("yangyy message body："+ message.getBody());
        System.out.println("yangyy message properties："+ message.getMessageProperties());
        /*
        测试结果：
        yangyy message body：[B@2426809e
        yangyy message properties：MessageProperties [headers={__TypeId__=java.lang.String}, contentType=application/json, contentEncoding=UTF-8, contentLength=0, receivedDeliveryMode=PERSISTENT, priority=0, redelivered=false, receivedExchange=exchange.fanout, receivedRoutingKey=, deliveryTag=1, consumerTag=amq.ctag-Ypa8MwV9MhP-T185iWZ6NQ, consumerQueue=yangyy]
         */
    }
}
