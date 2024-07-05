package com.stock.chitchat.stock;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StockService {
    private final StockRepository stockRepository;

    public Stock findStockById(Long stockId) {
        return stockRepository.findById(stockId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 주식입니다."));
    }
}
