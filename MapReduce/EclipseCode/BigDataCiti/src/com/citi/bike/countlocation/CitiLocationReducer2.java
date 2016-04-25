package com.citi.bike.countlocation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import com.citi.bike.dao.LocationID;

public class CitiLocationReducer2 extends MapReduceBase implements Reducer<Text, Text, Text, Text>
{

	@Override
	public void reduce(Text key, Iterator<Text> values, OutputCollector<Text, Text> output, Reporter reporter)
			throws IOException {
		
		ArrayList<LocationID> locationList = new ArrayList<LocationID>();
		
		//output.collect(new Text(bikeData[0]), new Text(bikeData[1]+","+bikeData[2]+","+bikeData[3]));
		while (values.hasNext())
		{	
			String readData = null;
			readData = values.next().toString();
			
			String[] mapperData = readData.trim().split(",");
			
			int id = Integer.parseInt(mapperData[0]);
			int count = Integer.parseInt(mapperData[2]);
			String locationName = mapperData[1];
			
			LocationID objLocation = new LocationID(id, count, locationName);
			
			locationList.add(objLocation);
		}
		
		
		//Sort the Array List by Count
		Collections.sort(locationList);
		
		/*
		for (LocationID loc: locationList)
		{
			System.out.println(loc);
		}
		*/
		
		
		if (key.equals(new Text("S")))
		{
			output.collect(null, new Text("---------------------------------------------------------------"));
			output.collect(null, new Text("Top 10 CitiBike 'Source' locations in NewYork"));
		}
		else
		{
			output.collect(null, new Text("---------------------------------------------------------------"));
			output.collect(null, new Text("Top 10 CitiBike 'Destination' locations in NewYork"));
		}
		
		if (locationList.size() > 10)
		{
			for (int i=0; i<10; i++)
			{
				output.collect(null, new Text(locationList.get(i).toString()));
			}
		}
		else
		{
			for (LocationID loc: locationList)
			{
				output.collect(null, new Text(loc.toString()));
			}
		}
	}
}
