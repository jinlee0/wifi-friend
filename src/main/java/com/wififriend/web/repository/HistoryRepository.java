package com.wififriend.web.repository;

import com.wififriend.web.entity.History;

public class HistoryRepository extends RepositoryImplBase<History, String> {
    private static final HistoryRepository instance = new HistoryRepository();

    private HistoryRepository(){}

    public static HistoryRepository getInstance() {
        return instance;
    }

}
