package com.citi.bike.dao;

public class LocationRoute implements Comparable<LocationRoute> {

	public String id;
	public String locationName1;
	public String locationName2;
	public int count;

	public LocationRoute(String id, int count, String locationName1, String locationName2) {
		super();
		this.id = id;
		this.locationName1 = locationName1;
		this.locationName2 = locationName2;
		this.count = count;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getLocationName1() {
		return locationName1;
	}


	public void setLocationName1(String locationName1) {
		this.locationName1 = locationName1;
	}


	public String getLocationName2() {
		return locationName2;
	}


	public void setLocationName2(String locationName2) {
		this.locationName2 = locationName2;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	@Override
	public int compareTo(LocationRoute objLocationRoute) {
		// TODO Auto-generated method stub

		int compare = ((LocationRoute) objLocationRoute).getCount();
		return compare - this.count;
	}

	@Override
	public String toString() {
		return count + " rides \t\t\t\t\t Between Location's = " + locationName1  + " -AND- " + locationName2;
	}

}
