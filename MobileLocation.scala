package com.sunnyinfo.scala.day06.MobileLocation

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 在一定的时间范围内,手机用户在基站范围停留的总时长去TopN
  *
  * Created by czf on Wed Dec 27 PM14:43 CST 2017}
  **/
object MobileLocation {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("MobileLocation").setMaster("local[2]")
    val sc = new SparkContext(conf)
    //获取数据
    val files: RDD[String] = sc.textFile("C://Users/Administrator/Desktop/log")
    val phoneAndLacAndTime: RDD[((String, String), Long)] = files.map(line => {
      val fields: Array[String] = line.split(",")
      val phone = fields(0)
      val time = fields(1).toLong
      val lac = fields(2)
      val eventType = fields(3).toInt
      val time_long = if (eventType == 1) -time else time

      ((phone, lac), time_long)
    })
    //用户在某基站停留的总时长
    val sumedPhoneAndLacAndTime: RDD[((String, String), Long)] = phoneAndLacAndTime.reduceByKey(_+_)
    //把计算的数据改一下结构,目的是需要和经纬度做对接(join)
    val lacAndPhoneAndTime: RDD[(String, (String, Long))] = sumedPhoneAndLacAndTime.map(line => {
      val phone = line._1._1
      val lac = line._1._2
      val time_long = line._2

      (lac, (phone, time_long))
    })
    val lacInfo: RDD[String] = sc.textFile("C://Users/Administrator/Desktop/lac_info.txt")
    val lacAndXY: RDD[(String, (String, String))] = lacInfo.map(line => {
      val fields = line.split(",")
      val lac = fields(0)
      val x = fields(1)
      val y = fields(2)

      (lac, (x, y))
    })

    val joined: RDD[(String, ((String, Long), (String, String)))] = lacAndPhoneAndTime.join(lacAndXY)
    //以手机号进行分组并按照停留时间来降序排序
    val phoneAndTimeAndLacAndXY: RDD[(String, (Long, String, (String, String)))] = joined.map(line => {
      val phone = line._2._1._1
      val lac = line._1
      val time = line._2._1._2
      val xy = line._2._2

      (phone, (time, lac, xy))
    })

    val res: RDD[(String, List[(String, (Long, String, (String, String)))])] = phoneAndTimeAndLacAndXY.groupBy(_._1).mapValues(_.toList.sortBy(_._1).reverse.take(2))
    println(res.collect.toBuffer)

    sc.stop()
  }
}
