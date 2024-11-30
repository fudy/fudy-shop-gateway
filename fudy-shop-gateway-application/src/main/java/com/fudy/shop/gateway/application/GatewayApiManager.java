package com.fudy.shop.gateway.application;

import com.fudy.shop.gateway.application.assembler.GatewayApiAssembler;
import com.fudy.shop.gateway.application.dto.CreateApiCommand;
import com.fudy.shop.gateway.application.dto.GatewayApiDTO;
import com.fudy.shop.gateway.application.dto.UpdateApiCommand;
import com.fudy.shop.gateway.domain.model.GatewayApi;
import com.fudy.shop.gateway.domain.service.GatewayApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GatewayApiManager {
    @Autowired
    private GatewayApiService service;
    @Autowired
    private GatewayApiAssembler assembler;

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
        return assembler.toGatewayApiDTO(api);
    }

    public void removeApi(Long id) {
        service.removeApi(id);
    }

    public void updateApi(Long id, UpdateApiCommand updateApiCommand) {
        GatewayApi gatewayApi = assembler.toGatewayApi(updateApiCommand);
        gatewayApi.setId(id);
        service.updateApi(gatewayApi);
    }
}
