package com.arydz.sparkspring.domain;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class StockController {

    private final StockService stockService;

    @GetMapping("/stock")
    public String loadDataFromFiles() {
        long count = stockService.loadStockData();
        return "count: " + count;
    }

    @GetMapping("/person")
    public String countPerson() {
        long count = stockService.countPersons();
        return "count: " + count;
    }
}
