package com.sunnyinfo.scala.day06.objectcount

import java.net.URL

import org.apache.spark.{HashPartitioner, Partitioner, SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

import scala.collection.mutable

/**
  * Created by czf on Wed Dec 27 PM16:42 CST 2017}
  **/
object ProjectCount2 {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("ObjectCount").setMaster("local")
    val sc: SparkContext = new SparkContext(conf)
    val file: RDD[String] = sc.textFile("C://Users/Administrator/Desktop/access.txt")
    val urlAndOne: RDD[(String, Int)] = file.map(line => {
      val fields = line.split("\t")
      val url = fields(1)
      (url, 1)
    })
    val sumed: RDD[(String, Int)] = urlAndOne.reduceByKey(_+_)
    val projectAndUrlAndCount: RDD[(String, (String, Int))] = sumed.map(line => {
      val url = line._1
      val count = line._2
      val project = new URL(url).getHost
      (project, (url, count))
    })
    //调用Spark默认的分区器在此时会发生哈希碰撞导致的数据倾斜,需要自定义分区器
//    val res: RDD[(String, (String, Int))] = projectAndUrlAndCount.partitionBy(new HashPartitioner(5))
//    res.saveAsTextFile("C://Users/Administrator/Desktop")
    //获取所有学科信息
    val projects: Array[String] = projectAndUrlAndCount.keys.distinct().collect()
    //调用自定义分区器获取分区号
    val partitioner: ProjectPartitioner = new ProjectPartitioner(projects)
    val partitioned: RDD[(String, (String, Int))] = projectAndUrlAndCount.partitionBy(partitioner)
    val res: RDD[(String, (String, Int))] = partitioned.mapPartitions(iter => {
      iter.toList.sortBy(_._2._2).reverse.take(3).iterator
    })
    res.saveAsTextFile("C://Users/Administrator/Desktop/out")

    sc.stop()
  }
}
class ProjectPartitioner(projects: Array[String]) extends Partitioner {
  //存储学科和对应的分区号
  private val projectAndNum: mutable.HashMap[String, Int] = new mutable.HashMap[String, Int]()
  //计数器,用来生成分区号
  var n = 0
  for (p <- projects) {
    projectAndNum += (p -> n)
    n += 1
  }
  //获取分区数
  override def numPartitions: Int = projects.length
  //获取分区号
  override def getPartition(key: Any): Int = projectAndNum.getOrElse(key.toString, 0)
}