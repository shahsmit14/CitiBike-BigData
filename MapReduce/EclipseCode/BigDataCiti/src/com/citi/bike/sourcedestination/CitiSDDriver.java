package com.citi.bike.sourcedestination;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;

import com.citi.bike.countlocation.CitiLocationDriver;
import com.citi.bike.countlocation.CitiLocationMapper;
import com.citi.bike.countlocation.CitiLocationMapper2;
import com.citi.bike.countlocation.CitiLocationReducer;
import com.citi.bike.countlocation.CitiLocationReducer2;

public class CitiSDDriver {

	public static void main (String[]args) throws Exception
	{
		JobClient client = new JobClient();
		
		JobConf conf1 = new JobConf(CitiSDDriver.class);
		conf1.setJobName("CitiBike-TopRoute-1/2");

		conf1.setMapperClass(CitiSDMapper1.class);
		conf1.setReducerClass(CitiSDReducer1.class);
	
		conf1.setOutputKeyClass(Text.class);
		conf1.setOutputValueClass(Text.class);

		conf1.setNumReduceTasks(5);
		
		FileInputFormat.setInputPaths(conf1, new Path("CleanedInput"));
		FileOutputFormat.setOutputPath(conf1, new Path("TempSDInput"));

		client.setConf(conf1);

		try 
		{
			JobClient.runJob(conf1);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		
		JobConf conf2 = new JobConf(CitiSDDriver.class);
		conf2.setJobName("CitiBike-TopRoute-2/2");

		conf2.setMapperClass(CitiSDMapper2.class);
		conf2.setReducerClass(CitiSDReducer2.class);
	
		conf2.setMapOutputKeyClass(IntWritable.class);
		conf2.setMapOutputValueClass(Text.class);
		
		//conf2.setOutputKeyClass(Text.class);
		//conf2.setOutputValueClass(Text.class);

		//conf.setNumReduceTasks(5);
		
		FileInputFormat.setInputPaths(conf2, new Path("TempSDInput"));
		FileOutputFormat.setOutputPath(conf2, new Path("TopRouteOutput"));

		client.setConf(conf2);

		try 
		{
			JobClient.runJob(conf2);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		
	}
}
