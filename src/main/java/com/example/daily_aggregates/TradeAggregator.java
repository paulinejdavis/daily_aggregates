package com.example.daily_aggregates;

import java.util.*;
import java.util.stream.Collectors;
import java.util.Comparator;

public class TradeAggregator {

    public static Map<String, Map<String, DailySummary>> aggregate(List<Trade> trades){
        if (trades.isEmpty()) {
            return Collections.emptyMap();
        }

        return trades.stream()
                .collect(Collectors.groupingBy(
                        Trade::getDate,  // Group by date first
                        Collectors.groupingBy(
                                Trade::getTicker,  // Then group by ticker
                                Collectors.collectingAndThen(Collectors.toList(),
                                        TradeAggregator::summariseTrades) // Summarize
                        )
                ));
    }

    private static DailySummary summariseTrades(List<Trade> trades) {
        if (trades.isEmpty()) {
            return new DailySummary(0, 0, 0, 0, 0);
        }

        // Sort trades to ensure correct open/close price selection
        List<Trade> sortedTrades = trades.stream()
                .sorted(Comparator.comparing(Trade::getTimestamp))
                .toList();

        double openPrice = sortedTrades.get(0).getPrice();
        double closePrice = sortedTrades.get(sortedTrades.size() - 1).getPrice();
        OptionalDouble highPrice = sortedTrades.stream().mapToDouble(Trade::getPrice).max();
        OptionalDouble lowPrice = sortedTrades.stream().mapToDouble(Trade::getPrice).min();

        // totalVolume sums **price * volume*(trade -> trade.getPrice() * trade.getVolume())* (not just volume)
        double totalVolume = sortedTrades.stream()
                .mapToDouble(trade -> trade.getPrice() * trade.getVolume())
                .sum();

        sortedTrades.forEach(trade ->
                System.out.println("Trade: " + trade.getTimestamp() +
                        " | Price: " + trade.getPrice() +
                        " | Volume: " + trade.getVolume())
        );

        return new DailySummary(
                openPrice,
                closePrice,
                highPrice.orElse(0),
                lowPrice.orElse(0),
                totalVolume

        );
    }


}
