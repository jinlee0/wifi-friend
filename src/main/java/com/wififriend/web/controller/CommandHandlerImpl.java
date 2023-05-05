package com.wififriend.web.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public abstract class CommandHandlerImpl implements CommandHandler {
    protected String name;

    protected CommandHandlerImpl(String name) {
        this.name = name;
    }
}
