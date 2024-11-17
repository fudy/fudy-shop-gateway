package com.fudy.shop.gateway.infrastructure.mybatis.data;

import lombok.Data;

@Data
public class GatewayApiDO extends CommonDO {
    /** 接口名 */
    private String apiName;
    /** 方法名 */
    private String methodName;
    /** 参数名 */
    private String parameterTypes;
    /** 版本号 */
    private String version;
}
