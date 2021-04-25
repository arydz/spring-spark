package com.arydz.sparkspring.domain;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.*;
import org.apache.spark.sql.types.DataTypes;

import java.util.Arrays;
import java.util.List;

public class SimpleJob {

    private static final String STOCK_FILES_DIR = "/opt/bitnami/spark/5m_stock/01032021/nasdaq_etfs/";

    private static final String HEADERS[] =
            new String[]{"TICKER", "PER", "DATE", "TIME", "OPEN", "HIGH", "LOW", "CLOSE", "VOL", "OPENINT"};


    public Long call(JavaSparkContext sc) {
        List<Long> list = Arrays.asList(1L, 2L, 3L, 4L, 5L);

        JavaRDD<Long> rdd = sc.parallelize(list);
        return rdd.count();
    }

    public Long callPerson(SparkSession sparkSession) {

        List<Person> list = Arrays.asList(new Person("Beatka"), new Person("Adrian"));

        Encoder<Person> personEncoder = Encoders.bean(Person.class);
        Dataset<Person> dataset = sparkSession.createDataset(list, personEncoder);
        return dataset.count();
    }

    public Long readFile(SparkSession spark) {

        Dataset<Row> stocksDataSet = spark.read()
                .option("header", true)
                .option("delimiter", ",")
                .csv(STOCK_FILES_DIR)
                .toDF(HEADERS);

        spark.udf()
                .register("get_file_name", (String fullPath) -> {
                    int lastIndex = fullPath.lastIndexOf("/");
                    return fullPath.substring(lastIndex, fullPath.length() - 1);
                }, DataTypes.StringType);

        Dataset<Row> rowDataset =
                stocksDataSet.withColumn("test_column", functions.callUDF("get_file_name", functions.input_file_name()));
        long count = rowDataset.count();

        rowDataset.createOrReplaceTempView("stock");

        Dataset<Row> select_distinct_test_column_from_stock = spark.sql("SELECT DISTINCT test_column FROM stock");
        select_distinct_test_column_from_stock.show(100, false);

        System.out.println(count);
        return count;
    }
}
