package com.citi.bike.countlocation;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import com.citi.bike.readdata.Bike;

public class CitiLocationMapper extends MapReduceBase implements Mapper<WritableComparable, Writable, Text, Text>
{

	@Override
	public void map(WritableComparable key, Writable value, OutputCollector<Text, Text> output, Reporter reporter)
			throws IOException {
		// TODO Auto-generated method stub
		
		String data = ((Text)value).toString().trim();
		//Bike objBike = new Bike();

		if(data.length()>0)
		{
			String[] bikeData = data.split(",");

			/*
			//TripDuration
			objBike.tripDuartion = (bikeData[0].length() > 0 && bikeData[0] != null) ? Long.parseLong(bikeData[0].replace("\"","")) : null;
			
			//StartTime
			if (bikeData[1].length() > 0 && bikeData[1] != null)
			{
				bikeData[1]= bikeData[1].replace("\"","");
				objBike.setStartTime(bikeData[1]);
			}
			else
			{
				objBike.setStartTime(null);
			}

			//EndTime
			if(bikeData[2].length()>0 && bikeData[2]!=null)
			{
				bikeData[2]= bikeData[2].replace("\"","");
				objBike.setStopTime(bikeData[2]);
			}
			else
			{
				objBike.setStopTime(null);
			}

			//startStationID
			objBike.startStationID = (bikeData[3].length() > 0 && bikeData[3] != null) ? Integer.parseInt((bikeData[3].replace("\"",""))) : null;

			//startStationName
			objBike.startStationName = (bikeData[4].length() > 0 && bikeData[4] != null) ? bikeData[4].replace("\"","") : null;

			//startStationLatitude
			objBike.startStationLatitude = (bikeData[5].length() > 0 && bikeData[5] != null) ? Double.valueOf(bikeData[5].replace("\"","")) : null;

			//startStationLongitude
			objBike.startStationLongitude = (bikeData[6].length() > 0&& bikeData[6] != null) ? Double.valueOf(bikeData[6].replace("\"","")) : null;

			//endStationID
			objBike.endStationID = (bikeData[7].length() > 0 && bikeData[7] != null) ? Integer.parseInt((bikeData[7].replace("\"",""))) : null;

			//endStationName
			objBike.endStationName = (bikeData[8].length() > 0 && bikeData[8] != null) ? bikeData[8].replace("\"","") : null;

			//endStationLatitude
			objBike.endStationLatitude = (bikeData[9].length() > 0 && bikeData[9] != null) ? Double.valueOf(bikeData[9].replace("\"","")) : null;

			//endStationLongitude
			objBike.endStationLongitude = (bikeData[10].length() > 0&& bikeData[10] != null) ? Double.valueOf(bikeData[10].replace("\"","")) : null;

			//BikeID
			objBike.bikeID = (bikeData[11].length() > 0 && bikeData[11] != null) ? Integer.parseInt((bikeData[11].replace("\"",""))) : null;

			//userType
			objBike.userType = (bikeData[12].length() > 0 && bikeData[12] != null) ? bikeData[12].replace("\"","") : null;

			//birthYear
			if(bikeData[13].equals("\\N")|| bikeData[13]==null)
			{
				objBike.setBirthYear(0);
			}
			else
			{
				objBike.setBirthYear(Integer.parseInt(bikeData[13].replace("\"","")));
			}

			//gender
			objBike.gender = (bikeData[14].length() > 0 && bikeData[14] != null) ? Integer.parseInt((bikeData[14].replace("\"",""))) : null;

			objBike.displayRecords(objBike);
			
			String outputData = objBike.getTripDuartion() + "," 
					+ objBike.getStartTime() + "," 
					+ objBike.getStopTime() + ","
					+ objBike.getStartStationID()+ ","
					+ objBike.getStartStationName()+","
					+ objBike.getStartStationLatitude() +","
					+ objBike.getStartStationLongitude()+","
					+ objBike.getEndStationID() + ","
					+ objBike.getEndStationName()+","
					+ objBike.getEndStationLatitude()+","
					+ objBike.getEndStationLongitude()+","
					+ objBike.getBikeID()+","
					+ objBike.getUserType()+","
					+ objBike.getBirthYear()+","
					+ objBike.getGender();

*/
			output.collect(new Text(bikeData[3]), new Text("S"+","+bikeData[4]));
			output.collect(new Text(bikeData[7]), new Text("D"+","+bikeData[8]));
		}
	}
}
