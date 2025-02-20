package com.example.daily_aggregates;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TradeAggregatorTest {

    @Test
    void shouldCalculateDailyOpenClosePrices() {
        List<Trade> trades = List.of(
                new Trade("2025-01-20 09:00:01", "ABC", 100, 500),
                new Trade("2025-01-20 09:10:00", "ABC", 110, 200),
                new Trade("2025-01-20 16:00:00", "ABC", 120, 300)
        );

        DailySummary summary = TradeAggregator.aggregate(trades); //not created as yet

        double expectedTotalVolume = trades.stream()
                        .mapToDouble(trade -> trade.getPrice() * trade.getVolume())
                                .sum();
        assertEquals(100, summary.openPrice());
        assertEquals(120, summary.closePrice());
        assertEquals(120, summary.highPrice());
        assertEquals(100, summary.lowPrice());
        assertEquals(expectedTotalVolume, summary.totalVolume());

    }
}
