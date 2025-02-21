package com.example.daily_aggregates;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TradeServiceTest {

    private TradeService tradeService;

    @BeforeEach
    void setup() {
        TradeFileReader tradeFileReader = new TradeFileReader();
        tradeService = new TradeService(tradeFileReader);
    }

    @Test
    void shouldReturnAggregatedTradeSummaries() throws IOException {
        Map<String, Map<String, DailySummary>> summaries = tradeService.getDailySummaries("src/test/resources/sample_trades.txt");

        assertFalse(summaries.isEmpty());
        assertTrue(summaries.get("2025-01-20").containsKey("ABC"));

        DailySummary abcSummary = summaries.get("2025-01-20").get("ABC");
        assertEquals(100, abcSummary.openPrice());
        assertEquals(120, abcSummary.closePrice());
        assertEquals(120, abcSummary.highPrice());
        assertEquals(100, abcSummary.lowPrice());
        assertEquals((100 * 500) + (110 * 200) + (120 * 300), abcSummary.totalVolume());

    }
}
