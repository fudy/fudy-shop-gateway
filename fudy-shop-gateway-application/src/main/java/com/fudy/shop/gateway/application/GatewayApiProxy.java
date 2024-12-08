package com.fudy.shop.gateway.application;

import com.fudy.shop.gateway.domain.model.GatewayApi;
import org.apache.dubbo.rpc.service.GenericService;

public class GatewayApiProxy {
    private GenericService service;
    private GatewayApi api;

    public GatewayApiProxy(GenericService service, GatewayApi api) {
        this.service = service;
        this.api = api;
    }

    public Object execute(Object[] args) {
        return service.$invoke(api.getMethodName(), api.getParameterTypes(), args);
    }
}
