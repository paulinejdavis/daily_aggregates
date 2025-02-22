package com.example.daily_aggregates;


import io.micrometer.core.instrument.distribution.StepBucketHistogram;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("api/trades")
public class TradeController {

    private final TradeService tradeService;

    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }
    @GetMapping("/aggregates")
    public Map<String, Map<String, DailySummary>> getAggregatres() throws IOException {
        return tradeService.getDailySummaries(("src/test/resources/sample_trades.txt"));
    }

}
