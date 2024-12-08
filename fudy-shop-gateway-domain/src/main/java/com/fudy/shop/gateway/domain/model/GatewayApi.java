package com.fudy.shop.gateway.domain.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import lombok.Data;

import java.util.List;

@Data
public class GatewayApi extends Entity {
    /** 接口名 */
    private String apiName;
    /** 方法名 */
    private String methodName;
    /** 参数名 */
    private String[] parameterTypes;
    /** 版本号 */
    private String version;
}
