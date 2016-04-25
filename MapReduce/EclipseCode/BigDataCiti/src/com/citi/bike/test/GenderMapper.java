package com.citi.bike.test;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import com.citi.bike.readdata.Bike;

public class GenderMapper extends MapReduceBase implements Mapper<WritableComparable, Writable, Text, Text> {

	@Override
	public void map(WritableComparable key, Writable value, OutputCollector<Text, Text> output, Reporter reporter)
			throws IOException {
		// TODO Auto-generated method stub
		String data = ((Text)value).toString().trim();
		System.out.println("Data:" + data);
		if(data.length()>0)
		{
			String[] bikeData = data.split(",");
			
			String gender = bikeData[14];
			System.out.println("Gender" + gender);
			if(gender.equals("1"))
			{
				output.collect(new Text("male"), new Text("1"));
			}
			else if(gender.equals("2"))
			{
				output.collect(new Text("female"), new Text("1"));
			}
			//if(gender.equals("0"))
			else
			{
				output.collect(new Text("NA_gender"), new Text("1"));
			}
	
			String userType = bikeData[12];
			if(userType.equals("Customer"))
			{
				output.collect(new Text("Customer"), new Text("1"));
			}
			else if(userType.equals("Subscriber"))
			{
				output.collect(new Text("Subscriber"), new Text("1"));
			}
			//String birthYear = bikeData[13];
			String customerType = bikeData[12];
			
			String op="";
			
			if(customerType.equals("Customer")&& gender.equals("1"))
			{
				 op = "Male Customer";
			}
			else if(customerType.equals("Customer")&& gender.equals("2"))
			{
				 op = "Female Customer";
			}
			else if(customerType.equals("Customer")&& gender.equals("0"))
			{
				 op = "Gender Unknown Customer";
			}
			
			else if(customerType.equals("Subscriber")&& gender.equals("1"))
			{
				 op = "Male Subscriber";
			}
			else if(customerType.equals("Subscriber")&& gender.equals("2"))
			{
				 op = "Female Subscriber";
			}
					
			output.collect(new Text(op), new Text("1"));
			}
	}

}
