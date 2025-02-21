package com.example.daily_aggregates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TradeFileReader {
    public static List<Trade> readTradesFromFile(String filePath) throws IOException {
        List<Trade> trades = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(filePath));

        for (String line : lines) {
            Trade trade = TradeParser.parse(line);
            if (trade != null) {
                trades.add(trade);
            }
        }
        return trades;
    }
}
