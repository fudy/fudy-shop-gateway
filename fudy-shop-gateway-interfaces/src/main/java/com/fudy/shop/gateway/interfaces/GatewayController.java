package com.fudy.shop.gateway.interfaces;

import com.fudy.shop.gateway.application.GenericServiceManager;
import com.fudy.shop.gateway.application.MessageSender;
import com.fudy.shop.gateway.application.dto.Result;
import com.fudy.shop.gateway.application.facade.GenericServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayController {
    @Autowired
    private GenericServiceManager manager;
    @Autowired
    private MessageSender messageSender;


    @PostMapping(value = "/api/gateway")
    public Result<Object> execute() {
        Object result = manager.invoke();
        return Result.success(result);

    }

    @PostMapping(value = "/api/message")
    public Result<Object> message() {
        messageSender.send("hello world");
        return Result.success(null);

    }
}
