package kr.co.infopub.chapter.s179;
import java.io.Serializable;
public class KREarthQuake implements Serializable {
	
	private double latitude;
	private double longitude;
	private String time;
	private String location;
	private double magnitude;

	public KREarthQuake() {
		super();
	}
	public KREarthQuake(double latitude, double longitude, String time,
						String location, double magnitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.time = time;
		this.location = location;
		this.magnitude = magnitude;
	}
	@Override
	public String toString() {
		return "KREarthQuake [latitude=" + latitude + ", longitude="
				+ longitude + ", time=" + time + ", location=" + location
				+ ", magnitude=" + magnitude + "]";
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public double getMagnitude() {
		return magnitude;
	}
	public void setMagnitude(double magnitude) {
		this.magnitude = magnitude;
	}
	
	
}
