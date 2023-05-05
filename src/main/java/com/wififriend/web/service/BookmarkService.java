package com.wififriend.web.service;

import com.wififriend.web.entity.Bookmark;
import com.wififriend.web.entity.BookmarkGroup;
import com.wififriend.web.entity.Wifi;
import com.wififriend.web.repository.BookmarkGroupRepository;
import com.wififriend.web.repository.BookmarkRepository;
import com.wififriend.web.repository.WifiRepository;
import lombok.Builder;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

public class BookmarkService {
    private final BookmarkRepository bookmarkRepository = BookmarkRepository.getInstance();
    private final BookmarkGroupRepository bookmarkGroupRepository = BookmarkGroupRepository.getInstance();
    private final WifiRepository wifiRepository = WifiRepository.getInstance();

    public void add(String wifiId, String bookmarkGroupId) {
        bookmarkRepository.save(new Bookmark(wifiId, bookmarkGroupId));
    }

    public List<BookmarkListDto> getAll() {
        List<Bookmark> bookmarks = bookmarkRepository.findAll();
        List<BookmarkListDto> dtos = new LinkedList<>();
        for (Bookmark bookmark : bookmarks) {
            BookmarkGroup bookmarkGroup = bookmarkGroupRepository.findById(bookmark.getBookmarkGroupId()).orElseThrow(RuntimeException::new);
            Wifi wifi = wifiRepository.findById(bookmark.getWifiId()).orElseThrow(RuntimeException::new);
            BookmarkListDto dto = BookmarkListDto.builder()
                    .bookmarkId(bookmark.getId())
                    .bookmarkGroupId(bookmarkGroup.getId())
                    .bookmarkGroupName(bookmarkGroup.getName())
                    .wifiName(wifi.getName())
                    .createdAt(bookmark.getCreatedAt())
                    .build();
            dtos.add(dto);
        }
        return dtos;
    }

    public void remove(String id) {
        bookmarkRepository.deleteById(id);
    }

    @Getter
    @Builder
    public static class BookmarkListDto {
        private String bookmarkId;
        private String bookmarkGroupId;
        private String bookmarkGroupName;
        private String wifiName;
        private String createdAt;
    }
}
