package com.citi.bike.countlocation.source;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class CitiSourceMapper1 extends MapReduceBase implements Mapper<WritableComparable, Writable, Text, Text>
{
	@Override
	public void map(WritableComparable key, Writable value, OutputCollector<Text, Text> output, Reporter reporter)
			throws IOException {
		
		String data = ((Text)value).toString().trim();

		if(data.length()>0)
		{
			String[] bikeData = data.split(",");

			output.collect(new Text(bikeData[4]), new Text (bikeData[4] + "," + bikeData[5]));			
		}
	}
}
