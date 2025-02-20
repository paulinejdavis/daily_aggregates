package com.example.daily_aggregates;


public class TradeParser {

    public static Trade parse(String logEntry) {
        String[] parts = logEntry.split(";");
        if (parts.length != 4) {
            throw new IllegalArgumentException("Invalid log entry format");
        }

        String timestamp = parts[0];
        String ticker = parts[1];
        double price = Double.parseDouble(parts[2]);
        int volume = Integer.parseInt(parts[3]);

        return new Trade(timestamp, ticker, price, volume);
    }
}
