package kr.co.infopub.chapter.s170;
public class HaversineDistance {
 // �� �� �� ��ġ ������ �Ÿ� 
 public static double distance(double lat1, double lng1, double lat2, double lng2) {
	 double earthR = 6371; // km
	 double dLat = (lat2-lat1);
	 double dLng = (lng2-lng1);
	 double a = Math.sin(Math.toRadians(dLat/2)) * Math.sin(Math.toRadians(dLat/2)) +
	            Math.cos(Math.toRadians( lat1))  * Math.cos(Math.toRadians( lat2)) * 
	            Math.sin(Math.toRadians( dLng/2))* Math.sin(Math.toRadians( dLng/2)); 
	 double c = 2 * Math.asin(Math.sqrt(a)); 
	 double d = earthR * c;
	 return d;
 }
 //��ü ����� �����ε�
 public static double distance(Geo geo1, Geo geo2) { 
    return distance(geo1.getLatitude(), geo1.getLongitude(),
    		geo2.getLatitude(), geo2.getLongitude());
 }
 public static void main(String[] args) {	 
	double latitude1=37.52127220511242;
    double longitude1=127.0074462890625;   // ����
	double latitude2=35.137879119634185;
	double longitude2=129.04541015625;     // �λ�
	
	double d=distance(new Geo(latitude1,longitude1),
			          new Geo(latitude2,longitude2));
	System.out.println("���� ~ �λ갣 �Ÿ� : "+d+" Km");
 }
}