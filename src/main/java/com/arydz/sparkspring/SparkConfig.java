package com.arydz.sparkspring;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfig {

    @Value("${spark.app.name}")
    private String appName;
    @Value("${spark.master}")
    private String masterUri;

    @Bean
    public SparkConf conf() {
        return new SparkConf()
                .setAppName(appName)
                .setMaster(masterUri);
    }

    @Bean
    public JavaSparkContext sc() {

        SparkSession spark = SparkSession.builder()
                .config(conf())
                .getOrCreate();

        SparkContext sparkContext = spark.sparkContext();
        return new JavaSparkContext(sparkContext);
    }

    // todo clean up
    /*@Bean
    public SparkConf conf() {
        return new SparkConf()
                .setSparkHome(sparkHome)
                .set("spark.driver.host", "localhost")
                .set("spark.driver.port", "4040")
                .set("spark.driver.allowMultipleContexts", String.valueOf(true))
                .setAppName(appName)
                .setMaster(masterUri);
    }

    @Bean
    public JavaSparkContext sc() {

        SparkSession spark = SparkSession.active();

//        SparkSession spark = SparkSession.builder()
//                .config(conf())
//                .getOrCreate();

        SparkContext sparkContext = spark.sparkContext();

        return new JavaSparkContext(sparkContext);

//        Option<SparkSession> activeSession = SparkSession.getActiveSession();
//        SparkSession sparkSession = activeSession.get();
//        SparkContext sparkContext = sparkSession.sparkContext();
//
//        return new JavaSparkContext(conf());
    }*/

   /* @Bean
    public SparkConf sparkConf() {
        return new SparkConf()
                .setSparkHome(sparkHome)
                .setAppName("Standalone Spark Test")
                .set("spark.driver.host", "10.7.90.150")
                .set("spark.driver.port", "4040")
                .setMaster("spark://10.7.90.150:4040")
                .setJars(new String[]{"/home/arydz/workspace/learning/spring-spark/build/libs/spring-spark-1.0-SNAPSHOT.jar"});
//                .set("spark.cassandra.connection.host",env.getProperty(AppConfig.CONTACTPOINTS));
    }

    @Bean
    public JavaSparkContext javaSparkContext() {
        return new JavaSparkContext(sparkConf());
    }
*/
}