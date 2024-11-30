package com.fudy.shop.gateway.interfaces;

import com.fudy.shop.gateway.application.GatewayApiManager;
import com.fudy.shop.gateway.application.dto.CreateApiCommand;
import com.fudy.shop.gateway.application.dto.GatewayApiDTO;
import com.fudy.shop.gateway.application.dto.Result;
import com.fudy.shop.gateway.application.dto.UpdateApiCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class GatewayApiController {
    @Autowired
    private GatewayApiManager gatewayApiManager;

    /** 查询所有api */
    @GetMapping("/apis")
    public Result<List<GatewayApiDTO>> findAllApiList() {
        try {
            List<GatewayApiDTO> data = gatewayApiManager.findAllApiList();
            return Result.success(data);
        } catch (Exception e) {
            log.error("failed to find all api list", e);
            return Result.fail(e.getMessage());
        }

    }

    /** 根据id查询指定api */
    @GetMapping("/apis/{id}")
    public Result<GatewayApiDTO> findApi(@PathVariable("id") Long id) {
        try {
            GatewayApiDTO data = gatewayApiManager.findApi(id);
            return Result.success(data);
        } catch (Exception e) {
            log.error("failed to find api", e);
            return Result.fail(e.getMessage());
        }
    }

    /** 创建一个api */
    @PostMapping("/apis")
    public Result<GatewayApiDTO> createApi(@RequestBody CreateApiCommand createApiCommand) {
        try {
            GatewayApiDTO data = gatewayApiManager.createApi(createApiCommand);
            return Result.success(data);
        } catch (Exception e) {
            log.error("failed to create api", e);
            return Result.fail(e.getMessage());
        }
    }

    /** 根据id删除指定api */
    @DeleteMapping("/apis/{id}")
    public Result<Void> removeApi(@PathVariable("id") Long id) {
        try {
            gatewayApiManager.removeApi(id);
            return Result.success(null);
        } catch (Exception e) {
            log.error("failed to remove api", e);
            return Result.fail(e.getMessage());
        }
    }

    /** 修改指定api */
    @PutMapping("/apis/{id}")
    public Result<Void> updateApi(@PathVariable("id") Long id, @RequestBody UpdateApiCommand updateApiCommand) {
        try {
            gatewayApiManager.updateApi(id, updateApiCommand);
            return Result.success(null);
        } catch (Exception e) {
            log.error("failed to update api", e);
            return Result.fail(e.getMessage());
        }
    }




}
