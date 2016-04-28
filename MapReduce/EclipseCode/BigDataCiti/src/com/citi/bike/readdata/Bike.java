package com.citi.bike.readdata;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
//tripduration	starttime	stoptime	start station id	start station name	start station latitude	start station longitude	end station id	end station name	end station latitude	end station longitude	bikeid	usertype	birth year	gender

public class Bike
{
	//variables
	public long tripDuartion;
	public Date dateValue;
	public Date startTime;
	public Date stopTime;
	public int startStationID;
	public String startStationName;
	public double startStationLatitude;
	public double startStationLongitude;
	public int endStationID;
	public String endStationName;
	public double endStationLatitude;
	public double endStationLongitude;
	public long bikeID;
	public String userType;
	public int birthYear;
	public int gender;
	public String ageGroup;


	//getters and setters methods

	public int getStartStationID() {
		return startStationID;
	}
	public String getAgeGroup() {
		return ageGroup;
	}
	
	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}
	public long getTripDuartion() {
		return tripDuartion;
	}

	public void setTripDuartion(long tripDuartion) {
		this.tripDuartion = tripDuartion;
	}

	public Date getDateValue() {
		return dateValue;
	}

	public void setDateValue(String dateValue) {

		if (dateValue != null)
		{
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date temp=new Date();
			try {
				temp = dateFormat.parse(dateValue);
				this.dateValue = temp;
				
				//System.out.println(this.dateValue);

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			this.dateValue = null;
		}
	}

	public Date getStartTime() {
		return startTime;
	}


	public void setStartTime(String date) {

		if (date != null)
		{
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date temp=new Date();
			try {
				temp = dateFormat.parse(date);
				this.startTime = temp;

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			this.startTime = null;
		}
	}

	public Date getStopTime() {
		return stopTime;
	}

	public void setStopTime(String date) {

		if (date != null)
		{
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date temp = new Date();
			try {
				temp = dateFormat.parse(date);
				this.stopTime = temp; 
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			this.stopTime = null;
		}
	}

	public void setStartStationID(int startStationID) {
		this.startStationID = startStationID;
	}
	public String getStartStationName() {
		return startStationName;
	}
	public void setStartStationName(String startStationName) {
		this.startStationName = startStationName;
	}
	public double getStartStationLatitude() {
		return startStationLatitude;
	}
	public void setStartStationLatitude(double startStationLatitude) {
		this.startStationLatitude = startStationLatitude;
	}
	public double getStartStationLongitude() {
		return startStationLongitude;
	}
	public void setStartStationLongitude(double startStationLongitude) {
		this.startStationLongitude = startStationLongitude;
	}
	public int getEndStationID() {
		return endStationID;
	}
	public void setEndStationID(int endStationID) {
		this.endStationID = endStationID;
	}
	public String getEndStationName() {
		return endStationName;
	}
	public void setEndStationName(String endStationName) {
		this.endStationName = endStationName;
	}
	public double getEndStationLatitude() {
		return endStationLatitude;
	}
	public void setEndStationLatitude(double endStationLatitude) {
		this.endStationLatitude = endStationLatitude;
	}
	public double getEndStationLongitude() {
		return endStationLongitude;
	}
	public void setEndStationLongitude(double endStationLongitude) {
		this.endStationLongitude = endStationLongitude;
	}
	public long getBikeID() {
		return bikeID;
	}
	public void setBikeID(long bikeID) {
		this.bikeID = bikeID;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public int getBirthYear() {
		return birthYear;
	}
	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}


	//Custom Methods
	
	public String calculateAgeGroup(int year)
	{
		Date date = new Date();
		
		int calculatedAge = 2016 - year;
		//System.out.println(calculatedAge);
		
		if (calculatedAge >= 0 && calculatedAge <=10)
		{
			return "0-10";
		}
		else if (calculatedAge > 10 && calculatedAge <=20)
		{
			return "11-20";
		}
		else if (calculatedAge > 20 && calculatedAge <=30)
		{
			return "21-30";
		}
		else if (calculatedAge > 30 && calculatedAge <=40)
		{
			return "31-40";
		}
		else if (calculatedAge > 40 && calculatedAge <=50)
		{
			return "41-50";
		}
		else if (calculatedAge > 50 && calculatedAge <=60)
		{
			return "51-60";
		}
		else if (calculatedAge > 60)
		{
			return "61plus";
		}
		else
		{
			return "NotAvailable";
		}
	}
	
	public void displayRecords(Bike objBike)
	{
		System.out.println("-----------------------------------------");
		System.out.println("Trip Duration:" + objBike.getTripDuartion());
		System.out.println("StartTime:" + objBike.getStartTime());
		System.out.println("StopTime:" + objBike.getStopTime());
		System.out.println("StartStationID:" + objBike.getStartStationID());
		System.out.println("StartStationName:" + objBike.getStartStationName());
		System.out.println("StartStationLatitude:" + objBike.getStartStationLatitude());
		System.out.println("StartStationLongitude:" + objBike.getStartStationLongitude());
		System.out.println("EndStationID:" + objBike.getEndStationID());
		System.out.println("EndStationName:" + objBike.getEndStationName());
		System.out.println("EndStationLatitude:" + objBike.getEndStationLatitude());
		System.out.println("EndStationLongitude:" + objBike.getEndStationLongitude());
		System.out.println("BikeID:" + objBike.getBikeID());
		System.out.println("UserType:" + objBike.getUserType());
		System.out.println("BirthYear:" + objBike.getBirthYear());
		System.out.println("Gender:" + objBike.getGender());	
		System.out.println("-----------------------------------------"); 
	}
}

