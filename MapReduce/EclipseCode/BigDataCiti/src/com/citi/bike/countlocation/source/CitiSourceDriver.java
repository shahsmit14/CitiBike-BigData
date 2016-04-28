package com.citi.bike.countlocation.source;

import javax.security.auth.callback.TextOutputCallback;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;
import org.apache.hadoop.mapred.jobcontrol.JobControl;

import com.citi.bike.sourcedestination.CitiSDDriver;
import com.citi.bike.sourcedestination.CitiSDMapper1;
import com.citi.bike.sourcedestination.CitiSDMapper2;
import com.citi.bike.sourcedestination.CitiSDReducer1;
import com.citi.bike.sourcedestination.CitiSDReducer2;

public class CitiSourceDriver {

	public static void main (String[]args) throws Exception
	{	
		JobConf conf1 = new JobConf(CitiSourceDriver.class);
		conf1.setJobName("CitiBike-TopRoute-1/2");

		conf1.setMapperClass(CitiSourceMapper1.class);
		conf1.setReducerClass(CitiSourceReducer1.class);
	
		conf1.setOutputKeyClass(Text.class);
		conf1.setOutputValueClass(Text.class);

		conf1.setInputFormat(TextInputFormat.class);
		conf1.setOutputFormat(TextOutputFormat.class);
		
		conf1.setNumReduceTasks(5);
		
		FileInputFormat.setInputPaths(conf1, new Path(args[0]));
		FileOutputFormat.setOutputPath(conf1, new Path(args[1]));

		try 
		{
			JobClient.runJob(conf1);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		
		JobConf conf2 = new JobConf(CitiSourceDriver.class);
		conf2.setJobName("CitiBike-TopRoute-2/2");

		conf2.setMapperClass(CitiSourceMapper2.class);
		conf2.setReducerClass(CitiSourceReducer2.class);
	
		conf2.setMapOutputKeyClass(IntWritable.class);
		conf2.setMapOutputValueClass(Text.class);
		
		conf2.setInputFormat(TextInputFormat.class);
		conf2.setOutputFormat(TextOutputFormat.class);
		
		//conf2.setOutputKeyClass(Text.class);
		//conf2.setOutputValueClass(Text.class);

		//conf2.setNumReduceTasks(5);
		
		FileInputFormat.setInputPaths(conf2, new Path(args[1]));
		FileOutputFormat.setOutputPath(conf2, new Path(args[2]));

		
		try 
		{
			JobClient.runJob(conf2);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		
	//	JobControl jbControl = new JobControl("JbControl");
		
		
	}
}
