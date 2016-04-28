package com.citi.bike.readdata;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.lang.management.GarbageCollectorMXBean;
import java.util.ArrayList;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;


public class CitiDriver {

	public static void main (String[]args) throws Exception
	{
	
		JobClient client = new JobClient();
		JobConf conf = new JobConf(CitiDriver.class);
		conf.setJobName("CitiBike-CleanData");

		conf.setMapperClass(CitiMapper.class);
		conf.setReducerClass(CitiReducer.class);
		
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(Text.class);

		conf.setNumReduceTasks(5);
		
		FileInputFormat.setInputPaths(conf, new Path(args[0]));
		FileOutputFormat.setOutputPath(conf, new Path(args[1]));

		client.setConf(conf);

		try 
		{
			JobClient.runJob(conf);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	
	}
}

