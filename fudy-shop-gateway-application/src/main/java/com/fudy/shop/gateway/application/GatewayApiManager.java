package com.fudy.shop.gateway.application;

import com.fudy.shop.gateway.application.assembler.GatewayApiAssembler;
import com.fudy.shop.gateway.application.dto.CreateApiCommand;
import com.fudy.shop.gateway.application.dto.ExecuteApiCommand;
import com.fudy.shop.gateway.application.dto.GatewayApiDTO;
import com.fudy.shop.gateway.application.dto.UpdateApiCommand;
import com.fudy.shop.gateway.domain.model.GatewayApi;
import com.fudy.shop.gateway.domain.service.GatewayApiService;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GatewayApiManager {
    @Autowired
    private GatewayApiService service;
    @Autowired
    private GatewayApiAssembler assembler;
    @Autowired
    private GatewayApiProxyFactory factory;
    @Autowired
    private MessageSender messageSender;

    public List<GatewayApiDTO> findAllApiList() {
        List<GatewayApi> list = service.findAllApiList();
        return assembler.toGatewayApiDTOs(list);
    }

    public GatewayApiDTO findApi(Long id) {
        GatewayApi api = service.findApi(id);
        return assembler.toGatewayApiDTO(api);
    }

    public GatewayApiDTO createApi(CreateApiCommand createApiCommand) {
        GatewayApi api = assembler.toGatewayApi(createApiCommand);
        service.createApi(api);
        // factory.saveApi(api);
        messageSender.send(String.valueOf(api.getId()));
        return assembler.toGatewayApiDTO(api);
    }

    public void removeApi(Long id) {
        service.removeApi(id);
        messageSender.send(String.valueOf(id));
        //factory.removeApi(id);
    }

    public void updateApi(Long id, UpdateApiCommand updateApiCommand) {
        GatewayApi gatewayApi = assembler.toGatewayApi(updateApiCommand);
        gatewayApi.setId(id);
        service.updateApi(gatewayApi);
        // factory.saveApi(gatewayApi);
        messageSender.send(String.valueOf(id));
    }

    public Object execute(Long id, ExecuteApiCommand command) throws Exception {
        GatewayApiProxy apiProxy = factory.getApi(id);
        return apiProxy.execute(command.getArgs());
    }
}
