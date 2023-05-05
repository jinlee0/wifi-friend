package com.wififriend.web.repository;

import com.wififriend.web.entity.Wifi;

public class WifiRepository extends RepositoryImplBase<Wifi, String> {
    private static final WifiRepository instance = new WifiRepository();
    private WifiRepository(){}
    public static WifiRepository getInstance() {
        return instance;
    }
    public static void main(String[] args) {
        WifiRepository wifiRepository = new WifiRepository();
        System.out.println(wifiRepository.kClass());
        System.out.println(wifiRepository.tClass());
        System.out.println(wifiRepository.tableName());
        Wifi wifi = wifiRepository.findById("e9089b77-44b0-4b1b-9c27-75759aa3eb85").get();
        System.out.println(wifi);
        System.out.println(wifiRepository.findAll().size());
        System.out.println(wifiRepository.findAll().size());
    }
}
