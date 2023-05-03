package com.wififriend.web.repository;

import com.wififriend.web.entity.Wifi;

import java.util.List;
import java.util.Optional;

public class WifiRepositoryImpl extends RepositoryImplBase<Wifi, String> {



    public static void main(String[] args) {
        WifiRepositoryImpl wifiRepository = new WifiRepositoryImpl();
        System.out.println(wifiRepository.kClass());
        System.out.println(wifiRepository.tClass());
        System.out.println(wifiRepository.tableName());
        Wifi wifi = wifiRepository.findById("e9089b77-44b0-4b1b-9c27-75759aa3eb85").get();
        System.out.println(wifi);
        System.out.println(wifiRepository.findAll().size());
    }
}
