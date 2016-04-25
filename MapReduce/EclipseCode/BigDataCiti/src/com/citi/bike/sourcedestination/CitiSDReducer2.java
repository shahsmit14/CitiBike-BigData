package com.citi.bike.sourcedestination;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import com.citi.bike.dao.LocationID;
import com.citi.bike.dao.LocationRoute;
import com.google.protobuf.DescriptorProtos.SourceCodeInfo.Location;

public class CitiSDReducer2 extends MapReduceBase implements Reducer<IntWritable, Text, IntWritable, Text>
{

	@Override
	public void reduce(IntWritable key, Iterator<Text> values, OutputCollector<IntWritable, Text> output, Reporter reporter)
			throws IOException {

		while (values.hasNext())
		{	
			String readData = null;
			readData = values.next().toString();
			
			output.collect(null, new Text(readData));
		}
	}
	
	/*
	@Override
	public void reduce(Text key, Iterator<Text> values, OutputCollector<Text, Text> output, Reporter reporter)
			throws IOException {

		while (values.hasNext())
		{	
			String readData = null;
			readData = values.next().toString();
			
			output.collect(key, new Text(readData));
		}
	}
	*/
	/*
	@Override
	public void reduce(Text key, Iterator<Text> values, OutputCollector<Text, Text> output, Reporter reporter)
			throws IOException {
		
		ArrayList<LocationRoute> locationRouteList = new ArrayList<LocationRoute>();
		
		
		//output.collect(new Text("1"), new Text(data));
		
		//output.collect(new Text(bikeData[0]), new Text(bikeData[1]+","+bikeData[2]+","+bikeData[3]));
		while (values.hasNext())
		{	
			String readData = null;
			readData = values.next().toString();
			
			String[] mapperData = readData.trim().split(",");
			
			
			String id = mapperData[0];
			int count = Integer.parseInt(mapperData[1]);
			String locationName1= mapperData[2];
			String locationName2 = mapperData[3]; 
			
			LocationRoute objLocationRoute = new LocationRoute(id, count, locationName1, locationName2);
			
			locationRouteList.add(objLocationRoute);
		}
		
		
		//Sort the Array List by Count
		Collections.sort(locationRouteList);
		
		if (locationRouteList.size() > 10)
		{
			for (int i=0; i<10; i++)
			{
				output.collect(null, new Text(locationRouteList.get(i).toString()));
			}
		}
		else
		{
			for (LocationRoute locationRoute: locationRouteList)
			{
				output.collect(null, new Text(locationRoute.toString()));
			}
		}
	}
	*/
}
