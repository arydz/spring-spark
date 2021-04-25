package com.arydz.sparkspring.domain;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class StockService {

//    private static final String STOCK_FILES_DIR = "file:///opt/bitnami/spark/stock/";
    private static final String STOCK_FILES_DIR = "file:///opt/bitnami/spark/5m_stock/01032021/nasdaq_etfs/aaxj.us.txt";

    private SparkSession sparkSession;

    @SneakyThrows
    public Long loadStockData() {

        SparkContext sparkContext = sparkSession.sparkContext();
        JavaSparkContext javaSparkContext = new JavaSparkContext(sparkContext);
        Dataset<Row> stocksDataSet = sparkSession.read()
                .option("header", true)
                .option("delimiter", ",")
                .csv(STOCK_FILES_DIR);

        stocksDataSet.show(25);

        return stocksDataSet.count();
    }

    public long countPersons() {

        SimpleJob simpleJob = new SimpleJob();
        return simpleJob.callPerson(sparkSession);
    }

    public long readFile() {

        SimpleJob simpleJob = new SimpleJob();
        return simpleJob.readFile(sparkSession);
    }
}
