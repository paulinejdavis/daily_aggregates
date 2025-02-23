package com.example.daily_aggregates;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class TradeFileReader {
    public List<Trade> readTradesFromFile(String filePath) throws IOException {
        List<Trade> trades = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                line = line.trim();

                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                if (line.isEmpty()) {
                    continue;
                }

                try {
                    Trade trade = parseTrade(line);

                    if (!trade.getDate().startsWith("2025")) {
                        System.out.println("WARNING: Skipping trade with incorrect date -> " + line);
                        continue;
                    }

                    trades.add(trade);
                } catch (IllegalArgumentException e) {
                    System.out.println("WARNING: Skipping invalid trade entry: " + line);
                }
            }
        }

        return trades;
    }

    private Trade parseTrade(String line) {
        String[] parts = line.split(";");
        if (parts.length != 4) {
            throw new IllegalArgumentException("Invalid log entry format: " + line);
        }

        return new Trade(
                parts[0],
                parts[1],
                Double.parseDouble(parts[2]),
                Integer.parseInt(parts[3])
        );
    }
}
