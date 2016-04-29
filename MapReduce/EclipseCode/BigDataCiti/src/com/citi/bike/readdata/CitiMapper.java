package com.citi.bike.readdata;
import org.apache.hadoop.io.Writable; 
import org.apache.hadoop.io.WritableComparable; 
import org.apache.hadoop.mapred.MapReduceBase; 
import org.apache.hadoop.mapred.Mapper; 
import org.apache.hadoop.mapred.OutputCollector; 
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text; 


public class CitiMapper extends MapReduceBase implements Mapper<WritableComparable, Writable, Text, Text>
{

	@Override
	public void map(WritableComparable key, Writable value, OutputCollector<Text, Text> output, Reporter reporter)
			throws IOException {
		// TODO Auto-generated method stub

		String data = ((Text)value).toString().trim();
		Bike objBike = new Bike();

		if(data.length()>0)
		{
			String[] bikeData = data.split(",");

			if (bikeData.length == 15)
			{
				//TripDuration
				objBike.tripDuartion = (bikeData[0].length() > 0 && bikeData[0] != null) ? Long.parseLong(bikeData[0].replace("\"","")) : 0;

				//StartTime
				if (bikeData[1].length() > 0 && bikeData[1] != null)
				{
					bikeData[1]= bikeData[1].replace("\"","");
					objBike.setStartTime(bikeData[1]);
					objBike.setDateValue(bikeData[1]);
				}
				else
				{
					objBike.setStartTime("2013-12-31 00:00:00");
					objBike.setDateValue("2013-12-31 00:00:00");
				}

				//EndTime
				if(bikeData[2].length()>0 && bikeData[2]!=null)
				{
					bikeData[2]= bikeData[2].replace("\"","");
					objBike.setStopTime(bikeData[2]);
				}
				else
				{
					objBike.setStopTime("2013-12-31 00:00:00");
				}


				//startStationID
				objBike.startStationID = (bikeData[3].length() > 0 && bikeData[3] != null) ? Integer.parseInt((bikeData[3].replace("\"",""))) : 0;

				//startStationName
				objBike.startStationName = (bikeData[4].length() > 0 && bikeData[4] != null) ? bikeData[4].replace("\"","") : "unknown";

				//startStationLatitude
				objBike.startStationLatitude = (bikeData[5].length() > 0 && bikeData[5] != null) ? Double.valueOf(bikeData[5].replace("\"","")) : 0;

				//startStationLongitude
				objBike.startStationLongitude = (bikeData[6].length() > 0&& bikeData[6] != null) ? Double.valueOf(bikeData[6].replace("\"","")) : 0;

				//endStationID
				objBike.endStationID = (bikeData[7].length() > 0 && bikeData[7] != null) ? Integer.parseInt((bikeData[7].replace("\"",""))) : 0;

				//endStationName
				objBike.endStationName = (bikeData[8].length() > 0 && bikeData[8] != null) ? bikeData[8].replace("\"","") : "unknown";

				//endStationLatitude
				objBike.endStationLatitude = (bikeData[9].length() > 0 && bikeData[9] != null) ? Double.valueOf(bikeData[9].replace("\"","")) : 0;

				//endStationLongitude
				objBike.endStationLongitude = (bikeData[10].length() > 0&& bikeData[10] != null) ? Double.valueOf(bikeData[10].replace("\"","")) : 0;

				//BikeID
				objBike.bikeID = (bikeData[11].length() > 0 && bikeData[11] != null) ? Integer.parseInt((bikeData[11].replace("\"",""))) : 0;

				//userType
				objBike.userType = (bikeData[12].length() > 0 && bikeData[12] != null) ? bikeData[12].replace("\"","") : "unknown";

				//birthYear
				if(bikeData[13].equals("\\N")|| bikeData[13].equals("") || bikeData[13].equals(null))
				{	
					objBike.setBirthYear(2016);
				}
				else
				{
					objBike.setBirthYear(Integer.parseInt(bikeData[13].replace("\"","")));
				}
				objBike.ageGroup = objBike.calculateAgeGroup(objBike.getBirthYear());

				//gender
				objBike.gender = (bikeData[14].length() > 0 && bikeData[14] != null) ? Integer.parseInt((bikeData[14].replace("\"",""))) : 2;


				//objBike.displayRecords(objBike);

				DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");

				//System.out.println(objBike.getDateValue());
				String dateValue = dateFormat2.format(objBike.getDateValue());
				//System.out.println(dateValue);

				String outputData = objBike.getTripDuartion() + "," 
						+ dateValue + ","
						+ dateFormat1.format(objBike.getStartTime()) + "," 
						+ dateFormat1.format(objBike.getStopTime()) + ","
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
						+ objBike.getGender()+","
						+ objBike.getAgeGroup();

				output.collect(new Text("1"), new Text(outputData));
			}
		}
	}
}
