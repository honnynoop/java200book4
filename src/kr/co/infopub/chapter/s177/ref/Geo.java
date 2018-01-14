package kr.co.infopub.chapter.s177.ref;

public class Geo {
	private double latitude;      
	private double longitude;   
	public Geo(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public Geo() {            // 기본생성자
		this(37.5, 127.0);   
	}
	//생성자 오버로딩 추가
	public Geo(double [] latlng) {
		this.latitude = latlng[0];
		this.longitude = latlng[1];
	}
	//복사 생성자
	public Geo(Geo geo) {
		this.latitude = geo.getLatitude();
		this.longitude =geo.getLongitude();
	}
	
	public double getLatitude() {   //getter 
		return latitude;
	}
	public void setLatitude(double latitude) { //setter
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	@Override
	public String toString() {
		return "(" + latitude + ", " + longitude + ")";
	}
	
}
