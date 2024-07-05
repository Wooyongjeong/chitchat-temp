package com.stock.chitchat.stock;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Sector {
    TECHNOLOGY("XLK"),
    ;

    private final String ticker;
}
