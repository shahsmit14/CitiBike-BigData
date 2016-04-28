package com.citi.bike.readdata;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text; 
import org.apache.hadoop.io.WritableComparable; 
import org.apache.hadoop.mapred.MapReduceBase; 
import org.apache.hadoop.mapred.OutputCollector; 
import org.apache.hadoop.mapred.Reducer; 
import org.apache.hadoop.mapred.Reporter;


import java.lang.StringBuilder; 
import java.util.*; 


public class CitiReducer extends MapReduceBase implements Reducer<Text, Text, Text, Text>
{

	@Override
	public void reduce(Text key, Iterator<Text> values, OutputCollector<Text, Text> output, Reporter reporter)
			throws IOException {
		
		while (values.hasNext())
		{
			String readData = null;
			readData = values.next().toString();
			//System.out.println("readDAta" + readData);
			output.collect(null, new Text(readData.toString()));
		}
	}
}
