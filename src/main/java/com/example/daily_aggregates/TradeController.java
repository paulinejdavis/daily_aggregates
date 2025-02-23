package com.example.daily_aggregates;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/trades")
public class TradeController {

    private final TradeService tradeService;

    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }
    @GetMapping("/summary")
    public ResponseEntity<?> getTradeSummaries(@RequestParam String filePath) {
        try {
            Map<String, Map<String, DailySummary>> summaries = tradeService.getDailySummaries(filePath);
            return ResponseEntity.ok(summaries);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error reading file: " + e.getMessage());
        }
    }


    @GetMapping("/market-index")
    public double getMarketIndex(@RequestParam String filePath) throws IOException {
        Map<String, Map<String, DailySummary>> summaries = tradeService.getDailySummaries(filePath);
        return tradeService.calculateMarketIndex(summaries);
    }

}
