package com.wififriend.web.entity;

import lombok.Getter;
import lombok.ToString;

import java.util.UUID;
@Getter
@ToString
public abstract class BaseEntity {
    private final String id = UUID.randomUUID().toString();
}
