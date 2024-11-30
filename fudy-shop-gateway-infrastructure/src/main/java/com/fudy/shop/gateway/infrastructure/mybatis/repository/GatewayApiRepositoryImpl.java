package com.fudy.shop.gateway.infrastructure.mybatis.repository;

import com.fudy.shop.gateway.domain.model.GatewayApi;
import com.fudy.shop.gateway.domain.repository.GatewayApiRepository;
import com.fudy.shop.gateway.infrastructure.mybatis.convertor.GatewayApiConvertor;
import com.fudy.shop.gateway.infrastructure.mybatis.data.GatewayApiDO;
import com.fudy.shop.gateway.infrastructure.mybatis.mapper.GatewayApiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GatewayApiRepositoryImpl implements GatewayApiRepository {
    @Autowired
    private GatewayApiMapper mapper;
    @Autowired
    private GatewayApiConvertor convertor;

    @Override
    public List<GatewayApi> findAll() {
        List<GatewayApiDO> list = mapper.selectList();
        return convertor.toGatewayApis(list);
    }

    @Override
    public GatewayApi find(Long id) {
        GatewayApiDO apiDO = mapper.select(id);
        return convertor.toGatewayApi(apiDO);
    }

    @Override
    public void save(GatewayApi api) {
        GatewayApiDO apiDO = convertor.toGatewayApiDO(api);
        if (api.hasId()) {
            mapper.update(apiDO);
        } else {
            mapper.insert(apiDO);
        }
    }

    @Override
    public void remove(Long id) {
        mapper.delete(id);
    }
}
