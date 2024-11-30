package com.fudy.shop.gateway.application.assembler;

import com.fudy.shop.gateway.application.dto.CreateApiCommand;
import com.fudy.shop.gateway.application.dto.GatewayApiDTO;
import com.fudy.shop.gateway.application.dto.UpdateApiCommand;
import com.fudy.shop.gateway.domain.model.GatewayApi;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GatewayApiAssembler {

    GatewayApiDTO toGatewayApiDTO(GatewayApi gatewayApi);

    List<GatewayApiDTO> toGatewayApiDTOs(List<GatewayApi> gatewayApis);

    GatewayApi toGatewayApi(CreateApiCommand command);

    GatewayApi toGatewayApi(UpdateApiCommand command);
}
