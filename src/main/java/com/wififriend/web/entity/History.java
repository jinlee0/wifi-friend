package com.wififriend.web.entity;

import lombok.*;

@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class History extends BaseEntity {
    private String latitude;
    private String longitude;
    private String createdAt;
}
