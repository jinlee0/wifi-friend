package com.wififriend.web.repository;

import com.wififriend.web.entity.BaseEntity;
import com.wififriend.web.entity.Wifi;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

public abstract class RepositoryImplBase<T extends BaseEntity, K> implements Repository<T, K> {
    protected final TransactionExecutor tx = TransactionExecutor.getInstance();

    protected final Class<T> tClass() {
        return (Class<T>) (((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    protected final Class<K> kClass() {
        return (Class<K>) (((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1]);
    }

    protected final String tableName() {
        return tClass().getSimpleName();
    }

    @Override
    public Optional<T> findById(K id) {
        List<T> exec = tx.execQuery(
                String.format("select * from %s where id='%s'", tableName(), id),
                tClass());
        if(exec.isEmpty()) return Optional.empty();
        else return Optional.of(exec.get(0));
    }

    @Override
    public List<T> findAll() {
        return tx.execQuery(String.format("select * from %s", tableName()), tClass());
    }
}
