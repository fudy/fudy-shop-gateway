package com.fudy.shop.gateway.domain.service;

import com.fudy.shop.gateway.domain.model.GatewayApi;
import com.fudy.shop.gateway.domain.repository.GatewayApiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GatewayApiService {
    private GatewayApiRepository repository;

    @Autowired
    public GatewayApiService(GatewayApiRepository repository) {
        this.repository = repository;
    }

    public List<GatewayApi> findAllApiList() {
        return repository.findAll();
    }

    public GatewayApi findApi(Long id) {
        return repository.find(id);
    }

    public void createApi(GatewayApi api) {
        repository.save(api);
    }

    public void removeApi(Long id) {
        repository.remove(id);
    }

    public void updateApi(GatewayApi gatewayApi) {
        repository.save(gatewayApi);
    }
}
