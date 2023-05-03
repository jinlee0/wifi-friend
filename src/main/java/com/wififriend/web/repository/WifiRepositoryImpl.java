package com.wififriend.web.repository;

import com.wififriend.web.entity.Wifi;

import java.util.List;
import java.util.Optional;

public class WifiRepositoryImpl extends RepositoryImplBase<Wifi, String> {
    private final TransactionExecutor tx = TransactionExecutor.getInstance();

    @Override
    public Optional<Wifi> findById(String id) {
        List<Wifi> exec = tx.execQuery("select * from " + tableName() + " where id=" + id, tClass());
        if(exec.isEmpty()) return Optional.empty();
        else return Optional.of(exec.get(0));
    }

    public static void main(String[] args) {
        WifiRepositoryImpl wifiRepository = new WifiRepositoryImpl();
        System.out.println(wifiRepository.kClass());
        System.out.println(wifiRepository.tClass());
        System.out.println(wifiRepository.tableName());
    }
}
