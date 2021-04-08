# Read Me First
The following was discovered as part of building this project:

* The original package name 'com.arydz.livyspring.spring-spark' is invalid and this project uses 'com.arydz.livyspring.springspark' instead.

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

#### How to integrate your application with dockerized Spark Cluster (standalone mode)?
(This is the simples option)
1. Download apache spark on your local machine 
1. In gradle dependencies block, put `compile fileTree(dir: "$sparkHome/jars", include: ['*.jar'])`
2. This will load same libraries that are used by docker container
   
(Other option is to replace all jars in $SPARK_HOME/jars in your docker container)

#### How to sole libraries problems with Spring and Spark?
1. In gradle file exclude root-cause files
```configurations {
    all {
        exclude group: 'org.springframework.boot', module: 'spring-boot-starter-logging'
        exclude group: 'ch.qos.logback', module: 'logback-classic'
    }
}
```
https://discuss.gradle.org/t/download-some-dependencies-and-copy-them-to-a-local-folder/6246
2. There can be also problems with different versions like `gson-2.8.6.jar` library which is required by Spring
3. It's an option to replace `gson-2.x.x.jar` library with the newer version

#### How to read local files on dockerized Spark Cluster (standalone mode)?
*(in google: docker spark local file)*
- https://github.com/bitnami/bitnami-docker-spark/issues/23
- https://www.java-success.com/02-apache-zeppelin-docker-tutorial-read-file-local-file-system/
1. Create volume on Docker (docker-compose.yaml)
2. Copy desired files (volume block for each spark'ish service)
3. On your local machine create same dir and put there files

Still, it's recommended to use hdfs instead of local file system.

*examples:*
- https://stackoverflow.com/questions/59047740/unable-to-read-local-files-in-spark-kubernetes-cluster-mode




### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

