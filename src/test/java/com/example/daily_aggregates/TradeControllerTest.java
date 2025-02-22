package com.example.daily_aggregates;

import org.junit.jupiter.api.Test;
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

    @Test
    void shouldReturnAggregatesAsJson() {
        String url = "http://localhost:" + port + "/api/trades/aggregates";
        Map<String, Map<String, DailySummary>> response = restTemplate.getForObject(url, Map.class);

        assertNotNull(response);
        assertFalse(response.isEmpty());
        assertTrue(response.containsKey("2025-01-20"));
        assertTrue(response.get("2025-01-20").containsKey("ABC"));

    }
}
