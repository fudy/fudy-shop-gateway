package com.fudy.shop;


import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.fudy.shop.gateway.Application;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest(classes = Application.class)
public class ApplicationTest {
    @Test
    public void testDubboInvoke() {
        // 创建 ApplicationConfig 实例
        ApplicationConfig application = new ApplicationConfig();
        application.setName("generic-consumer");

        // 创建 ReferenceConfig 实例
        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        reference.setApplication(application);
        reference.setInterface("com.fudy.shop.item.api.ItemService"); // 设置服务接口名称
        reference.setGeneric(true); // 设置为泛化引用

        // 获取泛化服务
        GenericService genericService = reference.get();

        Map<String, Object> args = new HashMap<>();
        args.put("itemId", 1L);
        // 调用服务方法
        Object result = genericService.$invoke(
                "getItem", // 方法名
                new String[]{"com.fudy.shop.item.api.dto.ItemQuery"}, // 参数类型
                new Object[]{args} // 参数值
        );

        System.out.println(result); // 输出结果
    }
}
