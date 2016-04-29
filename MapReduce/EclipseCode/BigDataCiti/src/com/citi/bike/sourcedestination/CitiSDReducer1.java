package com.citi.bike.sourcedestination;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class CitiSDReducer1 extends MapReduceBase implements Reducer<Text, Text, Text, Text>
{
	@Override
	public void reduce(Text key, Iterator<Text> values, OutputCollector<Text, Text> output, Reporter reporter)
			throws IOException {

		int routeCount = 0;
		double totalDuration = 0;
		String locationName1 = "";
		String locationName2 = "";
		String locationID1 = "";
		String locationID2 = "";
		String locationLat1 = "";
		String locationLong1 = "";
		String locationLat2 = "";
		String locationLong2 = "";

		while (values.hasNext())
		{	
			String readData = null;
			readData = values.next().toString();
		
			String[] mapperData = readData.trim().split(",");
			
			locationID1 = mapperData[0];
			locationName1 = mapperData[1];
			locationLat1 = mapperData[2];
			locationLong1 = mapperData[3];
			
			locationID2 = mapperData[4];
			locationName2 = mapperData[5];
			locationLat2 = mapperData[6];
			locationLong2 = mapperData[7];
			
			routeCount = routeCount + 1;
			totalDuration = totalDuration + Double.valueOf(mapperData[8]);
		}
		
		double averageDuration = totalDuration / routeCount;
		
		output.collect(null, new Text(routeCount +"," + locationID1 + "," + locationName1 + "," + locationLat1 + "," + locationLong1 + "," + locationID2 + "," + locationName2 + "," + locationLat2 + "," + locationLong2 + "," + averageDuration));
	}
}
