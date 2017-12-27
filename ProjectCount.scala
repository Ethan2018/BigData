package com.sunnyinfo.scala.day06.objectcount

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
  * Created by czf on Wed Dec 27 PM16:23 CST 2017}
  **/
object ProjectCount {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("ObjectCount").setMaster("local")
    val sc: SparkContext = new SparkContext(conf)
    val projects = Array("http://java.learn.com", "http://ui.learn.com", "http://bigdata.learn.com", "http://android.learn.com", "http://h5.learn.com")
    val file: RDD[String] = sc.textFile("C://Users/Administrator/Desktop/access.txt")
    val urlAndOne: RDD[(String, Int)] = file.map(line => {
      val fields = line.split("\t")
      val url = fields(1)
      (url, 1)
    })
    val sumed: RDD[(String, Int)] = urlAndOne.reduceByKey(_+_)
    //我们会将经常用到的数据或者是shuffle后的比较重要的数据先缓存一下
    //1.便于以更快的速度读取该数据
    //2.提高数据的安全性
    val cached: RDD[(String, Int)] = sumed.persist()
    for (p <- projects) {
      val filterdProject: RDD[(String, Int)] = cached.filter(_._1.startsWith(p))
      val res: Array[(String, Int)] = filterdProject.sortBy(_._2, false).take(3)
      println(res.toBuffer)
    }
    sc.stop()
  }
}
