package com.sunnyinfo.mr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class HighTemprature {
	
	
	public static class MyMapper extends Mapper<LongWritable, Text, Text, Text> {
		List<String> list = new ArrayList<String>();
		
		@Override
		protected void setup(Mapper<LongWritable, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			// TODO Auto-generated method stub
		}
		
		
		@Override
		protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			// TODO Auto-generated method stub
			String line = value.toString();
			String year = line.substring(0, 4);
			String temp = line.substring(8, line.length());
			list.add(year + "_" + temp);
			
		}
		
		@Override
		protected void cleanup(Mapper<LongWritable, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			// TODO Auto-generated method stub
			
			double tmp = Double.MIN_VALUE;
			Map<String, Double> dd = new HashMap<String, Double>();
			String[] str = {};
			double tm = 0;
			for (String string : list) {
				str = string.split("_");
				tm = Double.parseDouble(str[1]);
//				dd.put(str[0], tm);
				if (tm > tmp) {
					tmp = tm;
				}
				dd.put(str[0], tmp);
			}
			Set<String> keyset = dd.keySet();
			Iterator<String> it = keyset.iterator();
			while (it.hasNext()) {
				String key = it.next();
				context.write(new Text(key), new Text(dd.get(key) + ""));
			}
			
/*			List<Map.Entry<String, Double>> list2 = new ArrayList<Map.Entry<String,Double>>();
			Collections.sort(list2, new Comparator<Map.Entry<String, Double>>() {

				public int compare(Entry<String, Double> o1, Entry<String, Double> o2) {
					// TODO Auto-generated method stub
					
					int flag = o1.getKey().compareTo(o2.getKey());
					if (flag == 0) {
						return o1.getValue().compareTo(o2.getValue());
					}
					return flag;
					
//					double result = o2.getValue() - o1.getValue();
//					if (result > 0) {
//						return 1;
//					} else if (result == 0) {
//						return 0;
//					} else {
//						return -1;
//					}
					
				}
			});
//			for(Entry<String, Double> en : list2){  
//	            System.out.println(en.getKey()+" "+en.getValue());  
//	        }  
			
*/			
//			Set<String> keys = dd.keySet();
//			Iterator<String> iterator = keys.iterator();
//			while (iterator.hasNext()) {
//				String key = iterator.next();
//				if (tmp < dd.get(key)) {
//					tmp = dd.get(key);
//				}
//				context.write(new Text(key), new Text(tmp + ""));
//			}
			
		}
	}
	
	
	
    public static class MyReducer extends Reducer<Text, Text, Text, Text> {
    	
    	@Override
    	protected void setup(Reducer<Text, Text, Text, Text>.Context context) throws IOException, InterruptedException {
    		// TODO Auto-generated method stub
    		context.write(new Text("历史年份"), new Text("最高温度"));
    	}
    }
	
	
	
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "my-temprature-statistics");
		job.setJarByClass(HighTemprature.class);
		job.setMapperClass(MyMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		job.setReducerClass(MyReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		setArgs(job, args);
		
		int issuccessed = job.waitForCompletion(true) ? 0 : 1;
		System.exit(issuccessed);
	}
	
	
	public static void setArgs(Job job, String[] args) {
		try {
			if (args.length != 2) {
				System.out.println("arguments size is not enough!!!");
				System.out.println("Useage :yarn jar *.jar wordcount /inputdata /outputdata");
			}
			FileInputFormat.addInputPath(job, new Path(args[0]));
			FileOutputFormat.setOutputPath(job, new Path(args[1]));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
