package com.fudy.shop.gateway.infrastructure.rocketmq;

import com.fudy.shop.gateway.application.MessageSender;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "app.rocketmq.enabled", havingValue = "true")
public class RocketMQSender implements MessageSender {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void send(String message) {
        rocketMQTemplate.convertAndSend("fudy_shop_gateway_topic", message);
    }
}
