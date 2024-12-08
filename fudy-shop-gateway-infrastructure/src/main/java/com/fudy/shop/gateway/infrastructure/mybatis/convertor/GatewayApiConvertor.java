package com.fudy.shop.gateway.infrastructure.mybatis.convertor;

import com.fudy.shop.gateway.domain.model.GatewayApi;
import com.fudy.shop.gateway.infrastructure.mybatis.data.GatewayApiDO;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GatewayApiConvertor {
    List<GatewayApi> toGatewayApis(List<GatewayApiDO> list);

    GatewayApi toGatewayApi(GatewayApiDO apiDO);

    GatewayApiDO toGatewayApiDO(GatewayApi api);

    default String[] toParameterTypes(String parameterTypes) {
        if (StringUtils.isBlank(parameterTypes)) {
            return new String[]{};
        }
        return parameterTypes.split(",");
    }

    default String toParameterTypes(String[] parameterTypes){
        if (null == parameterTypes || parameterTypes.length == 0) {
            return null;
        }
        return StringUtils.join(parameterTypes, ",");
    }

}
