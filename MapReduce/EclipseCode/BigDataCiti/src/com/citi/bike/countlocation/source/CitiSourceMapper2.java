package com.citi.bike.countlocation.source;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class CitiSourceMapper2 extends MapReduceBase implements Mapper<LongWritable, Text, IntWritable, Text>
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
}
