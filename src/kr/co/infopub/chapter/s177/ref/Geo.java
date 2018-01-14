package kr.co.infopub.chapter.s177.ref;

public class Geo {
	private double latitude;      
	private double longitude;   
	public Geo(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public Geo() {            // �⺻������
		this(37.5, 127.0);   
	}
	//������ �����ε� �߰�
	public Geo(double [] latlng) {
		this.latitude = latlng[0];
		this.longitude = latlng[1];
	}
	//���� ������
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
