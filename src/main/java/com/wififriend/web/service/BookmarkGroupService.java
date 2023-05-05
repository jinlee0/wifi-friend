package com.wififriend.web.service;

import com.wififriend.web.entity.BookmarkGroup;
import com.wififriend.web.repository.BookmarkGroupRepository;
import com.wififriend.web.repository.BookmarkRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BookmarkGroupService {
    private final BookmarkGroupRepository bookmarkGroupRepository = BookmarkGroupRepository.getInstance();
    private final BookmarkRepository bookmarkRepository = BookmarkRepository.getInstance();

    public List<BookmarkGroup> getAll() {
        return bookmarkGroupRepository.findAll()
                .stream()
                .sorted(Comparator.comparingInt(bg -> Integer.parseInt(bg.getOrdinal())))
                .collect(Collectors.toList());
    }

    public void add(String name, String ordinal) {
        bookmarkGroupRepository.save(new BookmarkGroup(name, ordinal));
    }

    public BookmarkGroup getById(String id) {
        return bookmarkGroupRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public void modify(String id, String newName, String newOrdinal) {
        BookmarkGroup bookmarkGroup = bookmarkGroupRepository.findById(id).orElseThrow(RuntimeException::new);
        bookmarkGroup.modify(newName, newOrdinal);
        bookmarkGroupRepository.save(bookmarkGroup);
    }

    public void remove(String id) {
        bookmarkRepository.deleteByBookmarkGroupId(id);
        bookmarkGroupRepository.deleteById(id);
    }
}
