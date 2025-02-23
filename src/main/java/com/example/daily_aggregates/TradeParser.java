package com.example.daily_aggregates;

public class TradeParser {

    public static Trade parse(String line) {
        if (line == null || line.trim().isEmpty()) {
            System.out.println("Skipping empty line.");
            return null;
        }
        String[] parts = line.split(";");
        if (parts.length != 4) {
            System.out.println("Skipping invalid line." + line);
            return null;
        }

        try {

            String timestamp = parts[0].trim();
            String ticker = parts[1].trim();
            double price = Double.parseDouble(parts[2].trim());
            int volume = Integer.parseInt(parts[3].trim());

            return new Trade(timestamp, ticker, price, volume);
        } catch (NumberFormatException e) {
            System.out.println("Skipping line due to invalid number: " + line);
            return null;
        }

    }
}
