package com.example.daily_aggregates;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Trade {
    private String timestamp;
    private String ticker;
    private double price;
    private int volume;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss");
    public Trade(String timestamp, String ticker, double price, int volume)
    {
        this.timestamp = timestamp;
        this.ticker = ticker;
        this.price = price;
        this.volume = volume;
    }

    public LocalDateTime getTimestamp() {
        return LocalDateTime.parse(timestamp, formatter);
    }

    public String getDate() {
        return getTimestamp().toLocalDate().toString();
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

    @Override
    public String toString() {
        return String.format("Trade[timestamp=%s, ticker=%s, price=%.2f, volume=%,d]",
                timestamp, ticker, price, (int) volume);

    }

}
