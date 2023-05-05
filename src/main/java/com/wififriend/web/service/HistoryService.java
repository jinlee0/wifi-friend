package com.wififriend.web.service;

import com.wififriend.web.entity.History;
import com.wififriend.web.repository.HistoryRepository;

import java.time.LocalDateTime;
import java.util.List;

public class HistoryService {
    private final HistoryRepository historyRepository = HistoryRepository.getInstance();

    public void add(String lat, String lnt) {
        historyRepository.save(new History(lat, lnt, LocalDateTime.now().toString()));
    }
    public List<History> getAll() {
        return historyRepository.findAll();
    }

    public void remove(String historyId) {
        historyRepository.deleteById(historyId);
    }
}
