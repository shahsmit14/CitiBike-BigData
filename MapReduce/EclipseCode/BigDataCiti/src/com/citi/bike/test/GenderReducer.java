package com.citi.bike.test;
import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
public class GenderReducer extends MapReduceBase implements Reducer<Text, Text, Text, Text>{

	public void reduce(Text key, Iterator<Text> value, OutputCollector<Text, Text> output, Reporter reporter)
			throws IOException {
		// TODO Auto-generated method stub
		int sum = 0;

		System.out.println("Values: "+ value);

		while(value.hasNext()){

			sum = sum + Integer.valueOf(value.next().toString());
		}
		System.out.println("count" + sum);
		output.collect(key, new Text(String.valueOf(sum)));
	}



}
