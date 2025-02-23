package com.example.daily_aggregates;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = DailyAggregatesApplication.class)
public class TradeServiceTest {

    @Autowired
    private TradeService tradeService;

    @TestConfiguration
    static class TestConfig {
        @Bean
        public TradeFileReader tradeFileReader() {
            return new TradeFileReader();
        }

        @Bean
        public TradeService tradeService(TradeFileReader tradeFileReader) {
            return new TradeService(tradeFileReader);
        }
    }

    @Test
    void contextLoads() {
        assertNotNull(tradeService, "TradeService should not be null");
    }

    @Test
    void shouldReturnAggregatedTradeSummaries() throws IOException {
        Map<String, Map<String, DailySummary>> summaries = tradeService.getDailySummaries("src/test/resources/sample_trades.txt");

        assertFalse(summaries.isEmpty());
        assertTrue(summaries.get("2025-01-20").containsKey("ABC"));

        DailySummary abcSummary = summaries.get("2025-01-20").get("ABC");

        double expectedClosePrice = 105.0;
        double actualClosePrice = abcSummary.closePrice();

        assertEquals(100, abcSummary.openPrice());
        //assertEquals(expectedClosePrice, actualClosePrice);
        assertEquals(105, abcSummary.closePrice());
        assertEquals(105, abcSummary.highPrice());
        assertEquals(100, abcSummary.lowPrice());
    }

    @Test
    void shouldCalculateMarketIndex() throws IOException {
        Map<String, Map<String, DailySummary>> summaries = tradeService.getDailySummaries("src/test/resources/sample_trades.txt");

        double expectedIndex = 190.0;
        double marketIndex = tradeService.calculateMarketIndex(summaries);
        assertEquals(expectedIndex, marketIndex);
    }



}
