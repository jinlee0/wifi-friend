package com.wififriend.web.service;

import com.wififriend.web.entity.Wifi;
import com.wififriend.web.repository.WifiRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class WifiRetrieveService {
    private final WifiRepository wifiRepository = WifiRepository.getInstance();


    public List<Wifi> retrieve(String lat, String lnt) {
        double dLat = Double.parseDouble(lat);
        double dLnt = Double.parseDouble(lnt);
        List<Wifi> wifi = wifiRepository.findAll()
                .stream()
                .sorted((w1, w2) -> (int) (w1.distanceFrom(dLat, dLnt) - w2.distanceFrom(dLat, dLnt)))
                .limit(20)
                .collect(Collectors.toList());
        return wifi;
    }

    public Wifi getById(String id) {
        return wifiRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}
