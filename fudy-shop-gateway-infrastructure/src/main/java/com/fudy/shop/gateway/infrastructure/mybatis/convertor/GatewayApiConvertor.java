package com.fudy.shop.gateway.infrastructure.mybatis.convertor;

import com.fudy.shop.gateway.domain.model.GatewayApi;
import com.fudy.shop.gateway.infrastructure.mybatis.data.GatewayApiDO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GatewayApiConvertor {
    List<GatewayApi> toGatewayApis(List<GatewayApiDO> list);

    GatewayApi toGatewayApi(GatewayApiDO apiDO);

    GatewayApiDO toGatewayApiDO(GatewayApi api);
}
