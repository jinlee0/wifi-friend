package com.wififriend.web.repository;

import com.wififriend.web.entity.Bookmark;

import java.util.List;
import java.util.stream.Collectors;

public class BookmarkRepository extends RepositoryImplBase<Bookmark, String> {
    private static final BookmarkRepository instance = new BookmarkRepository();
    private BookmarkRepository(){}
    public static BookmarkRepository getInstance() {
        return instance;
    }

    public void deleteByBookmarkGroupId(String bookmarkGroupId) {
        checkCache();
        tx.execUpdate(String.format("delete from %s where bookmarkGroupId='%s'", tableName(), bookmarkGroupId));
        cache.removeIf(b -> b.getBookmarkGroupId().equals(bookmarkGroupId));
    }
}
