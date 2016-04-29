package com.citi.bike.pagerank;

import org.apache.hadoop.io.Writable; 
import org.apache.hadoop.io.WritableComparable; 
import org.apache.hadoop.mapred.MapReduceBase; 
import org.apache.hadoop.mapred.Mapper; 
import org.apache.hadoop.mapred.OutputCollector; 
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;

import org.apache.hadoop.io.Text; 

public class StationIterMapper extends MapReduceBase implements Mapper<WritableComparable, Writable, Text, Text> 
{ 

	public void map(WritableComparable key, Writable value, 
			OutputCollector output, Reporter reporter) throws IOException 
	{
		
		// get the current station
		String data = ((Text)value).toString(); 
		int index = data.indexOf(":"); 

		if (index == -1) 
		{ 
			return; 
		} 

		// split into (station,PR) and outinks 
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

		// parse current score
		double currScore = 0.2;

		try 
		{ 
			currScore = Double.parseDouble(pagerank); 
		} 
		catch (Exception e) 
		{ 
			currScore = 0.2; //0.5 from 1
		} 

		// get number of outlinks
		data = data.substring(index+1); 
		String[] pages = data.split(" "); 
		int numoutlinks = 0;

		if (pages.length == 0) 
		{
			numoutlinks = 1;
		} 
		else 
		{
			for (String page : pages) 
			{ 
				if(page.length() > 0) 
				{
					numoutlinks = numoutlinks + 1;
				}
			} 
		}

		// collect each outlink, with the dampened PR of its inlink, and its inlink
		Text toEmit = new Text((new Double(.85 * currScore / numoutlinks)).toString()); //Changed the Dampening Factor from 0.98 to 0.85

		for (String page : pages) 
		{ 
			if(page.length() > 0) 
			{
				output.collect(new Text(page), toEmit); 
				//output.collect(new Text(page), new  Text(" " + pagetitle)); 
			}
		} 

		// collect the inlink with its dampening factor, and all outlinks
		output.collect(new Text(pagetitle), new Text("0.000295858"));//507 stations //("0.000000481")); //375//481//(".000000075")); //0.000000563 //0.02 to 0.0000000754 
		output.collect(new Text(pagetitle), new Text(" " + data));//(" " + data)); 
	} 
} 
