package com.example.daily_aggregates;

import org.junit.jupiter.api.Test;

import java.util.List;

public class TradeAggregatorTest {

    @Test
    void shouldCalculateDailyOpenClosePrices() {
        List<Trade> trades = List.of(
                new Trade("2025-01-20 09:00:01", "ABC", 100, 500),
                new Trade("2025-01-20 09:10:00", "ABC", 100, 500),
                new Trade("2025-01-20 16:00:00", "ABC", 100, 500)
        );

        DailySummary summary = TradeAggregator.aggregate(trades); //not created as yet

        assertEquals(100, summary.getOpenPrice());
        assertEquals(120, summary.getClosePrice());
        assertEquals(110, summary.getHighPrice());
        assertEquals(100, summary.getLowPrice());
        assertEquals(100*500 + 120*300, summary.getTotalVolume());

    }
}
