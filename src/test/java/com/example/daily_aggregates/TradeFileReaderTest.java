package com.example.daily_aggregates;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TradeFileReaderTest {

    @Test
    void shouldReadTradesFromFile() throws IOException {
        List<Trade> trades =
            TradeFileReader.readTradesFromFile("src/test/resources/sample_trades.txt");

            assertFalse(trades.isEmpty());
            assertEquals("ABC", trades.get(0).getTicker());
            assertEquals(100, trades.get(0).getPrice());
        }
}
