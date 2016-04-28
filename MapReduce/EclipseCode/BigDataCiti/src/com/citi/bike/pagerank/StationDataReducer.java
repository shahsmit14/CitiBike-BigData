package com.citi.bike.pagerank;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class StationDataReducer extends MapReduceBase implements Reducer<Text, Text, Text, Text>
{
	@Override
	public void reduce(Text key, Iterator<Text> values, OutputCollector<Text, Text> output, Reporter reporter)
			throws IOException {

		Set<String> set = new HashSet<String>();
		
		/*
		//Non-unique outlinks
		StringBuilder outlinks = new StringBuilder();
		
		//Initial Page rank
		outlinks.append("0.2");
		outlinks.append(":");

		String prefix = "";
		*/
		
		while (values.hasNext())
		{
//			outlinks.append(prefix);
//			prefix = ",";
//			outlinks.append(values.next().toString());
//			
			set.add(values.next().toString());
		}
		
		
		StringBuffer uniqueOutLinks = new StringBuffer();
		
		//Initial Page rank
		uniqueOutLinks.append("0.2");
		uniqueOutLinks.append(":");
		
		String pre = ""; 
		
		for (String s : set) 
		{
			uniqueOutLinks.append(pre);
			pre = " ";
			uniqueOutLinks.append(s);
		} 
		
		output.collect(key, new Text(uniqueOutLinks.toString()));
	}
}
