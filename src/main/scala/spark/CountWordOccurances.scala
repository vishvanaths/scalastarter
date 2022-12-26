package com.vish
package spark

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

import scala.math.min

object CountWordOccurances {
  def main(args: Array[String]): Unit = {
    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)

    // Create a SparkContext using every core of the local machine, named RatingsCounter
    val sc = new SparkContext("local[*]", "CountWordOccurrences")

    // Load up each line of the ratings data into an RDD
    val source = sc.textFile("D:/sparkdata/book.txt")

    //source.take(5).foreach(println)
    println("Word   Count//Not on cluster")
    source.flatMap(x => x.split("\\W+"))
      .map(w => w.toLowerCase)
      .countByValue()
      .toSeq
      .sortWith(_._2 > _._2)
      .foreach(x => println(x._1 + " " + x._2))

    println("/nWord   Count//On cluster")
    source.flatMap(x => x.split("\\W+"))
      .map(w => w.toLowerCase)
      .map(x => (x, 1))
      .reduceByKey((x, y) => x+y )
      .map(x => (x._2, x._1))
      .sortByKey()
      .collect()
      .foreach(x => println(x._2 + " " + x._1))
  }

}
