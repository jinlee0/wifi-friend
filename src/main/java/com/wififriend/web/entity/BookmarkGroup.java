package com.wififriend.web.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BookmarkGroup extends BaseEntity {
    private String name;
    private String createdAt = LocalDateTime.now().toString();
    private String updatedAt;
    private String ordinal;

    public BookmarkGroup(String name, String ordinal) {
        this.name = name;
        this.ordinal = ordinal;
    }

    public void modify(String newName, String newOrdinal) {
        this.name = newName;
        this.ordinal = newOrdinal;
    }
}
