package com.fudy.shop.gateway.infrastructure.dubbo;

import com.fudy.shop.gateway.application.facade.GenericServiceFacade;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Component
public class GenericConsumer implements GenericServiceFacade {
    private final ApplicationConfig applicationConfig;
    private final RegistryConfig registryConfig;

    @Autowired
    public GenericConsumer(ApplicationConfig applicationConfig, RegistryConfig registryConfig) {
        this.applicationConfig = applicationConfig;
        this.registryConfig = registryConfig;
    }

    public Object invoke() {
        // 创建ReferenceConfig实例
        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        reference.setApplication(applicationConfig);
        reference.setRegistry(registryConfig);
        reference.setInterface("com.fudy.shop.item.api.ItemService"); // 动态设置接口名称
        reference.setGeneric(true); // 泛化接口
        // 获取泛化调用的代理对象
        GenericService genericService = reference.get();
        Map<String, Object> args = new HashMap<>();
        args.put("itemId", 1L);
        // 调用服务
        try {
            return genericService.$invoke(
                    "getItem", // 方法名
                    new String[]{"com.fudy.shop.item.api.dto.ItemQuery"}, // 参数类型
                    new Object[]{args} // 参数值
            );
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}
