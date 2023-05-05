package com.wififriend.web.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Bookmark extends BaseEntity {
    private String bookmarkGroupId;
    private String wifiId;
    private String createdAt = LocalDateTime.now().toString();

    public Bookmark(String wifiId, String bookmarkGroupId) {
        this.wifiId = wifiId;
        this.bookmarkGroupId = bookmarkGroupId;
    }
}
