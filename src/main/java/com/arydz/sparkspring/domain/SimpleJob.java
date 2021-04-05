package com.arydz.sparkspring.domain;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;
import java.util.List;

public class SimpleJob {

    public Long call(JavaSparkContext sc) {
        List<Long> list = Arrays.asList(1L, 2L, 3L, 4L, 5L);
        JavaRDD<Long> rdd = sc.parallelize(list);
        return rdd.count();
    }
}
