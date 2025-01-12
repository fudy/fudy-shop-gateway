package com.fudy.shop.gateway.infrastructure.rocketmq;

import com.fudy.shop.gateway.application.ApiMessageConsumer;
import com.fudy.shop.gateway.application.MessageSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@ConditionalOnProperty(name = "app.rocketmq.enabled", havingValue = "false")
public class NativeSender implements MessageSender {
    @Autowired
    private ApiMessageConsumer consumer;

    @Override
    public void send(String message) {
        log.info("send message: {}", message);
        consumer.onMessage(message);
    }
}
