package com.example.daily_aggregates;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TradeParserTest {
    @Test
    void shouldParseTradeEntry(){
        String logEntry = "2025-01-20 09:00:01;ABC;100;500";
        Trade trade = TradeParser.parse(logEntry);

        assertNotNull(trade);
        assertEquals("ABC", trade.getTicker());
        assertEquals(100, trade.getPrice());
        assertEquals(500, trade.getVolume());
    }
}
