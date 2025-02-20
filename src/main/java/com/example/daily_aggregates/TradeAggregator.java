package com.example.daily_aggregates;

import java.util.List;
import java.util.OptionalDouble;

public class TradeAggregator {

    public static DailySummary aggregate(List<Trade> trades) {
        if (trades.isEmpty()) {
            return new DailySummary(0,0,0,0,0 );
        }

        double openPrice = trades.get(0).getPrice();
        double closePrice = trades.get(trades.size() - 1).getPrice();
        OptionalDouble highPrice = trades.stream().mapToDouble(Trade ::getPrice).max();
        OptionalDouble lowPrice = trades.stream().mapToDouble(Trade ::getPrice).min();
        double totalVolume = trades.stream().mapToDouble(trade -> trade.getPrice() * trade.getVolume()).sum();

        return new DailySummary(
                openPrice,
                closePrice,
                highPrice.orElse(0),
                lowPrice.orElse(0),
                totalVolume
        );

    }
}
