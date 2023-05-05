package com.wififriend.web.repository;

import com.wififriend.web.entity.BaseEntity;
import com.wififriend.web.entity.History;
import com.wififriend.web.entity.Wifi;
import com.wififriend.web.utils.Reflect;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.time.LocalDateTime;
import java.util.Deque;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

public abstract class RepositoryImplBase<T extends BaseEntity, K> implements Repository<T, K> {
    protected final TransactionExecutor tx = TransactionExecutor.getInstance();
    protected List<T> cache; // 전체 데이터를 캐싱해둔다. 업데이트 시 캐시에도 반영해야 한다.

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
        checkCache();
        return cache.stream().filter(t -> t.getId().equals(id)).findFirst();
    }

    @Override
    public List<T> findAll() {
        checkCache();
        return cache;
    }

    @Override
    public T save(T entity) {
        checkCache();
        if (cache.contains(entity)) {
            try {
                Deque<Field> fields = Reflect.getAllFields(entity.getClass());
                StringJoiner sj = new StringJoiner(", ");
                for (Field field : fields) {
                    field.setAccessible(true);
                    Object o = field.get(entity);
                    if (o==null) {
                        sj.add(field.getName() + "=null");
                    } else {
                        sj.add(field.getName() + "='" + o + "'");
                    }
                }
                tx.execUpdate(String.format("update %s set %s where id=%s", tableName(), sj, entity.getId()));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }else {
            try {
                Deque<Field> fields = Reflect.getAllFields(entity.getClass());
                StringJoiner sj = new StringJoiner(", ");
                for (Field field : fields) {
                    field.setAccessible(true);
                    Object o = field.get(entity);
                    if (o==null) {
                        sj.add("null");
                    } else {
                        sj.add("'" + o + "'");
                    }
                }
                String sql = String.format("insert into %s values(%s)", tableName(), sj);
                tx.execUpdate(sql);
                cache.add(entity);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return entity;
    }

    public void deleteById(K id) {
        checkCache();
        tx.execUpdate(String.format("delete from %s where id='%s'", tableName(), id));
        cache.removeIf(t -> t.getId().equals(id));
    }

    protected void checkCache() {
        if(cache == null) {
            cache = tx.execQuery(String.format("select * from %s", tableName()), tClass());
        }
    }

    public static void main(String[] args) {
        HistoryRepository hr = HistoryRepository.getInstance();
        hr.save(new History("37", "127", LocalDateTime.now().toString()));
    }
}
