package com.vish
package spark

import org.apache.log4j._
import org.apache.spark.SparkContext

object FriendsAgeFinderExecution {
  def main(args: Array[String]): Unit = {
    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)

    // Create a SparkContext using every core of the local machine, named RatingsCounter
    val sc = new SparkContext("local[*]", "Friends Scorefinder")

    // Load up each line of the ratings data into an RDD
    val lines = sc.textFile("D:/sparkdata/fakefriends-noheader.csv")

    //lines.take(5).foreach(println)

    lines.map(x => (x.split(",")(2).toInt, x.split(",")(3).toInt))
      .map(x => (x._1, (x._2, 1)))
      .reduceByKey( (x, y) => (x._1+y._1, x._2 + y._2))
      .mapValues(x => x._1/x._2)
      .sortByKey()
      .collect()
      .foreach(println)
  }
}
