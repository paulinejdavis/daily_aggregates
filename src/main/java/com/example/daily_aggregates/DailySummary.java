package com.example.daily_aggregates;

public record DailySummary (
        double openPrice,
        double closePrice,
        double highPrice,
        double lowPrice,
        double totalVolume

) {


    public DailySummary(double openPrice, double closePrice, double highPrice, double lowPrice, double totalVolume) {
        this.openPrice = openPrice;
        this.closePrice = closePrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.totalVolume = totalVolume;
    }
    public double openPrice() {
        return openPrice;
    }
    public double closePrice() {
        return closePrice;
    }
    public double highPrice() {
        return highPrice;
    }
    public double lowPrice() {
        return lowPrice;
    }
    public double totalVolume() {
        return totalVolume;
    }

}

