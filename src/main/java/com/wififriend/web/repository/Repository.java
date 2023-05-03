package com.wififriend.web.repository;

import com.wififriend.web.entity.BaseEntity;

import java.util.List;
import java.util.Optional;

public interface Repository<T extends BaseEntity, K> {
    Optional<T> findById(K id);
    List<T> findAll();
}
