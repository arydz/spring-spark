package com.arydz.sparkspring.domain;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class StockService {

    private static final String STOCK_FILES_DIR = "file:///opt/bitnami/spark/stock/";

    private SparkSession sparkSession;

    @SneakyThrows
    public Long loadStockData() {

        Dataset<Row> stocksDataSet = sparkSession.read()
                .option("header", true)
                .option("delimiter", ",")
                .csv(STOCK_FILES_DIR);

        stocksDataSet.show(150);

        return stocksDataSet.count();
    }
}
