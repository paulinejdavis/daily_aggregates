package com.example.daily_aggregates;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
 class TradeControllerTest {

    @LocalServerPort
    private int port;

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private TradeService tradeService;

    @Test
    void shouldReturnAggregatesAsJson() {
        String url = "http://localhost:" + port + "/trades/summary?filePath=src/test/resources/sample_trades.txt";
        Map<String, Map<String, DailySummary>> response = restTemplate.getForObject(url, Map.class);

        assertNotNull(response);
        assertFalse(response.isEmpty());
        assertTrue(response.containsKey("2025-01-20"));
        assertTrue(response.get("2025-01-20").containsKey("ABC"));

    }

    @Test
    void shouldReturnTradeSummary() {
        String filePath = "src/test/resources/sample_trades.txt";

        try {
            Map<String, Map<String, DailySummary>> summaries = tradeService.getDailySummaries(filePath);

            assertFalse(summaries.isEmpty());
            assertTrue(summaries.containsKey("2025-01-20"));
            assertTrue(summaries.get("2025-01-20").containsKey("ABC"));

            DailySummary abcSummary = summaries.get("2025-01-20").get("ABC");

            assertEquals(100, abcSummary.openPrice());
            assertEquals(105, abcSummary.closePrice());
            assertEquals(105, abcSummary.highPrice());
            assertEquals(100, abcSummary.lowPrice());
            assertEquals(50000, abcSummary.totalVolume());

        } catch (Exception e) {
            fail("Exception occurred while fetching trade summaries: " + e.getMessage());
        }
    }
}
