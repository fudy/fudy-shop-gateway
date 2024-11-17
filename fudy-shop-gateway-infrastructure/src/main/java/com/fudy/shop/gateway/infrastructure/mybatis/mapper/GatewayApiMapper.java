package com.fudy.shop.gateway.infrastructure.mybatis.mapper;

import com.fudy.shop.gateway.infrastructure.mybatis.data.GatewayApiDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface GatewayApiMapper {
    String SELECT_COLUMNS = "id,created_time,modified_time,api_name,method_name,parameter_types,version";

    /** 查询 */
    @Select("select " + SELECT_COLUMNS + " from gateway_api where id = #{id}")
    GatewayApiDO select(@Param("id") Long id);

    @Select("select " + SELECT_COLUMNS + " from gateway_api")
    List<GatewayApiDO> selectList();

    /** 插入 */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into gateway_api(created_time,modified_time,api_name,method_name,parameter_types,version) " +
            "values(now(), now(), #{apiName}, #{methodName}, #{parameterTypes}, #{version})")
    void insert(GatewayApiDO userDO);

    /** 更新 */
    @Update("update gateway_api set modified_time=now(), api_name=#{apiName}, method_name=#{methodName}, " +
            "parameter_types=#{parameterTypes}, version=#{version} where id = #{id}")
    void update(GatewayApiDO userDO);

    /** 删除 */
    @Delete("delete from gateway_api where id = #{id}")
    void delete(@Param("id") Long id);

}
