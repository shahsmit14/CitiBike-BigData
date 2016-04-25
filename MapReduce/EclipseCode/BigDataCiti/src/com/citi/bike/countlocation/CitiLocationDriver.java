package com.citi.bike.countlocation;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;

public class CitiLocationDriver {

	public static void main (String[]args) throws Exception
	{
	
		JobClient client = new JobClient();
		
		JobConf conf1 = new JobConf(CitiLocationDriver.class);
		conf1.setJobName("CitiBike-TopLocations-1/2");

		conf1.setMapperClass(CitiLocationMapper.class);
		conf1.setReducerClass(CitiLocationReducer.class);
	
		conf1.setOutputKeyClass(Text.class);
		conf1.setOutputValueClass(Text.class);

		//conf.setNumReduceTasks(5);
		
		FileInputFormat.setInputPaths(conf1, new Path("CleanedInput"));
		FileOutputFormat.setOutputPath(conf1, new Path("TempInput"));

		client.setConf(conf1);

		try 
		{
			JobClient.runJob(conf1);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		
		JobConf conf2 = new JobConf(CitiLocationDriver.class);
		conf2.setJobName("CitiBike-TopLocations-2/2");

		conf2.setMapperClass(CitiLocationMapper2.class);
		conf2.setReducerClass(CitiLocationReducer2.class);
	
		conf2.setOutputKeyClass(Text.class);
		conf2.setOutputValueClass(Text.class);

		//conf.setNumReduceTasks(5);
		
		FileInputFormat.setInputPaths(conf2, new Path("TempInput"));
		FileOutputFormat.setOutputPath(conf2, new Path("TopLocationOutput"));

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
