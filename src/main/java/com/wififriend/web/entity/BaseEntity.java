package com.wififriend.web.entity;

import lombok.Getter;
import lombok.ToString;

import java.util.UUID;
@Getter
@ToString
public abstract class BaseEntity {
    protected final String id = UUID.randomUUID().toString();

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof  BaseEntity)) return false;
        return id.equals(((BaseEntity) obj).getId());
    }
}
