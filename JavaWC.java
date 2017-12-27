package com.sunnyinfo.scala;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.util.Arrays;

/**
 * Created by czf on Tue Dec 26 PM14:08 CST 2017}
 **/
public class JavaWC {
    public static void main(String[] args) {
        //创建配置信息类
        final SparkConf conf = new SparkConf().setAppName("JavaWC").setMaster("local");
        final JavaSparkContext jsc = new JavaSparkContext();
        //获取数据
        final JavaRDD<String> lines = jsc.textFile(args[0]);
        //切分并压平
        final JavaRDD<String> words = lines.flatMap(new FlatMapFunction<String, String>() {
            public Iterable<String> call(String s) throws Exception {
                return Arrays.asList(s.split(" "));
            }
        });
        //把单词生成pair
        final JavaPairRDD<String, Integer> pair = words.mapToPair(new PairFunction<String, String, Integer>() {

            public Tuple2<String, Integer> call(String s) throws Exception {
                return new Tuple2<String, Integer>(s, 1);
            }
        });
        //聚合数据
        final JavaPairRDD<String, Integer> reduced = pair.reduceByKey(new Function2<Integer, Integer, Integer>() {
            public Integer call(Integer integer, Integer integer2) throws Exception {
                return integer + integer2;
            }
        });
        //Java接口Api并没有提供SortBy算子,需要把数据key-value调换在用sortByKey进行排序,然后再调换回来
        final JavaPairRDD<Integer, String> swaped = reduced.mapToPair(new PairFunction<Tuple2<String, Integer>, Integer, String>() {
            public Tuple2<Integer, String> call(Tuple2<String, Integer> stringIntegerTuple2) throws Exception {
                return stringIntegerTuple2.swap();
            }
        });
        final JavaPairRDD<Integer, String> sorted = swaped.sortByKey(false);
        final JavaPairRDD<String, Integer> res = sorted.mapToPair(new PairFunction<Tuple2<Integer, String>, String, Integer>() {
            public Tuple2<String, Integer> call(Tuple2<Integer, String> integerStringTuple2) throws Exception {
                return integerStringTuple2.swap();
            }
        });
        System.out.println(res.collect());
        jsc.stop();

    }
}
