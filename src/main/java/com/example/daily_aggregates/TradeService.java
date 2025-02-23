package com.example.daily_aggregates;

import com.example.daily_aggregates.TradeAggregator;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;


@Service
public class TradeService {

    private final TradeFileReader tradeFileReader;

    public TradeService(TradeFileReader tradeFileReader) {
        this.tradeFileReader = tradeFileReader;
    }

    public Map<String, Map<String, DailySummary>> getDailySummaries(String filePath) throws IOException {
//        List<Trade> trades = tradeFileReader.readTradesFromFile(filePath);
//        return TradeAggregator.aggregate(trades);
        return TradeAggregator.aggregate(tradeFileReader.readTradesFromFile(filePath));
    }

    public double calculateMarketIndex(Map<String, Map<String, DailySummary>> summaries) {
        double marketIndex = 0.0;

        Map<String, Double> weights = Map.of(
                "ABC", 0.1,
                "MEGA", 0.3,
                "NGL", 0.4,
                "TRX", 0.2
        );

        String latestValidDate = summaries.keySet().stream()
                .sorted(Comparator.reverseOrder()) // Sort in descending order
                .filter(date -> summaries.get(date).keySet().stream().anyMatch(weights::containsKey))
                .findFirst()
                .orElse(null);

        if (latestValidDate == null) {
            System.out.println("⚠️ ERROR: No valid trades found for calculation.");
            return 0.0;
        }

        for (Map.Entry<String, DailySummary> entry : summaries.get(latestValidDate).entrySet()) {
            String ticker = entry.getKey();
            double closePrice = entry.getValue().closePrice();
            double weight = weights.getOrDefault(ticker, 0.0);

            if (weight > 0) {
                double contribution = weight * closePrice;
                marketIndex += contribution;
            } else {
                System.out.println("⚠️ WARNING: No weight assigned for " + ticker + ". Skipping.");
            }
        }

        double finalMarketIndex = Math.ceil(marketIndex);

        return finalMarketIndex;
    }

}
