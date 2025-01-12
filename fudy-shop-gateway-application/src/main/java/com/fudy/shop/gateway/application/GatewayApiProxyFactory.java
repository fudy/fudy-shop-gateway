package com.fudy.shop.gateway.application;

import com.fudy.shop.gateway.domain.model.GatewayApi;
import com.fudy.shop.gateway.domain.service.GatewayApiService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class GatewayApiProxyFactory {
    @Autowired
    private GatewayApiService service;
    @Autowired
    private ApplicationConfig applicationConfig;
    @Autowired
    private RegistryConfig registryConfig;

    Map<Long, GatewayApiProxy> apiCache = new HashMap<>();

    @PostConstruct
    public void init() {
        List<GatewayApi> list = service.findAllApiList();
        for (GatewayApi api : list) {
            GatewayApiProxy apiProxy = newGatewayApiProxy(api);
            apiCache.put(api.getId(), apiProxy);
        }
    }

    private GatewayApiProxy newGatewayApiProxy(GatewayApi api) {
        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        reference.setApplication(applicationConfig);
        reference.setRegistry(registryConfig);
        reference.setInterface(api.getApiName()); // 动态设置接口名称
        reference.setGeneric(true); // 泛化接口
        reference.setVersion(api.getVersion());
        try {
            return new GatewayApiProxy(reference.get(), api);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public GatewayApiProxy getApi(Long id) {
        return apiCache.get(id);
    }

    /** 删除 */
    public void removeApi(Long id) {
        apiCache.remove(id);
    }

    /** 保存 */
    public void saveApi(GatewayApi api) {
        if (null != api) {
            apiCache.put(api.getId(), newGatewayApiProxy(api));
        }
    }

}
