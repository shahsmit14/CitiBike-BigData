package com.citi.bike.dao;

import java.util.Comparator;
import java.util.Date;

public class LocationID implements Comparable<LocationID> {

	public int id;
	public String locationName;
	public int count;

	public LocationID(int id, int count, String locationName) {
		super();
		this.id = id;
		this.locationName = locationName;
		this.count = count;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}


	@Override
	public int compareTo(LocationID objLocation) {
		// TODO Auto-generated method stub

		int compare = ((LocationID) objLocation).getCount();
		return compare - this.count;
	}

	@Override
	public String toString() {
		return "Location ID = " + id + "\t\t\t\t\tCount = " + count + "\t\t\t\t\tLocation Name = " + locationName;
	}

}
