package com.fudy.shop.gateway.interfaces;

import com.fudy.shop.gateway.application.GenericServiceManager;
import com.fudy.shop.gateway.application.dto.Result;
import com.fudy.shop.gateway.application.facade.GenericServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayController {
    @Autowired
    private GenericServiceManager manager;

    @PostMapping(value = "/api/gateway")
    public Result<Object> execute() {
        Object result = manager.invoke();
        return Result.success(result);

    }
}
