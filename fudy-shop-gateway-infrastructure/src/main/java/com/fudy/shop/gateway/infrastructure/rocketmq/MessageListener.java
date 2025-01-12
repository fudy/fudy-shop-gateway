package com.fudy.shop.gateway.infrastructure.rocketmq;

import com.fudy.shop.gateway.application.ApiMessageConsumer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@ConditionalOnProperty(name = "app.rocketmq.enabled", havingValue = "true")
@RocketMQMessageListener(topic = "fudy_shop_gateway_topic", consumerGroup = "fudy_shop_gateway_mq_group")
public class MessageListener implements RocketMQListener<String> {
    @Autowired
    private ApiMessageConsumer consumer;

    @Override
    public void onMessage(String message) {
        log.info("Received message: {}" , message);
        consumer.onMessage(message);
    }
}