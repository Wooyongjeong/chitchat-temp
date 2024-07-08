package com.stock.chitchat.stock;

import com.stock.chitchat.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Stock extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 5)
    private String ticker;

    @Enumerated(EnumType.STRING)
    private Sector sector;

    @Enumerated(value = EnumType.STRING)
    private Exchange exchange;

    @Builder
    private Stock(String name, String ticker, Sector sector, Exchange exchange) {
        this.name = name;
        this.ticker = ticker;
        this.sector = sector;
        this.exchange = exchange;
    }
}
