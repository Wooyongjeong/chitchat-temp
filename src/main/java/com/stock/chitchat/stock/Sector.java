package com.stock.chitchat.stock;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum Sector {
    ENERGY("XLE"),
    MATERIALS("XLB"),
    INDUSTRIALS("XLI"),
    CONSUMER_DISCRETIONARY("XLY"),
    CONSUMER_STAPLES("XLP"),
    HEALTH_CARE("XLV"),
    FINANCIALS("XLF"),
    INFORMATION_TECHNOLOGY("XLK"),
    COMMUNICATION_SERVICES("XLC"),
    UTILITIES("XLU"),
    REAL_ESTATE("XLRE"),
    ;

    private final String ticker;

    public static Sector from(String ticker) {
        return Arrays.stream(values())
                .filter(sector -> ticker.equals(sector.ticker))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 Sector입니다."));
    }
}
