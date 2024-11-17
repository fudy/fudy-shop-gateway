package com.fudy.shop.gateway.infrastructure.mybatis.data;

import lombok.Data;

import java.util.Date;

@Data
public class CommonDO {
    /** 主键 */
    private Long id;
    /** 创建时间 */
    private Date createdTime;
    /** 修改时间 */
    private Date modifiedTime;
}
