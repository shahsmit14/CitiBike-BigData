package com.citi.bike.pagerank;

import java.io.IOException; 

import org.apache.hadoop.io.Text; 
import org.apache.hadoop.io.Writable; 
import org.apache.hadoop.io.WritableComparable; 
import org.apache.hadoop.mapred.MapReduceBase; 
import org.apache.hadoop.mapred.Mapper; 
import org.apache.hadoop.mapred.OutputCollector; 
import org.apache.hadoop.mapred.Reporter; 
import org.apache.hadoop.io.*; 

public class StationViewerMapper extends MapReduceBase implements Mapper<WritableComparable, Writable, Text, Text> 
{ 

	public void map(WritableComparable key, Writable value, 
			OutputCollector output, Reporter reporter) throws IOException 
	{ 

		// get the current page
		String data = ((Text)value).toString(); 
		int index = data.indexOf(":"); 
		
		if (index == -1) 
		{ 
			return; 
		} 

		// split into title and PR (tab or variable number of blank spaces)
		String toParse = data.substring(0, index).trim(); 
		String[] splits = toParse.split("\t"); 
		
		if(splits.length == 0) 
		{
			splits = toParse.split(" ");
			
			if(splits.length == 0) 
			{
				return;
			}
		}
		
		String pagetitle = splits[0].trim(); 
		String pagerank = splits[splits.length - 1].trim();

		// parse score
		double currScore = 0.0;
		
		try 
		{ 
			currScore = Double.parseDouble(pagerank); 
		} 
		catch (Exception e) 
		{ 
			currScore = 0.0;
		} 

		// collect
		//output.collect(new FloatWritable((float) - currScore), key); 
		output.collect(new FloatWritable((float) - currScore), new Text(pagetitle)); 
	} 
} 