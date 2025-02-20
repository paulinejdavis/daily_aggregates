package com.example.daily_aggregates;

import javax.management.monitor.StringMonitor;

public class Trade {
    private String timestamp;
    private String ticker;
    private double price;
    private int volume;

    public Trade(String timestamp, String ticker, double price, int volume)
    {
        this.timestamp = timestamp;
        this.ticker = ticker;
        this.price = price;
        this.volume = volume;
    }

    public String getTicker() {
        return ticker;
    }

    public double getPrice() {
        return price;
    }
    public int getVolume() {
        return volume;
    }

}
