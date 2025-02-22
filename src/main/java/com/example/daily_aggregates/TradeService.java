package com.example.daily_aggregates;

import com.example.daily_aggregates.TradeAggregator;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class TradeService {

    private final TradeFileReader tradeFileReader;

    public TradeService(TradeFileReader tradeFileReader) {

        this.tradeFileReader = tradeFileReader;
    }

    public Map<String, Map<String, DailySummary>> getDailySummaries(String filePath) throws IOException {
        List<Trade> trades = tradeFileReader.readTradesFromFile(filePath);
        return TradeAggregator.aggregate(trades);
    }
}
