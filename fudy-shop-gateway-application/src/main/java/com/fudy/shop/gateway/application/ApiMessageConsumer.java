package com.fudy.shop.gateway.application;

import com.fudy.shop.gateway.domain.model.GatewayApi;
import com.fudy.shop.gateway.domain.service.GatewayApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class ApiMessageConsumer {
    @Autowired
    private GatewayApiService service;
    @Autowired
    private GatewayApiProxyFactory factory;

    public void onMessage(String message) {
        log.info("Received message: {}" , message);
        Objects.requireNonNull(message);
        Long id = Long.valueOf(message);
        GatewayApi api = service.findApi(id);
        if (null == api) {
            log.info("remove api in memory, id: {}", id);
            factory.removeApi(id);
        } else {
            log.info("save api in memory, id: {}", id);
            factory.saveApi(api);
        }
    }
}
