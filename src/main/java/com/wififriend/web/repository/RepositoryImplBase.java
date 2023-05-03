package com.wififriend.web.repository;

import com.wififriend.web.entity.BaseEntity;

import java.lang.reflect.ParameterizedType;

public abstract class RepositoryImplBase<T extends BaseEntity, K> implements Repository<T, K> {

    protected final Class<T> tClass() {
        return (Class<T>) (((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    protected final Class<K> kClass() {
        return (Class<K>) (((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1]);
    }

    protected final String tableName() {
        return tClass().getSimpleName();
    }
}
