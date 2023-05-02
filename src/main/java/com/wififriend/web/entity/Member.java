package com.wififriend.web.entity;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class Member {
    private String id;
    private String name;
    private String email;

    private Member() {

    }

}
