package com.arydz.sparkspring.domain;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SparkSession;

import java.util.Arrays;
import java.util.List;

public class SimpleJob {

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
}
