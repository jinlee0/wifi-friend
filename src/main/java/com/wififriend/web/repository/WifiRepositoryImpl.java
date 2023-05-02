package com.wififriend.web.repository;

import com.wififriend.web.entity.Wifi;

import java.util.Optional;

public class WifiRepositoryImpl implements WifiRepository {
    @Override
    public Optional<Wifi> findById(String id) {
        return Optional.empty();
    }
}
