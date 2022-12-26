package com.vish
package spark

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

import scala.math.min

object MinMaxTemperatureAtLocation {
  def main(args: Array[String]): Unit = {
    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)

    // Create a SparkContext using every core of the local machine, named RatingsCounter
    val sc = new SparkContext("local[*]", "MinMaxtempartaturFinder")

    // Load up each line of the ratings data into an RDD
    val lines = sc.textFile("D:/sparkdata/1800.csv")

    //lines.take(5).foreach(println)
    println("Station  Year  Month Max Temperature")
    findTemperature(lines, "TMAX")
    println("\nStation  Year  Month Min Temperature")
    findTemperature(lines, "TMIN")
  }

  private def findTemperature(lines: RDD[String], typeOfTemperature :String) = {
    lines.map(x => parseLine(x))
      .filter(x => x._4 == typeOfTemperature)
      .map(x => ((x._1, x._2, x._3), x._5.toFloat))
      .reduceByKey((x, y) => min(x, y))
      .collect()
      .sortBy(_._1)
      .foreach(x => println((x._1)._1 + " " + (x._1)._2 + " " + (x._1)._3 + "  " + x._2))
  }

  private def parseLine(x: String) = {

    val split = x.split(",")
    val station = split(0)
    val year = split(1).substring(0,4)
    val month = split(1).substring(4,6)
    val entryType = split(2)
    val temperature = split(3).toFloat * 0.1f * (9.0 / 5.0f) + 32.0f
    (station, year, month, entryType, temperature)

  }
}
