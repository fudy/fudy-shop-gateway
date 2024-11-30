package com.fudy.shop.gateway.domain.repository;

import com.fudy.shop.gateway.domain.model.GatewayApi;

import java.util.List;

public interface GatewayApiRepository {
    List<GatewayApi> findAll();

    GatewayApi find(Long id);

    void save(GatewayApi api);

    void remove(Long id);
}
