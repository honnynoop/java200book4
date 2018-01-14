package kr.co.infopub.chapter.s178.json;

import java.io.Serializable;
public class Earthquake implements Serializable {

	public String eqid;
	public double magnitude;
	public double longitude;
	public double latitude;
	public String source;
	public String datetime;
	public double depth;
	public String location;

	public Earthquake() {
	}

	public Earthquake(String eqid, double magnitude, double longitude,
					  double latitude, String source,
					  String datetime, double depth, String location) {
		this.eqid = eqid;
		this.magnitude = magnitude;
		this.longitude = longitude;
		this.latitude = latitude;
		this.source = source;
		this.datetime = datetime;
		this.depth = depth;
		this.location = location;
	}

	public String getEqid() {
		return eqid;
	}

	public void setEqid(String eqid) {
		this.eqid = eqid;
	}

	public double getMagnitude() {
		return magnitude;
	}

	public void setMagnitude(double magnitude) {
		this.magnitude = magnitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public double getDepth() {
		return depth;
	}

	public void setDepth(double depth) {
		this.depth = depth;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Earthquake{" +
				"eqid='" + eqid + '\'' +
				", magnitude=" + magnitude +
				", longitude=" + longitude +
				", latitude=" + latitude +
				", source='" + source + '\'' +
				", datetime='" + datetime + '\'' +
				", depth=" + depth +
				", location='" + location + '\'' +
				'}';
	}
}
