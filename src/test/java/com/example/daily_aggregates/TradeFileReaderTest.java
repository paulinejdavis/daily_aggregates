package com.example.daily_aggregates;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TradeFileReaderTest {

    @TestConfiguration
    static class TestConfig {
        @Bean
        public TradeFileReader tradeFileReader() {
            return new TradeFileReader();
        }
    }

    @Test
    void shouldReadTradesFromFile() throws IOException {
        String filePath = "src/test/resources/sample_trades.txt";

        TradeFileReader tradeFileReader = new TradeFileReader();

        List<Trade> trades = tradeFileReader.readTradesFromFile(filePath);

        assertFalse(trades.isEmpty());
        assertEquals("ABC", trades.get(0).getTicker());
        assertEquals(100, trades.get(0).getPrice());
    }
}
