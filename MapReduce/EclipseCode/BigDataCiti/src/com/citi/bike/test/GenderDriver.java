package com.citi.bike.test;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;

import com.citi.bike.countlocation.CitiLocationDriver;
import com.citi.bike.countlocation.CitiLocationMapper;
import com.citi.bike.countlocation.CitiLocationReducer;

public class GenderDriver {

	public static void main (String[]args) throws Exception
	{
		JobClient client = new JobClient();
		JobConf conf = new JobConf(GenderDriver.class);
		conf.setJobName("GenderCount");

		conf.setMapperClass(GenderMapper.class);
		conf.setReducerClass(GenderReducer.class);
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(Text.class);

		//conf.setNumReduceTasks(5);

		FileInputFormat.setInputPaths(conf, new Path("CleanedInput"));
		FileOutputFormat.setOutputPath(conf, new Path("GenderOutput1"));

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
