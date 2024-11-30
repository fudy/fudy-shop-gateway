package com.fudy.shop.gateway.domain.model;

import lombok.Data;

@Data
public class Entity {
    private Long id;

    public boolean hasId() {
        return null != id;
    }
}
