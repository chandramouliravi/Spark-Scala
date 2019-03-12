package org.test
// Information from #  https://www.stubbornjava.com/posts/typesafe-config-features-and-example-usage
// https://www.stubbornjava.com/posts/database-connection-pooling-in-java-with-hikaricp

import java.util.concurrent.TimeUnit

import com.typesafe.config.ConfigFactory

object typeSafeConfig {

  def main(args: Array[String]) {

    println("Hello world")

    val props = ConfigFactory.load()
    //which defaults to parsing application.conf, application.json, and application.properties on the classpath
    System.out.println(" name from application Conf using Load() = " + props.getString("name"))
    //Keep on adding getString for as many Parameters as possible
    //If there are 50 parameters, its 50 lines of code in here

    val defaultConfig = ConfigFactory.parseResources("defaults.conf")
    //Difference is we can give our own file name
    val fallbackConfig = ConfigFactory.parseResources("overrides.conf")
      .withFallback(defaultConfig)
      .resolve();

    System.out.println("name: {}", defaultConfig.getString("conf.name"))
    System.out.println("name: {}", fallbackConfig.getString("conf.name"))

    System.out.println("title: {}", defaultConfig.getString("conf.title"))
    System.out.println("title: {}", fallbackConfig.getString("conf.title"))

    System.out.println("combined: {}", fallbackConfig.getString("conf.combined"))

    System.out.println("redis.ttl minutes: {}", fallbackConfig.getDuration("redis.ttl", TimeUnit.MINUTES))
    System.out.println("redis.ttl seconds: {}", fallbackConfig.getDuration("redis.ttl", TimeUnit.SECONDS))
    System.out.println("maxChunkSize  bytes: {}", fallbackConfig.getMemorySize("uploadService.maxChunkSize").toBytes())
    System.out.println("maxFileSize  bytes: {}", fallbackConfig.getMemorySize("uploadService.maxFileSize")toBytes())
    System.out.println("redis.ttl seconds: {}", fallbackConfig.getIntList("conf.nested.whitelistIds"))
    System.out.println("redis.ttl seconds: {}", fallbackConfig.getBoolean("featureFlags.featureA"))
    System.out.println("redis.ttl seconds: {}", fallbackConfig.getBoolean("featureFlags.featureB"))

  }// end of main

}// end of helloworld
