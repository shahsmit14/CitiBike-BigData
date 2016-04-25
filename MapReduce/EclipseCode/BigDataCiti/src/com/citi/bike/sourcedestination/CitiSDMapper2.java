package com.citi.bike.sourcedestination;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class CitiSDMapper2 extends MapReduceBase implements Mapper<LongWritable, Text, IntWritable, Text>
{

	public void map(LongWritable key, Text value, OutputCollector<IntWritable, Text> output, Reporter reporter) throws IOException
	{
		String data = ((Text)value).toString().trim();

		if(data.length()>0)
		{
			String[] bikeData = data.split(",");

			int count = Integer.parseInt(bikeData[0]);
			count = count * -1;
			IntWritable countIntWritable = new IntWritable(count);

			output.collect(countIntWritable, new Text(data));
		}

	}

	/*
	@Override
	public void map(WritableComparable key, Writable value, OutputCollector<Text, Text> output, Reporter reporter)
			throws IOException {


		String data = ((Text)value).toString().trim();

		if(data.length()>0)
		{
			String[] bikeData = data.split(",");

			//output.collect(null, new Text(routeCount +"," + locationID1 + "," + locationName1 + "," + locationID2 + "," + locationName2));
			int count = Integer.parseInt(bikeData[0]);
			count = count * -1;


			output.collect(new Text(String.valueOf(count)), new Text(data));
		}
	}
	 */
}
