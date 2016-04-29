package com.citi.bike.sourcedestination;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class CitiSDMapper1 extends MapReduceBase implements Mapper<WritableComparable, Writable, Text, Text>
{
	@Override
	public void map(WritableComparable key, Writable value, OutputCollector<Text, Text> output, Reporter reporter)
			throws IOException {

		String data = ((Text)value).toString().trim();

		if(data.length()>0)
		{
			String[] bikeData = data.split(",");

			int sourceID = Integer.parseInt(bikeData[4]);
			int destinationID = Integer.parseInt(bikeData[8]);

			if(sourceID != destinationID)
			{
				if(sourceID < destinationID)
				{
					output.collect(new Text(sourceID + "_" + destinationID), new Text (bikeData[4] + "," + bikeData[5] + "," + bikeData[6] + "," + bikeData[7] + "," + bikeData[8] + "," + bikeData[9] + "," + bikeData[10] + "," + bikeData[11] + "," + bikeData[0]));
				}
				else
				{
					output.collect(new Text(destinationID + "_" + sourceID), new Text (bikeData[8] + "," + bikeData[9] + "," + bikeData[10] + "," + bikeData[11] + "," + bikeData[4] + "," + bikeData[5] + "," + bikeData[6] + "," + bikeData[7] + "," + bikeData[0]));
				}
			}
		}
	}
}
