package com.wififriend.web.repository;

import com.wififriend.web.entity.BookmarkGroup;

public class BookmarkGroupRepository extends RepositoryImplBase<BookmarkGroup, String> {
    private static final BookmarkGroupRepository instance = new BookmarkGroupRepository();
    private BookmarkGroupRepository(){}
    public static BookmarkGroupRepository getInstance() {
        return instance;
    }
}
