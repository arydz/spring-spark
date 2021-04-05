package com.arydz.sparkspring.domain;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/stock")
public class StockController {

    private final StockService stockService;

    @GetMapping
    public String loadDataFromFiles() {
        Map<String, Long> count = stockService.getCount(Arrays.asList("Beatka, Adrian"));
        return "success: " + count;
    }
}
