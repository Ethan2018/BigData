package com.sunnyinfo.scala.day05

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
/**
  * Created by czf on Tue Dec 26 AM10:45 CST 2017}
  **/
object ScalaWC {
  def main(args: Array[String]): Unit = {
    //配置信息类
    //setAppName("ScalaWC") 设置应用程序名称
    //setMaster("local") 指定本地运行该程序
    //local代表启用一个线程来运行
    //local[2]代表启用两个线程来运行
    //local[*]代表有多少
    val conf: SparkConf = new SparkConf().setAppName("ScalaWC")/*.setMaster("local[*]")*/
    //创建Spark集群入口类
    val sc: SparkContext = new SparkContext(conf)
    val lines: RDD[String] = sc.textFile(args(0))
    // val lines: RDD[String] = sc.parallelize(Array("hello tom", "hello jerry hello jerry", "hello kitty"))
    val words: RDD[String] = lines.flatMap(_.split(" "))
    val pair: RDD[(String, Int)] = words.map((_, 1))
    val reduced: RDD[(String, Int)] = pair.reduceByKey((x, y) => x + y)
    val result: RDD[(String, Int)] = reduced.sortBy(_._2, false)
//    println(result.collect().toBuffer)
    result.saveAsTextFile(args(1))
    sc.stop()
  }
}
