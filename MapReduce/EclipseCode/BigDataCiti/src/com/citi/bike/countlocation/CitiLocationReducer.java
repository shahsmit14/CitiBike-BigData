package com.citi.bike.countlocation;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class CitiLocationReducer extends MapReduceBase implements Reducer<Text, Text, Text, Text>
{

	@Override
	public void reduce(Text key, Iterator<Text> values, OutputCollector<Text, Text> output, Reporter reporter)
			throws IOException {
		
		int sourceCount = 0;
		int destinationCount = 0;
		String locationName = "";
		
		while (values.hasNext())
		{	
			String readData = null;
			readData = values.next().toString();
			System.out.println("readData" + readData);
			
			String[] mapperData = readData.trim().split(",");
			locationName = mapperData[1];
			
			if (mapperData[0].equalsIgnoreCase("S"))
			{
				sourceCount = sourceCount + 1;
			}
			else
			{
				destinationCount = destinationCount + 1;
			}
		}
		
		if (sourceCount > 0)
		{
			output.collect(null, new Text("S"+","+key+","+locationName+","+sourceCount));
		}
		if (destinationCount > 0)
		{
			output.collect(null, new Text("D"+","+key+","+locationName+","+destinationCount));
		}
	}
}
