package com.example.daily_aggregates;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TradeAggregatorTest {

    @Test
    void shouldCalculateDailyOpenClosePrices() {
        List<Trade> trades = List.of(
                new Trade("2025-01-20 09:00:01", "ABC", 100, 500),
                new Trade("2025-01-20 09:10:00", "ABC", 110, 200),
                new Trade("2025-01-20 16:00:00", "ABC", 120, 300)
        );

        Map<String, Map<String, DailySummary>> summaries  = TradeAggregator.aggregate(trades);
        DailySummary summary = summaries.get("2025-01-20").get("ABC");

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
