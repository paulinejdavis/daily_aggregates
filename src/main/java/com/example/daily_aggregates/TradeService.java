package com.example.daily_aggregates;

import com.example.daily_aggregates.TradeAggregator;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle.summary;

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
        System.out.println("DEBUG: Running Market Index Calculation...");

        double marketIndex = 0.0;

        Map<String, Double> weights = Map.of(
                "ABC", 0.1,
                "MEGA", 0.3,
                "NGL", 0.4,
                "TRX", 0.2
        );

        // Find the most recent date that has at least one weighted stock
        String latestValidDate = summaries.keySet().stream()
                .sorted(Comparator.reverseOrder()) // Sort in descending order
                .filter(date -> summaries.get(date).keySet().stream().anyMatch(weights::containsKey))
                .findFirst()
                .orElse(null);

        if (latestValidDate == null) {
            System.out.println("⚠️ ERROR: No valid trades found for calculation.");
            return 0.0;
        }

        System.out.println("DEBUG: Latest Valid Trading Date: " + latestValidDate);
        System.out.println("DEBUG: Available Tickers on " + latestValidDate + " -> " + summaries.get(latestValidDate).keySet());

        for (Map.Entry<String, DailySummary> entry : summaries.get(latestValidDate).entrySet()) {
            String ticker = entry.getKey();
            double closePrice = entry.getValue().closePrice();
            double weight = weights.getOrDefault(ticker, 0.0);

            if (weight > 0) {
                double contribution = weight * closePrice;
                System.out.println("DEBUG: Ticker: " + ticker + " | Weight: " + weight + " | Close Price: " + closePrice + " | Contribution: " + contribution);
                marketIndex += contribution;
            } else {
                System.out.println("⚠️ WARNING: No weight assigned for " + ticker + ". Skipping.");
            }
        }

        System.out.println("DEBUG: Expected Sum Before Ceiling: " + marketIndex);
        double finalMarketIndex = Math.ceil(marketIndex);
        System.out.println("DEBUG: Final Market Index After Ceiling: " + finalMarketIndex);

        return finalMarketIndex;
    }

}
