package com.vish
package spark

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext

object TotalAmountSpendByCustomer {
  def main(args: Array[String]): Unit = {
    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)

    // Create a SparkContext using every core of the local machine, named RatingsCounter
    val sc = new SparkContext("local[*]", "TotalAmountSpendByCustomer")

    // Load up each line of the ratings data into an RDD
    val source = sc.textFile("D:/sparkdata/customer-orders.csv")

    //source.take(5).foreach(println)
    println("Customer   Total Amount Spent")
    source.map(x => {
              val split = x.split(",")
              (split(0).toInt, split(2).toFloat)
            })
      .reduceByKey((x,y) => x+y)
      .sortBy(_._2)
      .collect()
      .foreach(x => println(x._1 + "  " + x._2))
  }

}
