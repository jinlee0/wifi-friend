package com.wififriend.web.repository;

import com.wififriend.web.config.DbConfig;
import com.wififriend.web.entity.Wifi;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class TransactionExecutor {
    private static final TransactionExecutor instance = new TransactionExecutor();
    private final Connection conn;
    private final Semaphore semaphore = new Semaphore(1, true);

    private TransactionExecutor() {
        try {
            Class.forName(DbConfig.JDBC_DRIVER);
            this.conn = DriverManager.getConnection(DbConfig.DB_URL);;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> List<T> exec(String sql, Class<T> tClass) {
        try {
            semaphore.acquire();
            try (PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                return rsAsEntityList(rs, tClass);
            }
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
        }
    }

    private <T> List<T> rsAsEntityList(ResultSet rs, Class<T> tClass) {
        try {
            List<T> list = new ArrayList<>();
            while (rs.next()) {
                list.add(nextResultAsEntity(rs, tClass));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private  <T> T nextResultAsEntity(ResultSet rs, Class<T> tClass) {
        try {
            Constructor<T> declaredConstructor = tClass.getDeclaredConstructor();
            declaredConstructor.setAccessible(true);
            T t = declaredConstructor.newInstance();
            Field[] fields = tClass.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                field.set(t, getValue(rs, field));
            }
            return t;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private Object getValue(ResultSet rs, Field field) {
        try {
            return rs.getString(field.getName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static TransactionExecutor getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        Class<Wifi> wifiClass = Wifi.class;
        Field[] fields = wifiClass.getDeclaredFields();
        System.out.println(fields[0].getType().getSimpleName());
        System.out.println(fields[0].getType().getTypeName());
        System.out.println(fields[0].getType().getCanonicalName());
        System.out.println(fields[0].getClass());
        System.out.println(fields[0].getName());
    }
}